package jpabook.jpashop.controller

import jakarta.validation.Valid
import jpabook.jpashop.domain.Address
import jpabook.jpashop.domain.Member
import jpabook.jpashop.dto.MemberForm
import jpabook.jpashop.service.MemberService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/members")
class MemberController(
    private val memberService: MemberService
) {

    @GetMapping("/new")
    fun createForm(model: Model): String {
        model.addAttribute("memberForm", MemberForm())
        return "members/createMemberForm"
    }

    @PostMapping("/new")
    fun create(@Valid memberForm: MemberForm, result: BindingResult) : String {
        if (result.hasErrors()) {
            return "members/createMemberForm"
        }

        val address = Address(
            city = memberForm.city,
            street = memberForm.street,
            zipcode = memberForm.zipcode
        )

        val member = Member(name = memberForm.name!!, address = address)

        memberService.join(member)

        return "redirect:/"
    }

    @GetMapping("")
    fun list(model: Model): String {
        val members = memberService.findMembers()
        model.addAttribute("members", members)
        return "members/memberList"
    }
}