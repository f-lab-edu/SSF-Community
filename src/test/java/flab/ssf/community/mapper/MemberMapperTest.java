package flab.ssf.community.mapper;

import flab.ssf.community.domain.Member;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
//@ContextConfiguration(locations = "/mybatis/mybatis-config.xml")
@MybatisTest
//    @SpringBootTest
class MemberMapperTest {
    @Autowired
    public MemberMapper memberMapper;

//    @Autowired
//    public Member member;
    @Test
    void insert() {
        Member member = new Member();
        member.setUid("qewqwqe");
        member.setPhone("01021111245");
        member.setAddress("해운대");
        member.setDate(new Date());
        member.setEmail("dasda@naver.com");
        member.setEnabled('y');
        member.setGrade('y');
        member.setPw("sk12244");
        member.setRole('y');
        member.setName("강욱몬");

        memberMapper.insert(member);
        List<Member> list = memberMapper.findAll();
        System.out.println("결과" + list);

    }

    @Test
    void update() {
        Member member = new Member();
        member.setUid("qewqwqe");
        member.setPhone("01021111245");
        member.setAddress("해운대");
        member.setDate(new Date());
        member.setEmail("dasda@naver.com");
        member.setEnabled('y');
        member.setGrade('y');
        member.setPw("sk12244");
        member.setRole('y');
        member.setName("강욱몬");

        memberMapper.insert(member);
        List<Member> list = memberMapper.findAll();
        System.out.println("결과:"+ list);
        memberMapper.update(member, "123456789");
        List<Member> list1 = memberMapper.findAll();
        System.out.println("변경결과:"+list1);
    }

    @Test
    void delete() {
        Member member= new Member();
        member.setUid("qewqwqe");
        member.setPhone("01021111245");
        member.setAddress("해운대");
        member.setDate(new Date());
        member.setEmail("dasda@naver.com");
        member.setEnabled('y');
        member.setGrade('y');
        member.setPw("sk12244");
        member.setRole('y');
        member.setName("강욱몬");

        Member member2= new Member();
        member2.setUid("wwww");
        member2.setPhone("0688245");
        member2.setAddress("센텀");
        member2.setDate(new Date());
        member2.setEmail("wqqwwq@naver.com");
        member2.setEnabled('y');
        member2.setGrade('y');
        member2.setPw("2545");
        member2.setRole('y');
        member2.setName("몬스터");

        memberMapper.insert(member);
        memberMapper.insert(member2);
        memberMapper.delete(member);

        System.out.println(memberMapper.findAll());

    }

    @Test
    void findById() {
        Member member = new Member();
        member.setUid("qewqwqe");
        member.setPhone("01021111245");
        member.setAddress("해운대");
        member.setDate(new Date());
        member.setEmail("dasda@naver.com");
        member.setEnabled('y');
        member.setGrade('y');
        member.setPw("sk12244");
        member.setRole('y');
        member.setName("강욱몬");

        Member member2= new Member();
        member2.setUid("wwww");
        member2.setPhone("0688245");
        member2.setAddress("센텀");
        member2.setDate(new Date());
        member2.setEmail("wqqwwq@naver.com");
        member2.setEnabled('y');
        member2.setGrade('y');
        member2.setPw("2545");
        member2.setRole('y');
        member2.setName("몬스터");

        memberMapper.insert(member);
        memberMapper.insert(member2);
        System.out.println(memberMapper.findAll());


        System.out.println(memberMapper.findById("wwww"));
    }

    @Test
    void findByEmail() {
        Member member = new Member();
        member.setUid("qewqwqe");
        member.setPhone("01021111245");
        member.setAddress("해운대");
        member.setDate(new Date());
        member.setEmail("dasda@naver.com");
        member.setEnabled('y');
        member.setGrade('y');
        member.setPw("sk12244");
        member.setRole('y');
        member.setName("강욱몬");

        Member member2= new Member();
        member2.setUid("wwww");
        member2.setPhone("0688245");
        member2.setAddress("센텀");
        member2.setDate(new Date());
        member2.setEmail("wqqwwq@naver.com");
        member2.setEnabled('y');
        member2.setGrade('y');
        member2.setPw("2545");
        member2.setRole('y');
        member2.setName("몬스터");

        memberMapper.insert(member);
        memberMapper.insert(member2);

        System.out.println(memberMapper.findByEmail("wqqwwq@naver.com"));
    }

    @Test
    void login() {
        Member member = new Member();
        member.setUid("wwww");
        member.setPhone("0688245");
        member.setAddress("센텀");
        member.setDate(new Date());
        member.setEmail("wqqwwq@naver.com");
        member.setEnabled('y');
        member.setGrade('y');
        member.setPw("2545");
        member.setRole('y');
        member.setName("몬스터");

        memberMapper.insert(member);
        Member memberTest=memberMapper.login("wwww", "2545").get();
        assertThat(member).isEqualTo(memberTest);



    }

    @Test
    void selectEnabled() {
        Member member = new Member();
        member.setUid("wwww");
        member.setPhone("0688245");
        member.setAddress("센텀");
        member.setDate(new Date());
        member.setEmail("wqqwwq@naver.com");
        member.setEnabled('y');
        member.setGrade('y');
        member.setPw("2545");
        member.setRole('y');
        member.setName("몬스터");

        Member member2 = new Member();
        member2.setUid("dadsa");
        member2.setPhone("0688245");
        member2.setAddress("센텀");
        member2.setDate(new Date());
        member2.setEmail("wqqwwq@naver.com");
        member2.setEnabled('n');
        member2.setGrade('y');
        member2.setPw("2545");
        member2.setRole('y');
        member2.setName("몬스터");



        memberMapper.insert(member);
        memberMapper.insert(member2);

        List<Member> selectList=memberMapper.selectEnabled();
        assertThat(selectList.size()).isEqualTo(2);

    }



}