package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    // 모델뷰의 이름만 '논리이름'으로 세팅해줌
    @Override
    public ModelView proceess(Map<String, String> paraMap) {
        return new ModelView("new-form");
    }
}
