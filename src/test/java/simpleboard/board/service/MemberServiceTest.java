package simpleboard.board.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import simpleboard.board.domain.Member;
import simpleboard.board.repository.MemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void join() throws Exception {
        Member member = new Member();
        member.setLoginId("admin");
        member.setPassword("admin");
        member.setName("이기원");
        member.setEMail("asd@asd.com");

        Long saveId = memberService.join(member);
        assertThat(member).isSameAs(memberRepository.findOne(saveId));

    }

    @Test
    void join_DuplicateTest() throws Exception {
        Member member1 = new Member();
        member1.setLoginId("admin");
        member1.setPassword("admin");
        member1.setName("이기원");
        member1.setEMail("asd@asd.com");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setLoginId("admin");
        member2.setPassword("admin");
        member2.setName("이기원");
        member2.setEMail("asd@asd.com");

        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }

    @Test
    void login() throws Exception {
        Member member = new Member();
        member.setLoginId("admin");
        member.setPassword("admin");
        member.setName("이기원");
        member.setEMail("asd@asd.com");
        memberService.join(member);

        Long findId = memberService.login(member.getLoginId(), member.getPassword());

        assertThat(member.getMemberId()).isEqualTo(findId);
    }

    @Test
    void login_MemberIsNotExist() throws Exception {
        Member member = new Member();
        member.setLoginId("admin");
        member.setPassword("admin");
        member.setName("이기원");
        member.setEMail("asd@asd.com");
        memberService.join(member);


        assertThrows(IllegalStateException.class, () -> memberService.login(member.getLoginId(), "Ffff"));
    }


}