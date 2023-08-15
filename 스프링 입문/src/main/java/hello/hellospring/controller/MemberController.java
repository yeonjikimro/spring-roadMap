package hello.hellospring.controller;

import hello.hellospring.Service.MemberService;
import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 주석 생성 시 스프링 컨테이너에 MemberController를 넣어 스프링이 관리함.(스프링 Bean이 관리됨)
public class MemberController {

    /* 아래와 같이 new로 새로 계속 만들어 여러개의 인스턴스를 만들어 사용하는 것 보다
    하나의 인스턴스를 사용해 공유하는 것이 좋다.
       private final MemberService memberService = new MemberService();
     */
    private final MemberService memberService;

    @Autowired // MemberService를 스프링이 스프링컨테이너에 있는 memberService와 연결을 시켜준다.(DI)
    // (memberService에도 @Service 가 있어야 스프링컨테이너가 인식할 수 있다)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
