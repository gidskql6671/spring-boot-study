package hello.springmvc.basic.request

import com.fasterxml.jackson.databind.ObjectMapper
import hello.springmvc.basic.HelloData
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
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

    /**
     * @RequestBody
     * HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     *
     * @ResponseBody
     * - 모든 메서드에 @ResponseBody 적용
     * - 메시지 바디 정보 직접 반환(view 조회X)
     * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     */
    @ResponseBody
    @PostMapping("/request-body-json-v2")
    fun requestBodyJsonV2(@RequestBody messageBody: String): String {
        val (username, age) = objectMapper.readValue(messageBody, HelloData::class.java)
        log.info("username={}, age={}", username, age)
        return "ok"
    }

    /**
     * @RequestBody 생략 불가능(@ModelAttribute 가 적용되어 버림)
     * HttpMessageConverter 사용 -> MappingJackson2HttpMessageConverter (contenttype: application/json)
     */
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    fun requestBodyJsonV3(@RequestBody data: HelloData): String {
        log.info("username={}, age={}", data.username, data.age)
        return "ok"
    }
}