package hello.servlet.web.frontcontroller.v5.adapter

import hello.servlet.web.frontcontroller.ModelView
import hello.servlet.web.frontcontroller.v4.ControllerV4
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class ControllerV4HandlerAdapter: MyHandlerAdapter {
    override fun supports(handler: Any): Boolean = handler is ControllerV4

    override fun handle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): ModelView {
        val controller = handler as ControllerV4

        val paramMap: Map<String, String> = createParamMap(request)
        val model: MutableMap<String, Any> = HashMap()
        val viewName = controller.process(paramMap, model)

        return ModelView(viewName, model)
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