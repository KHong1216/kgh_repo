package blog.kgh.web.login;

import blog.kgh.domain.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SessionManagerTest {

    @Autowired
    SessionManager sessionManager;

    @Test
    public void sessionTest() throws Exception {

        MockHttpServletResponse response = new MockHttpServletResponse();
        Member member = Member.builder().loginId("1").password("1").username("2").build();
        sessionManager.createSession(member, response);


        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        Object result = sessionManager.getSession(request);
        assertThat(result).isEqualTo(member);

        sessionManager.expire(request);
        Object expired = sessionManager.getSession(request);
        assertThat(expired).isNull();
    }

}