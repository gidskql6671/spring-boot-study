package hello.servlet.web.servlet

import hello.servlet.domain.member.Member
import hello.servlet.domain.member.MemberRepository
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@WebServlet(name = "memberSaveServlet", urlPatterns = ["/servlet/members/save"])
class MemberSaveServlet: HttpServlet() {
    private val memberRepository = MemberRepository.getInstance()

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        val username = request.getParameter("username")
        val age = request.getParameter("age").toInt()

        val member = Member(username, age)
        memberRepository.save(member)

        response.contentType = "text/html"
        response.characterEncoding = "utf-8"

        val w = response.writer
        w.write(
            """<html>
            <head>
             <meta charset="UTF-8">
            </head>
            <body>
            성공
            <ul>
             <li>id=${member.id}</li>
             <li>username=${member.username}</li>
             <li>age=${member.age}</li>
            </ul>
            <a href="/index.html">메인</a>
            </body>
            </html>"""
        )
    }
}