package hello.servlet.web.frontcontroller.v4

import hello.servlet.web.frontcontroller.MyView
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@WebServlet(name = "frontControllerServletV4", urlPatterns = ["/front-controller/v4/*"])
class FrontControllerServletV4: HttpServlet() {
    private val controllerMap: MutableMap<String, ControllerV4> = HashMap()

    init {
        controllerMap["/front-controller/v4/members/new-form"] = MemberFormControllerV4()
        controllerMap["/front-controller/v4/members/save"] = MemberSaveControllerV4()
        controllerMap["/front-controller/v4/members"] = MemberListControllerV4()
    }

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        val requestURI = request.requestURI
        val controller = controllerMap[requestURI]
        if (controller == null) {
            response.status = HttpServletResponse.SC_NOT_FOUND
            return
        }
        val paramMap: Map<String, String> = createParamMap(request)
        val model: MutableMap<String, Any> = HashMap() //추가
        val viewName = controller.process(paramMap, model)
        val view: MyView = viewResolver(viewName)
        view.render(model, request, response)
    }

    private fun createParamMap(request: HttpServletRequest): Map<String, String> {
        val paramMap: MutableMap<String, String> = HashMap()
        request.parameterNames.asIterator()
            .forEachRemaining { paramName: String ->
                paramMap[paramName] = request.getParameter(paramName)
            }
        return paramMap
    }

    private fun viewResolver(viewName: String): MyView {
        return MyView("/WEB-INF/views/$viewName.jsp")
    }
}