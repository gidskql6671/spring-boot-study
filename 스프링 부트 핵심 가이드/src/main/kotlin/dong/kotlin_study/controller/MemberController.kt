package dong.kotlin_study.controller

import dong.kotlin_study.domain.Member
import dong.kotlin_study.dto.MemberForm
import dong.kotlin_study.service.MemberService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class MemberController(val memberService: MemberService) {

    @GetMapping("/members/new")
    fun createForm(): String = "members/createMemberForm"

    @PostMapping("/members/new")
    fun create(form: MemberForm): String {
        val member = Member(name = form.name)

        memberService.join(member);

        return "redirect:/"
    }

    @GetMapping("/members")
    fun list(model: Model): String {
        val members = memberService.findMembers()
        model.addAttribute("members", members)
        return "members/memberList"
    }
}