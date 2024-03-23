package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    // 모델에서 getParameter하듯이 값들 꺼내와서 model 이름 논리이름으로 세팅하고,
    // 레포지토리에 멤버 저장 후 모델에 member 데이터 추가
    @Override
    public ModelView proceess(Map<String, String> paraMap) {
        String username = paraMap.get("username");
        int age = Integer.parseInt(paraMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member",member);
        return mv;
    }
}
