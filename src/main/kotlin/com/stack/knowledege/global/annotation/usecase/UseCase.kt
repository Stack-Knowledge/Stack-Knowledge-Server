package com.stack.knowledege.global.annotation.usecase

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Component
@Transactional(rollbackFor = [Exception::class])
annotation class UseCase
