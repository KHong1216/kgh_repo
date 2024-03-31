package blog.kgh.domain.dto.board;

import lombok.Data;

@Data
public class PostResponse {

    private Long id;
    private String title;
    private String content;
<<<<<<< HEAD

    public PostResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
=======
    private int view;

    public PostResponse(Long id, String title, String content, int view) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.view = view;
>>>>>>> 72e1bcba2e927f5b6e781ed617bc9869b3635e05
    }
}
