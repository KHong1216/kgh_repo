package blog.kgh.service;

import blog.kgh.domain.entity.Member;
import blog.kgh.repository.MemberRepository;
import blog.kgh.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    //회원가입
    @Transactional
    public Long join(Member member) {
        Member createMember = Member.builder()
                .loginId(member.getLoginId())
                .password(member.getPassword())
                .username(member.getUsername())
                .build();
        memberRepository.save(createMember);
        return createMember.getId();
    }

    //회원수정
    @Transactional
    public void update(String id, String username) {
        Member member = memberRepository.findByLoginId(id);
        member.setUsername(username);
    }

    public boolean validateDuplicateMember(Member member) {

        Member findLoginId = memberRepository.findByLoginId(member.getLoginId());
        Member findUsername = memberRepository.findByUsername(member.getUsername());
        if (findLoginId != null || findUsername != null) {
            return false;
        }
        return true;
    }

    //전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //단건 조회
    public Member findOne(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }
}
