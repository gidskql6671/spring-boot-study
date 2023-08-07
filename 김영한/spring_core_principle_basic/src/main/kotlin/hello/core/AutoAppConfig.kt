package hello.core

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScan.Filter
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(excludeFilters = [Filter(Configuration::class)])
class AutoAppConfig {
}