package hello.servlet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan

@ServletComponentScan
@SpringBootApplication
class SpringMvc1Application

fun main(args: Array<String>) {
    runApplication<SpringMvc1Application>(*args)
}
