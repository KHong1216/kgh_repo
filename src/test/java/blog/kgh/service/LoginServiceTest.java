package blog.kgh.service;

import blog.kgh.domain.entity.Member;
import blog.kgh.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@Rollback(value = false)
@SpringBootTest
class LoginServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    LoginService loginService;

    //로그인
    @Test
    public void 로그인 () throws Exception {
        //given
        Member member = Member.builder()
                .loginId("aaa")
                .password("aaa!")
                .username("springA")
                .build();
        memberRepository.save(member);
        //when
        Member loginMember = loginService.login("aaa", "aaa!");

        //then
        assertThat(loginMember).isNotNull();
    }
}