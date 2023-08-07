package hello.servlet.web.frontcontroller.v5.adapter

import hello.servlet.web.frontcontroller.ModelView
import hello.servlet.web.frontcontroller.v3.ControllerV3
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class ControllerV3HandlerAdapter: MyHandlerAdapter {
    override fun supports(handler: Any): Boolean = handler is ControllerV3

    override fun handle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): ModelView {
        val controller = handler as ControllerV3
        val paramMap: Map<String, String> = createParamMap(request)
        return controller.process(paramMap)
    }

    private fun createParamMap(request: HttpServletRequest): Map<String, String> {
        val paramMap: MutableMap<String, String> = HashMap()
        request.parameterNames.asIterator()
            .forEachRemaining { paramName: String ->
                paramMap[paramName] = request.getParameter(paramName)
            }
        return paramMap
    }
}