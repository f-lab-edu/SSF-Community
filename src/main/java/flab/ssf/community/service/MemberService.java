package flab.ssf.community.service;

import flab.ssf.community.mapper.MemberMapper;
import flab.ssf.community.domain.Member;
import flab.ssf.community.utils.ScriptUtils;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.scanner.ScannerImpl;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class MemberService {

    private final MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }


    /**
     * 회원가입
     */
    public String join(Member member) throws IllegalStateException {
//        유효성검사 id, password, email, phone

        uidValidatePolicy(member.getUid());
        pwValidatePolicy(member.getPw());
        EmailValidatePolicy(member.getEmail());
        PhoneValidatePolicy(member.getPhone());
//        중복검사 id, email
        validateDuplicateId(member);
        validateDuplicateEmail(member);


        memberMapper.insert(member);
        return member.getUid();
    }

    /**
     * 아이디 정책유효성 검사
     */
    public static void uidValidatePolicy(String input) {

        String regex = "^[a-zA-Z0-9]{6,13}$";

        if (!Pattern.matches(regex, input)) {
            throw new IllegalStateException("잘못된 아이디 형식입니다.");
        } else if (input.equals("admin")) {
            throw new IllegalStateException("admin은 사용금지 아이디입니다.");
        }
    }

    /**
     * 비밀번호 정책유효성 검사
     */
    public static void pwValidatePolicy(String input) {

        String regex = "^.*(?=^.{8,16}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$";

        if (!Pattern.matches(regex, input)) {
            throw new IllegalStateException("잘못된 비밀번호 형식입니다.");
        }
    }

    /**
     * 이메일 정책유효성 검사
     */
    public static void EmailValidatePolicy(String input) {

        String regex = "\\w+@\\w+\\.\\w+(\\.\\w+)?";

        if (!Pattern.matches(regex, input)) {
            throw new IllegalStateException("잘못된 이메일 형식입니다.");
        }
    }

    /**
     * 폰번호 정책유효성 검사
     */
    public static void PhoneValidatePolicy(String input) {

        String regex = "^01(?:0|1|[6-9])\\d{8}";

        if (!Pattern.matches(regex, input)) {
            throw new IllegalStateException("잘못된 핸드폰번호 형식입니다.");
        }
    }


    /**
     * 이메일 중복검사
     */
    private void validateDuplicateEmail(Member member) {
        memberMapper.findByEmail(member.getEmail()).ifPresent
                (m -> {
                    throw new IllegalStateException("이미 등록된 이메일입니다.");
                });
    }

    /**
     * 아이디 중복검사
     */
    private void validateDuplicateId(Member member) {
        memberMapper.findById(member.getUid()).ifPresent
                (m -> {
                    throw new IllegalStateException("이미 등록된 아이디입니다.");
                });
    }

    /**
     * 이메일로 아이디찾기
     */
    public String findId(String email) {

        return memberMapper.findByEmail(email).orElseGet
                (() -> {
                    throw new IllegalStateException("등록되지 않은 회원입니다.");
                }).getUid();

    }

    /**
     * 아이디로 비밀번호 찾기(새로운 비밀번호로 변경)
     */
    public void findPassword(String uid, String pw) {
        Member member = memberMapper.findById(uid).orElseGet(() -> {
            throw new IllegalStateException("등록되지 않은 회원입니다.");
        });
        memberMapper.update(member, pw);
    }


    /**
     * 전체 회원 조회하기
     */
    public List<Member> findMembers() {
        return memberMapper.findAll();
    }

    /**
     * 회원한명조회하기(uid로)
     */
    public Optional<Member> findOne(String memberId) {
        return memberMapper.findById(memberId);
    }


    /**
     * 로그인하기(해당 멤버객체 내뱉기)
     */
    public Optional<Member> login(String uid, String pw) {
        return memberMapper.login(uid, pw);
    }

    /**
     * 회원전체조회(탈퇴멤버 제외)
     */
    public List<Member> findMembersEnabled() {
        return memberMapper.selectEnabled();
    }


    public void updateMember(Member member, String email, String phone, String address, String name)
            throws IllegalStateException {

            EmailValidatePolicy(email);
            validateDuplicateEmail(member);
            PhoneValidatePolicy(phone);
            memberMapper.ammendInformation(member, email, phone, address, name);
        

    }



    public void deleteMember(Member member) {
        memberMapper.delete(member);
    }

}
