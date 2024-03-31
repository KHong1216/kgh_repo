package blog.kgh.service;

import blog.kgh.domain.dto.board.PostResponse;
import blog.kgh.domain.entity.Member;
import blog.kgh.domain.entity.Post;
import blog.kgh.repository.PostRepository;
import blog.kgh.web.form.PostForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PostService {

    private final PostRepository postRepository;


    @Transactional
    public Long createPost(Post post, Member loginMember) {
        Post newPost = Post.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .member(loginMember)
                .build();

        postRepository.save(newPost);
        return newPost.getId();
    }


    //글 수정
    @Transactional
    public void updatePost(Long id,String title, String content) {
        Post findPost = postRepository.findOneById(id);
        findPost.setTitle(title);
        findPost.setContent(content);
    }

    //글 삭제
    @Transactional
    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    //전체 조회
    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    //단건 조회
    public Post findOne(Long id) {
        return postRepository.findOneById(id);
    }

    //view 증가
    @Transactional
    public void addView(Long id) {
        Post findPost = postRepository.findOneById(id);
        int addView = 1;
        findPost.setView(findPost.getView() + addView);
    }

    //페이징
    public Page<Post> paging(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}