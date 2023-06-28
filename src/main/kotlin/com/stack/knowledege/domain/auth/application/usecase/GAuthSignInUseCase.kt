package com.stack.knowledege.domain.auth.application.usecase

import com.stack.knowledege.domain.auth.presentation.data.request.GAuthSignInRequest
import com.stack.knowledege.domain.auth.presentation.data.response.TokenResponse
import com.stack.knowledege.domain.student.application.usecase.CreateStudentUseCase
import com.stack.knowledege.domain.student.application.spi.CommandStudentPort
import com.stack.knowledege.domain.user.application.spi.UserPort
import com.stack.knowledege.domain.user.domain.User
import com.stack.knowledege.domain.user.domain.constant.UserRole
import com.stack.knowledege.domain.user.exception.UserNotFoundException
import com.stack.knowledege.global.annotation.usecase.UseCase
import com.stack.knowledege.global.security.spi.JwtGeneratorPort
import com.stack.knowledege.thirdparty.gauth.spi.GAuthPort
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@UseCase
class GAuthSignInUseCase(
    private val gAuthPort: GAuthPort,
    private val userPort: UserPort,
    private val jwtGeneratorPort: JwtGeneratorPort,
    private val createStudentUseCase: CreateStudentUseCase,
    private val commandStudentPort: CommandStudentPort,
    @PersistenceContext
    private var entityManager: EntityManager
) {
    fun execute(gAuthSignInRequest: GAuthSignInRequest): TokenResponse {

        val gAuthToken = gAuthPort.queryGAuthToken(gAuthSignInRequest.code)
        val gAuthUserInfo = gAuthPort.queryUserInfo(gAuthToken.accessToken)
        val role = userPort.queryUserRoleByEmail(gAuthUserInfo.email, gAuthUserInfo.role)

        val em = entityManager.entityManagerFactory.createEntityManager()

        em.transaction.begin()
        val user = createUser(
            User(
                id = UUID.randomUUID(),
                email = gAuthUserInfo.email,
                name = gAuthUserInfo.name,
                profileImage = "gAuthUserInfo.profileUrl",
                roles = mutableListOf(role)
            )
        )
        em.flush()

        if (role == UserRole.ROLE_STUDENT) {
            println("asdfasdfasdfasdf")
            createStudentUseCase.execute(user)
        }

        em.flush()
        println("ㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹ")

        return jwtGeneratorPort.receiveToken(user.email)
    }

    private fun createUser(user: User): User =
        when (userPort.queryExistByEmail(user.email)) {
            true -> userPort.queryUserByEmail(user.email) ?: throw UserNotFoundException()
            false -> userPort.save(user)
        }
}