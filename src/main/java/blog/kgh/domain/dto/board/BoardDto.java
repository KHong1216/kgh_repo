package blog.kgh.domain.dto.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {

    private String title;
    private String content;
    private int view;
}
