package hello.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringCorePrincipleBasicApplication

fun main(args: Array<String>) {
    runApplication<SpringCorePrincipleBasicApplication>(*args)
}
