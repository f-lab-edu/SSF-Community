package flab.ssf.community.controller;

import flab.ssf.community.domain.Member;
import flab.ssf.community.service.MemberService;
import flab.ssf.community.utils.ScriptUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/login")
@SessionAttributes("user")
public class LoginController {

    private final MemberService memberService;

    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public String loginForm() {
        return "members/loginForm";
    }

    @PostMapping
    public String login(MemberForm memberForm, Model model,HttpSession session,HttpServletResponse response) {
        Member member;
        if (session.getAttribute("user")==null) {
            try {
                member = memberService.login(memberForm.getUid(), memberForm.getPw()).get();
                ScriptUtils.alert(response,"로그인하셨습니다.");
                model.addAttribute("user", member.getUid());
                session.setAttribute("user", member.getUid());
                if (member.getRole()!='y') {
                    return "login/loginResult";
                }
                return "login/loginResultAdmin";
            } catch (NoSuchElementException ne) {
                try {
                    ScriptUtils.alert(response, "존재하지않는 회원정보입니다.");
                } catch (Exception ex) {
                    ex.getMessage();
                } finally {
                    return "/";
                }
            } catch (Exception ex) {
                ex.getMessage();
                return "/";
            }

        } else {
            member=memberService.findOne((String)session.getAttribute("user")).get();
            model.addAttribute("user", member.getUid());
            if (member.getRole()!='y') {
                return "login/loginResult";
            }
            return "login/loginResultAdmin";
        }


    }



}
