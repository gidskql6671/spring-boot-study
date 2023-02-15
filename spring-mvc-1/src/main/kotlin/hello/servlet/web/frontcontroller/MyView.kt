package hello.servlet.web.frontcontroller

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class MyView(private val viewPath: String) {
    fun render(request: HttpServletRequest, response: HttpServletResponse) {
        val dispatcher = request.getRequestDispatcher(viewPath)
        dispatcher.forward(request, response)
    }
}