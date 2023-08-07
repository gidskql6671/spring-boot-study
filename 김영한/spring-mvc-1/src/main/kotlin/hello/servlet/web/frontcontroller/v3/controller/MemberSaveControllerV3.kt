package hello.servlet.web.frontcontroller.v3.controller

import hello.servlet.domain.member.Member
import hello.servlet.domain.member.MemberRepository
import hello.servlet.web.frontcontroller.ModelView
import hello.servlet.web.frontcontroller.v3.ControllerV3


class MemberSaveControllerV3: ControllerV3 {
    private val memberRepository = MemberRepository.getInstance()

    override fun process(paramMap: Map<String, String>): ModelView {
        val username = paramMap["username"]!!
        val age = paramMap["age"]!!.toInt()

        val member = Member(username, age)
        memberRepository.save(member)

        val mv = ModelView("save-result")
        mv.model["member"] = member
        return mv
    }
}