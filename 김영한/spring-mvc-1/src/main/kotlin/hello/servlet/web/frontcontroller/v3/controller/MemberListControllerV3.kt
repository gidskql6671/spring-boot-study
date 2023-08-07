package hello.servlet.web.frontcontroller.v3.controller

import hello.servlet.domain.member.Member
import hello.servlet.domain.member.MemberRepository
import hello.servlet.web.frontcontroller.ModelView
import hello.servlet.web.frontcontroller.v3.ControllerV3


class MemberListControllerV3: ControllerV3 {
    private val memberRepository = MemberRepository.getInstance()

    override fun process(paramMap: Map<String, String>): ModelView {
        val members: List<Member> = memberRepository.findAll()

        val mv = ModelView("members")
        mv.model["members"] = members
        return mv
    }
}