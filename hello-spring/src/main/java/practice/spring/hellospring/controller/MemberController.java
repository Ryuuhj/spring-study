package practice.spring.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import practice.spring.hellospring.service.MemberService;

@Controller
public class MemberController {

    //0. MemberService 이용하기 위한 생성자
    private final MemberService memberService;

    //Controller에 Service 삽입
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
