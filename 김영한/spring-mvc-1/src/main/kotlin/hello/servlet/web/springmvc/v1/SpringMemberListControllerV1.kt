package hello.servlet.web.springmvc.v1

import hello.servlet.domain.member.Member
import hello.servlet.domain.member.MemberRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView


@Controller
class SpringMemberListControllerV1 {
    private val memberRepository = MemberRepository.getInstance()

    @RequestMapping("/springmvc/v1/members")
    fun process(): ModelAndView {
        val members: List<Member> = memberRepository.findAll()

        val mv = ModelAndView("members")
        mv.addObject("members", members)
        return mv
    }
}