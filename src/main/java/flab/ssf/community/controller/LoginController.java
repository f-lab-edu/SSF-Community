package flab.ssf.community.controller;

import flab.ssf.community.domain.Member;
import flab.ssf.community.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/members")
@SessionAttributes("user")
public class LoginController {

    private final MemberService memberService;

    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "members/loginForm";
    }

    @PostMapping("/login")
    public String login(MemberForm memberForm, Model model,HttpSession session) {
        Member member;
        if (session.getAttribute("user")==null) {
            member = memberService.login(memberForm.getUid(), memberForm.getPw()).get();
            model.addAttribute("user", member.getUid());
            session.setAttribute("user", member.getUid());
        } else {
            member=memberService.findOne((String)session.getAttribute("user")).get();
        }

        if (member.getRole()!='y') {
            return "login/loginResult";
        }
        return "login/loginResultAdmin";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "home";
    }

}
