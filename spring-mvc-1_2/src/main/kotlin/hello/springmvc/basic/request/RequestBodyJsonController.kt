package hello.springmvc.basic.request

import com.fasterxml.jackson.databind.ObjectMapper
import hello.springmvc.basic.HelloData
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.PostMapping
import java.nio.charset.StandardCharsets
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Controller
class RequestBodyJsonController {
    private val log: Logger = LoggerFactory.getLogger(javaClass)
    private val objectMapper = ObjectMapper()

    @PostMapping("/request-body-json-v1")
    fun requestBodyJsonV1(
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        val inputStream = request.inputStream
        val messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)
        log.info("messageBody={}", messageBody)

        val (username, age) = objectMapper.readValue(messageBody, HelloData::class.java)
        log.info("username={}, age={}", username, age)

        response.writer.write("ok")
    }
}