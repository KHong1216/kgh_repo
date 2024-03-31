package blog.kgh.domain.dto.board;

import blog.kgh.domain.dto.member.MemberDto;
import blog.kgh.domain.entity.Member;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PostDto {

    private Long id;

    private String title; //제목

    private String content; //내용

    private String member; //글 작성 member


    public PostDto(Long id,String title, String content, String member) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.member = member;
    }
}
