package blog.kgh.domain.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class MemberDto {

    private String name;

    public MemberDto(String name) {
        this.name = name;
    }
}
