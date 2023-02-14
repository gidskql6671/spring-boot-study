package hello.servlet.web.servletmvc

import hello.servlet.domain.member.Member
import hello.servlet.domain.member.MemberRepository
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@WebServlet(name = "mvcMemberListServlet", urlPatterns = ["/servlet-mvc/members"])
class MvcMemberListServlet: HttpServlet() {
    private val memberRepository = MemberRepository.getInstance()

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        val members: List<Member> = memberRepository.findAll()
        request.setAttribute("members", members)

        val viewPath = "/WEB-INF/views/members.jsp"
        val dispatcher = request.getRequestDispatcher(viewPath)
        dispatcher.forward(request, response)
    }
}