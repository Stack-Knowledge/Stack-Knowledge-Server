package com.stack.knowledge.domain.user.application.service

import com.stack.knowledge.common.annotation.service.ServiceWithTransaction
import com.stack.knowledge.domain.user.application.spi.UserPort
import com.stack.knowledge.domain.user.domain.User
import com.stack.knowledge.domain.user.domain.constant.ApproveStatus
import com.stack.knowledge.domain.user.exception.AlreadyApprovedUserException
import com.stack.knowledge.domain.user.exception.InvalidApproveStatusException
import com.stack.knowledge.domain.user.exception.MessageSendFailedException
import com.stack.knowledge.domain.user.exception.UserNotFoundException
import com.stack.knowledge.domain.user.presentation.data.request.UpdateUserApproveStatusRequest
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.thymeleaf.context.Context
import org.thymeleaf.spring5.SpringTemplateEngine
import java.util.*

@ServiceWithTransaction
class UpdateUserApproveStatusService(
    private val userPort: UserPort,
    private val mailSender: JavaMailSender,
    private val templateEngine: SpringTemplateEngine
) {
    fun execute(userId: UUID, updateUserApproveStatusRequest: UpdateUserApproveStatusRequest) {
        val user = userPort.queryUserById(userId) ?: throw UserNotFoundException()

        if (user.approveStatus == ApproveStatus.APPROVED)
            throw AlreadyApprovedUserException()

        when (updateUserApproveStatusRequest.approveStatus) {
            ApproveStatus.PENDING -> throw InvalidApproveStatusException()
            ApproveStatus.APPROVED -> {
                sendEmail(user, ApproveStatus.APPROVED)
                userPort.save(user.copy(approveStatus = updateUserApproveStatusRequest.approveStatus))
            }
            ApproveStatus.REJECTED -> {
                sendEmail(user, ApproveStatus.REJECTED)
                userPort.deleteByUserId(userId)
            }
        }
    }

    private fun sendEmail(user: User, approveStatus: ApproveStatus) {
        runCatching {
            val message = mailSender.createMimeMessage()
            val helper = MimeMessageHelper(message, "UTF-8")

            val context = Context()
            val templateName = when (approveStatus) {
                ApproveStatus.APPROVED -> "approval"
                ApproveStatus.REJECTED -> "reject"
                else -> null
            }

            val template = templateEngine.process(templateName, context)

            helper.setSubject("Stack-Knowledge 이메일 인증")
            helper.setTo(user.email)
            helper.setText(template, true)

            mailSender.send(message)
        }.onFailure {
            throw MessageSendFailedException()
        }
    }
}