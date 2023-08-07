package hello.servlet.web.frontcontroller.v1

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


interface ControllerV1 {
    fun process(request: HttpServletRequest, response: HttpServletResponse)
}