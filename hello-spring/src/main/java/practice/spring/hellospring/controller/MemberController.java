package practice.spring.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import practice.spring.hellospring.domain.Member;
import practice.spring.hellospring.service.MemberService;

import java.util.List;

@Controller
public class MemberController {

    //0. MemberService 이용하기 위한 생성자
    private final MemberService memberService;

    //Controller에 Service 삽입 (injection)
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        System.out.println("memberService = " + memberService.getClass());

    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName()); //form에서 이름 get해와 member에 세팅

        memberService.join(member); //join - 회원정보 DB에 저장하는 메서드

        return "redirect:/"; //작업 마친 이후 홈 화면으로 돌려보냄
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
        //해당하는 뷰 템플릿(memberList)에 model에 담아 쉽게 옮기기 위함
    }
}

