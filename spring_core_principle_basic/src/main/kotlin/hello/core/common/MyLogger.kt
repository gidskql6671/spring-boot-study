package hello.core.common

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component
import java.util.*

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
class MyLogger {
    private lateinit var uuid: String
    var requestURL = ""

    fun log(message: String) {
        println("[$uuid][$requestURL] $message")
    }

    @PostConstruct
    fun init() {
        uuid = UUID.randomUUID().toString()
        println("[$uuid] request scope bean create: $this")
    }

    @PreDestroy
    fun close() {
        println("[$uuid] request scope bean close: $this")
    }
}