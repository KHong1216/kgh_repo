package blog.kgh.domain.dto.board;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreatePostRequest {

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    private String loginId;
}
