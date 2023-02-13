package hello.servlet.basic.response

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import hello.servlet.basic.HelloData
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@WebServlet(name = "responseJsonServlet", urlPatterns = ["/response-json"])
class ResponseJsonServlet: HttpServlet() {
    private val objectMapper = ObjectMapper().registerModule(KotlinModule.Builder().build())

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        response.setHeader("content-type", "application/json")
        response.characterEncoding = "utf-8"

        val data = HelloData("kim", 20)
        val result: String = objectMapper.writeValueAsString(data)
        response.writer.write(result)
    }
}