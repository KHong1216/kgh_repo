package blog.kgh.repository;

import blog.kgh.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByLoginId(String loginId);
    Member findByPassword(String password);
    Member findByUsername(String username);
}
