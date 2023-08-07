package hello.servlet.basic

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "helloServlet", urlPatterns = ["/hello"])
class HelloServlet: HttpServlet() {
    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        println("HelloServlet.service")
        println("request = $request")
        println("response = $response")

        val username: String = request.getParameter("username")
        println("username = $username")

        response.contentType = "text/plain"
        response.characterEncoding = "utf-8"
        response.writer.println("hello $username")
    }
}