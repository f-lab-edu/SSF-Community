package flab.ssf.community.controller;

import flab.ssf.community.utils.ScriptUtils;
import flab.ssf.community.domain.Member;
import flab.ssf.community.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/new")
    public void create(MemberForm memberForm, HttpServletResponse response) {
        Member member = new Member();
        if (memberForm.getAddress() == "") {
            try {
                ScriptUtils.alertAndBackPage(response, "주소를 입력하십시오.");
            } catch (IOException ie) {
                ie.getMessage();
            }
        } else if (memberForm.getName() == "") {
            try {
                ScriptUtils.alertAndBackPage(response, "성함을 입력하십시오.");
            } catch (IOException ie) {
                ie.getMessage();
            }
        } else {
            member.setAddress(memberForm.getAddress());
            member.setEmail(memberForm.getEmail());
            member.setEnabled('Y');
            member.setUid(memberForm.getUid());
            member.setGrade('N');
            member.setName(memberForm.getName());
            member.setRole('N');
            member.setPw(memberForm.getPw());
            member.setPhone(memberForm.getPhone());

            try {
                memberService.join(member);
                ScriptUtils.alertAndMovePage(response, "회원가입에 성공하였습니다.", "/");
            } catch (IllegalStateException il) {
                try {
                    ScriptUtils.alertAndBackPage(response, il.getMessage());
                } catch (Exception ex) {
                    ex.getMessage();
                }
            } catch (Exception ex) {
                ex.getMessage();
            }
        }

    }

    @GetMapping
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    @DeleteMapping
    public void deleteMember(MemberForm memberForm, HttpServletResponse response) {
        Member member = new Member();
        member.setUid(memberForm.getUid());
        memberService.deleteMember(member);
        try {
            ScriptUtils.alertAndMovePage(response, "회원탈퇴가 완료되었습니다.", "/");
        } catch (IOException io) {
            io.getMessage();
        }
    }

    @GetMapping("/findId")
    public String createFormfindId() {
        return "members/insertEmail";
    }

    @PostMapping("/findId")
    public String findId(MemberForm memberForm, Model model) {
        model.addAttribute("id", memberService.findId(memberForm.getEmail()));
        return "members/showId";
    }


    @GetMapping("/findPw")
    public String createFormfindPw() {
        return "members/insertId";
    }

    @PostMapping("/findPw")
    public String findPw(MemberForm memberForm) {

        memberService.findPassword(memberForm.getUid(), memberForm.getPw());
        return "home";
    }

    @GetMapping("/updateMember")
    public String loadMemberInformation(HttpSession session, Model model) {
        Member member = memberService.findOne(session.getAttribute("user").toString()).get();
        model.addAttribute("member", member);
        return "members/amendMemberInformation";
    }

    @PutMapping("/updateMember")
    public void ammendMemberInformation(MemberForm memberForm, HttpServletResponse response) {
        try {
            if (memberForm.getName() == "") {
                ScriptUtils.alertAndBackPage(response, "이름을 입력해주세요.");
            } else if (memberForm.getAddress() == "") {
                ScriptUtils.alertAndBackPage(response, "주소를 입력해주세요.");
            } else {
                Member member = new Member();
                member.setUid(memberForm.getUid());

                try {
                    memberService.updateMember(member, memberForm.getEmail(), memberForm.getPhone(), memberForm.getAddress()
                            , memberForm.getName());
                    ScriptUtils.alertAndMovePagePost(response, "회원정보 수정이 완료되었습니다.", "/login");
                } catch (IllegalStateException il) {
                    try {
                        ScriptUtils.alertAndBackPage(response, il.getMessage());
                    } catch (Exception ex) {
                        ex.getMessage();
                    }
                } catch (Exception ex) {
                    ex.getMessage();
                }
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
