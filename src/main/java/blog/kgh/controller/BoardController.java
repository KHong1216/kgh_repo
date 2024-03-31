package blog.kgh.controller;

import blog.kgh.domain.dto.board.PostDto;
import blog.kgh.domain.dto.board.PostResponse;
import blog.kgh.domain.entity.Member;
import blog.kgh.domain.entity.Post;
import blog.kgh.repository.PostRepository;
import blog.kgh.service.PostService;
import blog.kgh.web.form.PostForm;
import blog.kgh.web.login.Login;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final PostService postService;

    //목록
    @GetMapping("/list")
    public String boardList(@Login Member member, @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        Page<Post> posts = postService.paging(pageable);

        //페이징
        int nowPage = posts.getPageable().getPageNumber() + 1; //1페이지 시작
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 9, posts.getTotalPages());

        model.addAttribute("posts", posts);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("loginMember", member);

        return "board/board";
    }

    //글 작성
    @GetMapping("/add")
    public String createPost(@ModelAttribute("PostForm") PostForm postForm) {

        return "posts/addPost";
    }

    @PostMapping("/add")
    public String savePost(@Login Member loginMember, @Valid @ModelAttribute("PostForm") Post post, BindingResult result) {
        if (result.hasErrors()) {
            return "posts/addPost";
        }

        Post newPost = Post.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .member(loginMember)
                .build();
        postService.createPost(newPost, loginMember);
        return "redirect:/board/list";
    }

    @GetMapping("/{id}/edit")
    public String editPost(@Login Member loginMember, @ModelAttribute("PostForm") Post post, @PathVariable(value = "id") Long id, Model model) {
        Post showPost = postService.findOne(id);

        if (showPost.getCreateMember().equals(loginMember) ){
            log.info("currentMember = {} createMember={}", loginMember, showPost.getCreateMember());
            return "redirect:/board/list";
        }

        Post postForm = Post.builder().title(showPost.getTitle()).content(showPost.getContent()).build();
        model.addAttribute("form",postForm);

        return "posts/showPost";
    }

    @PostMapping("/{id}/edit")
    public String updatePost(@Valid @ModelAttribute("form") Post post, BindingResult result, @PathVariable(value = "id") Long id, Model model) {
        if (result.hasErrors()) {
            return "posts/showPost";
        }

        Post findPost = postService.findOne(post.getId());
        postService.updatePost(id, post.getTitle(), post.getContent());
        postService.addView(id);

        return "redirect:/board/list";
    }

    @GetMapping("/{id}")
    public String showPost(@PathVariable(value = "id") Long id, @ModelAttribute Post post) {

        postService.addView(id);
        return "posts/showPost";
    }
}
