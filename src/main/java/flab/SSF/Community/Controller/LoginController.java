package flab.SSF.Community.Controller;

import flab.SSF.Community.Mapper.MemberMapper;
import flab.SSF.Community.domain.Member;
import flab.SSF.Community.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("user")
public class LoginController {

    private final MemberService memberService;

    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/login")
    public String loginForm() {
        return "members/loginForm";
    }

    @PostMapping("/members/login")
    public String login(MemberForm memberForm, Model model,HttpServletResponse response) {
       Member member=memberService.login(memberForm.getUid(),memberForm.getPw()).get();
       model.addAttribute("user",member.getUid());
//        setCookie(response, member);

        if (member.getRole()!='y') {
            return "login/loginResult";
        }
        return "login/loginResultAdmin";
    }

    @GetMapping("/members/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "home";
    }

//    public HttpServletResponse setCookie(HttpServletResponse response,Member member) {
//        Cookie cookie = new Cookie("username",member.getUid());
//        cookie.setMaxAge(7*24*60*60);
//        response.addCookie(cookie);
//        return response;
//    }
}
