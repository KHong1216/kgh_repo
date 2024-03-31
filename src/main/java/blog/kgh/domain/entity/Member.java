package blog.kgh.domain.entity;

import blog.kgh.auditing.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;


@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
public class Member extends BaseEntity {
    /**
     * id - db에 저장될 아이디
     * loginId - 로그인 아이디
     * pw - 비밀번호
     * name - 이름
     * gender - 성별
     * address - 배송에 필요한 주소
     */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String loginId;

    private String password;

    private String username;

    @OneToMany(mappedBy = "createMember", cascade = CascadeType.ALL)
    private List<Post> posts;

    @Builder
    public Member(String loginId, String password, String username, List<Post> posts) {
        this.loginId = loginId;
        this.password = password;
        this.username = username;
        this.posts = posts;
    }
}
