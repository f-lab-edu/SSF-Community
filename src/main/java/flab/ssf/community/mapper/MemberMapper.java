package flab.ssf.community.mapper;

import flab.ssf.community.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface MemberMapper {

    // 회원가입
    void insert(Member member);

    //삭제
    void delete(Member member);
    //회원 전체 정보 출력(탈퇴회원 포함)

    List<Member> findAll();
    //UID로 회원찾기

    Optional<Member> findById(String uid);
    //이메일로 회원찾기

    Optional<Member> findByEmail(String email);

    //비밀번호 수정
    void update(@Param("member")Member member, @Param("dpw")String pw);

    //로그인
    Optional<Member> login(@Param("uid")String uid, @Param("pw")String pw);

    //가입 회원 정보 출력
    List<Member> selectEnabled();


}
