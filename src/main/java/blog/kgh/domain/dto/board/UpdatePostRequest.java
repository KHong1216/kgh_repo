package blog.kgh.domain.dto.board;

import lombok.Data;

@Data
public class UpdatePostRequest {

    private String title;

    private String content;
}
