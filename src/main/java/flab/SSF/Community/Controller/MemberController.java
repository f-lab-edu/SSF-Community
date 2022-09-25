package flab.SSF.Community.Controller;

import flab.SSF.Community.domain.Member;
import flab.SSF.Community.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm memberForm) {
        Member member = new Member();
        member.setAddress(memberForm.getAddress());
        member.setEmail(memberForm.getEmail());
        member.setEnabled('Y');
        member.setUid(memberForm.getUid());
        member.setGrade('N');
        member.setName(memberForm.getName());
        member.setRole('N');
        member.setPw(memberForm.getPw());
        member.setPhone(memberForm.getPhone());

        memberService.join(member);

        return "/home";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    @GetMapping("/members/findId")
    public String createFormfindId() {
        return "members/insertEmail";
    }

    @PostMapping("/members/findId")
    public String findId(MemberForm memberForm, Model model) {
        model.addAttribute("id",memberService.findId(memberForm.getEmail()));
        return "members/showId";
    }




    @GetMapping("/members/findPw")
    public String createFormfindPw() {
        return "members/insertId";
    }

    @PostMapping("/members/findPw")
    public String findPw(MemberForm memberForm) {

       memberService.findPassword(memberForm.getUid(),memberForm.getPw());
       return "/home";
    }

}
