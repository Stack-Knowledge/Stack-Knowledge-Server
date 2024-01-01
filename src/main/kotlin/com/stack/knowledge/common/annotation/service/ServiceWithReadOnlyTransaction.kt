package com.stack.knowledge.common.annotation.service

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Component
@Transactional(readOnly = true)
annotation class ServiceWithReadOnlyTransaction
