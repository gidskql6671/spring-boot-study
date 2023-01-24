package com.example.sbs

import jakarta.persistence.EntityManager
import org.springframework.context.annotation.Configuration

@Configuration
class SpringConfig(private val em: EntityManager) {
}