package flab.ssf.community.service;

import flab.ssf.community.mapper.MemberMapper;
import flab.ssf.community.domain.Member;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }


    /**
     * 회원가입
     */
    public String join(Member member) {
//        중복검사 id, email
        validateDuplicateId(member);
        validateDuplicateEmail(member);

//        정책유효성 검사

//        validatePolicy(,member.getId());
//        member.getEmail()

        memberMapper.insert(member);
        return member.getUid();
    }

    /**
     * 아이디 정책유효성 검사
     */
//    public static boolean validatePolicy(String regex, CharSequence input) {
//
//        Pattern p = Pattern.compile(regex);
//        Matcher m = p.matcher(input);
//        return (input.length()>13 || m.matches());
//    }


    private void validateDuplicateEmail(Member member) {
        memberMapper.findByEmail(member.getEmail()).ifPresent
                (m -> {throw new IllegalStateException("이미 등록된 이메일입니다.");});
    }

    private void validateDuplicateId(Member member) {
        memberMapper.findById(member.getUid()).ifPresent
                (m -> {throw new IllegalStateException("이미 등록된 아이디입니다.");} );
    }

    /**
     * 이메일로 아이디찾기
     */
    public String findId(String email) {

        return memberMapper.findByEmail(email).orElseGet
                (() -> {throw new IllegalStateException("등록되지 않은 회원입니다.");}).getUid();

    }

    /**
     * 아이디로 비밀번호 찾기(새로운 비밀번호로 변경)
     */
    public Member findPassword(String uid, String pw) {
        Member member=memberMapper.findById(uid).orElseGet(()->{throw new IllegalStateException("등록되지 않은 회원입니다.");});
        memberMapper.update(member,pw);
        return member;
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
}
