package hello.servlet.web.frontcontroller.v3

import hello.servlet.web.frontcontroller.MyView
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@WebServlet(name = "frontControllerServletV3", urlPatterns = ["/front-controller/v3/*"])
class FrontControllerServletV3: HttpServlet() {
    private val controllerMap: MutableMap<String, ControllerV3> = HashMap()

    init {
        controllerMap["/front-controller/v3/members/new-form"] = MemberFormControllerV3()
        controllerMap["/front-controller/v3/members/save"] = MemberSaveControllerV3()
        controllerMap["/front-controller/v3/members"] = MemberListControllerV3()
    }

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        val requestURI = request.requestURI
        val controller = controllerMap[requestURI]
        if (controller == null) {
            response.status = HttpServletResponse.SC_NOT_FOUND
            return
        }
        val paramMap: Map<String, String> = createParamMap(request)
        val mv = controller.process(paramMap)
        val viewName = mv.viewName
        val view: MyView = viewResolver(viewName)
        view.render(mv.model, request, response)
    }

    private fun createParamMap(request: HttpServletRequest): Map<String, String> {
        val paramMap: MutableMap<String, String> = HashMap()
        request.parameterNames.asIterator()
            .forEachRemaining { paramName: String ->
                paramMap[paramName] = request.getParameter(paramName)
            }
        return paramMap
    }

    private fun viewResolver(viewName: String): MyView = MyView("/WEB-INF/views/$viewName.jsp")
}