package blog.kgh.service;

import blog.kgh.domain.entity.Member;
import blog.kgh.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    //로그인
    public Member login(String loginId, String password) {
        if (memberRepository.findByLoginId(loginId) == null) {
            return null;
        }
        String checkPassword = memberRepository.findByLoginId(loginId).getPassword();
        if (checkPassword.equals(password)) {
            return memberRepository.findByLoginId(loginId);
        }else
        {
            return null;

        }
    }
}
