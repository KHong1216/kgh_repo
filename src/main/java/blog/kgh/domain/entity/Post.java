package blog.kgh.domain.entity;

import blog.kgh.auditing.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 글 작성 엔티티
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
@RequiredArgsConstructor
@Getter
@Setter
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id; //글 작성 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member createMember; //글 작성 member


    private String title; //제목

    private String content; //내용

    private int view;

    @Builder
    public Post(Member member, String title, String content, int view) {
        this.createMember = member;
        this.title = title;
        this.content = content;
        this.view = view;
    }
}
