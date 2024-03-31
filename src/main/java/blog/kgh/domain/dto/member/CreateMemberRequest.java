package blog.kgh.domain.dto.member;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreateMemberRequest {
    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;

    @NotEmpty
    private String username;
}
