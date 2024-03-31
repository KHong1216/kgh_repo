package blog.kgh.web.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PostForm {


    @NotEmpty
    private String title;

    @NotEmpty
    private String content;
}
