package blog.kgh.api;

import blog.kgh.domain.dto.board.CreatePostRequest;
import blog.kgh.domain.dto.board.PostDto;
import blog.kgh.domain.dto.board.PostResponse;
import blog.kgh.domain.dto.board.UpdatePostRequest;
import blog.kgh.domain.entity.Member;
import blog.kgh.domain.entity.Post;
import blog.kgh.service.MemberService;
import blog.kgh.service.PostService;
import blog.kgh.web.login.Login;
import io.swagger.annotations.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/api/posts")
    public Result posts() {
        List<Post> findPosts = postService.findPosts();
        List<PostDto> collect = findPosts.stream().map(m -> new PostDto(m.getId(), m.getTitle(), m.getContent(), m.getCreateMember().getUsername())).collect(Collectors.toList());
        return new Result(collect);
    }

    @PostMapping("/api/posts")
    public PostResponse savePost(@RequestBody @Valid CreatePostRequest request) {
        Member member = memberService.findOne(request.getLoginId());

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .member(member)
                .build();

        Long id = postService.createPost(post, member);
<<<<<<< HEAD
        return new PostResponse(id, post.getTitle(), post.getTitle());
=======
        return new PostResponse(id, post.getTitle(), post.getTitle(), post.getView());
>>>>>>> 72e1bcba2e927f5b6e781ed617bc9869b3635e05
    }

    @PutMapping("/api/{id}/posts")
    public PostResponse updatePost(@PathVariable("id") Long id, @RequestBody @Valid UpdatePostRequest request) {
        postService.updatePost(id, request.getTitle(), request.getContent());
        Post findPost = postService.findOne(id);
<<<<<<< HEAD
        return new PostResponse(findPost.getId(), findPost.getTitle(), findPost.getContent());
=======
        return new PostResponse(findPost.getId(), findPost.getTitle(), findPost.getContent(), findPost.getView());
>>>>>>> 72e1bcba2e927f5b6e781ed617bc9869b3635e05
    }

    @DeleteMapping("/api/{id}/posts")
    public PostResponse deletePost(@PathVariable("id") Long id) {
        Post findPost = postService.findOne(id);
        postService.deletePost(findPost);
<<<<<<< HEAD
        return new PostResponse(findPost.getId(), findPost.getTitle(), findPost.getContent());
=======
        return new PostResponse(findPost.getId(), findPost.getTitle(), findPost.getContent(), findPost.getView());
>>>>>>> 72e1bcba2e927f5b6e781ed617bc9869b3635e05
    }


    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

}
