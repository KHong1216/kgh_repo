package blog.kgh.service;

import blog.kgh.domain.entity.Member;
import blog.kgh.domain.entity.Post;
import blog.kgh.repository.MemberRepository;
import blog.kgh.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)

class PostServiceTest {

    @Autowired
    PostRepository postRepository;
    @Autowired
    PostService postService;


    @Test
    public void 작성() throws Exception {
        //given
        Member member = Member.builder()
                .loginId("1")
                .password("admin")
                .username("admin")
                .build();

        Post post = Post.builder()
                .title("제목 1")
                .content("내용 1")
                .member(member)
                .build();
        //when
        Post savedPost = postRepository.save(post);

        //then
        assertThat(savedPost.getTitle()).isEqualTo("제목 1");
    }

    @Test
    public void 페이징() throws Exception {
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "id");
    }
}