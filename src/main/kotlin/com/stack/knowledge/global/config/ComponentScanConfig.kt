package com.stack.knowledge.global.config

import com.stack.knowledge.common.annotation.service.ServiceWithReadOnlyTransaction
import com.stack.knowledge.common.annotation.service.ServiceWithTransaction
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType

@Configuration
@ComponentScan(
    basePackages = ["com.stack.knowledge"],
    includeFilters = [
        ComponentScan.Filter(
            type = FilterType.ANNOTATION,
            classes = [
                ServiceWithTransaction::class,
                ServiceWithReadOnlyTransaction::class
            ]
        )
    ]
)
class ComponentScanConfig