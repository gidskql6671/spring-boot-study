package hello.servlet.basic.request

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import hello.servlet.basic.HelloData
import org.springframework.util.StreamUtils
import java.nio.charset.StandardCharsets
import javax.servlet.ServletInputStream
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@WebServlet(name = "requestBodyJsonServlet", urlPatterns = ["/request-body-json"])
class RequestBodyJsonServlet: HttpServlet()  {
    private val objectMapper = ObjectMapper().registerModule(KotlinModule.Builder().build())

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        val inputStream: ServletInputStream = request.inputStream

        val messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)
        println("messageBody = $messageBody")

        val (username, age) = objectMapper.readValue(messageBody, HelloData::class.java)

        println("helloData.username = $username")
        println("helloData.age = $age")

        response.writer.write("ok")
    }
}