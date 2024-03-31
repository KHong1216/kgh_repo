package blog.kgh.domain.dto.member;

import lombok.Data;

@Data
public class UpdateMemberResponse {
    private Long id;

    private String username;

    public UpdateMemberResponse(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
