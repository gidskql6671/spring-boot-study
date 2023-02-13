package hello.servlet.basic.response

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@WebServlet(name = "responseHtmlServlet", urlPatterns = ["/response-html"])
class ResponseHtmlServlet: HttpServlet() {

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        response.contentType = "text/html"
        response.characterEncoding = "utf-8"

        val writer = response.writer
        writer.println("<html>")
        writer.println("<body>")
        writer.println(" <div>안녕?</div>")
        writer.println("</body>")
        writer.println("</html>")
    }
}