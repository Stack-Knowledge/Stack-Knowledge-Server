package com.stack.knowledege.domain.auth.application.usecase

import com.stack.knowledege.domain.auth.presentation.data.request.GAuthSignInRequest
import com.stack.knowledege.domain.auth.presentation.data.response.TokenResponse
import com.stack.knowledege.domain.student.application.spi.CommandStudentPort
import com.stack.knowledege.domain.student.application.spi.QueryStudentPort
import com.stack.knowledege.domain.student.application.usecase.CreateStudentUseCase
import com.stack.knowledege.domain.student.domain.Student
import com.stack.knowledege.domain.student.exception.StudentNotFoundException
import com.stack.knowledege.domain.student.persistence.mapper.StudentMapper
import com.stack.knowledege.domain.student.persistence.repository.StudentJpaRepository
import com.stack.knowledege.domain.user.application.spi.UserPort
import com.stack.knowledege.domain.user.domain.User
import com.stack.knowledege.domain.user.domain.constant.Authority
import com.stack.knowledege.domain.user.exception.UserNotFoundException
import com.stack.knowledege.global.annotation.usecase.UseCase
import com.stack.knowledege.global.security.spi.JwtGeneratorPort
import com.stack.knowledege.thirdparty.gauth.spi.GAuthPort
import java.util.*

@UseCase
class GAuthSignInUseCase(
    private val gAuthPort: GAuthPort,
    private val userPort: UserPort,
    private val jwtGeneratorPort: JwtGeneratorPort,
    private val createStudentUseCase: CreateStudentUseCase,
    private val queryStudentPort: QueryStudentPort,
    private val commandStudentPort: CommandStudentPort,
    private val studentJpaRepository: StudentJpaRepository,
    private val studentMapper: StudentMapper
) {
    fun execute(gAuthSignInRequest: GAuthSignInRequest): TokenResponse {

        val gAuthToken = gAuthPort.queryGAuthToken(gAuthSignInRequest.code)
        val gAuthUserInfo = gAuthPort.queryUserInfo(gAuthToken.accessToken)
        val authority = userPort.queryUserRoleByEmail(gAuthUserInfo.email, gAuthUserInfo.role)

        val user = createUser(
            User(
                id = UUID.randomUUID(),
                email = gAuthUserInfo.email,
                name = gAuthUserInfo.name,
                profileImage = "",
                authority = authority
            )
        )

        print("userId   ====================")
        println(user.id)

//        if (!queryStudentPort.existStudentByUser(user))
//            val studentId = createStudentUseCase.execute(user)

        val student = Student(
            id = UUID.randomUUID(),
            currentPoint = 0,
            cumulatePoint = 0,
            user = user.id
        )
        println(user.id)
        print("studentId ================== ")
        println(student.id)
//        commandStudentPort.save(student)
        studentJpaRepository.save(studentMapper.toEntity(student))
        val studentId = queryStudentPort.queryStudentByUser(user)?.let { it.id }
//            print("진짜 userId ================== ")
//            println(it!!.id)
//        }


        print("찐 studentId==========================    ")
        println(studentId)

        when (user.authority) {
            Authority.ROLE_STUDENT -> return jwtGeneratorPort.receiveToken(studentId!!, user.authority)
            Authority.ROLE_TEACHER -> return jwtGeneratorPort.receiveToken(user.id, user.authority)
        }

//        return jwtGeneratorPort.receiveToken(user.id)
    }

    private fun createUser(user: User): User {
        return when (userPort.queryExistByEmail(user.email)) {
            true -> userPort.queryUserByEmail(user.email) ?: throw UserNotFoundException()
            false -> userPort.save(user)
        }
    }
}