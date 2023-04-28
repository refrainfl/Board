package simpleboard.board.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import simpleboard.board.domain.Post;
import simpleboard.board.domain.PostFilter;


import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    @Transactional
    @Rollback
    public void postSaveTest() {
        Post post = new Post();
        post.setTitle("asd");
        post.setContents("hello");

        Long savedId = postRepository.save(post);
        Post findPost = postRepository.findOne(savedId);
        assertThat(post).isSameAs(findPost);
    }

    @Test
    @Transactional
    @Rollback
    public void postFindByTitleTest() {
        Post post1 = new Post();
        post1.setTitle("asd");
        post1.setContents("hello");
        postRepository.save(post1);

        Post post2 = new Post();
        post2.setTitle("asdasd");
        post2.setContents("hello");
        postRepository.save(post2);

        Post post3 = new Post();
        post3.setTitle("ffff");
        post3.setContents("hello");
        postRepository.save(post3);

        assertThat(postRepository.findByTitle("asd").size()).isEqualTo(2);
        assertThat(postRepository.findByTitle("f").size()).isEqualTo(1);
    }


    @Test
    @Transactional
    @Rollback
    public void postFindByContentsTest() {
        Post post1 = new Post();
        post1.setTitle("asd");
        post1.setContents("hello");
        post1.setFilter(PostFilter.NONE);
        postRepository.save(post1);

        Post post2 = new Post();
        post2.setTitle("asdasd");
        post2.setContents("hello");
        post2.setFilter(PostFilter.NOTICE);
        postRepository.save(post2);

        Post post3 = new Post();
        post3.setTitle("ffff");
        post3.setContents("hello1");
        post3.setFilter(PostFilter.NOTICE);
        postRepository.save(post3);

        assertThat(postRepository.findByContents("hello").size()).isEqualTo(3);
        assertThat(postRepository.findByContents("1").size()).isEqualTo(1);
    }

    @Test
    @Transactional
    @Rollback
    public void postFindByFilterTest() {
        Post post1 = new Post();
        post1.setTitle("asd");
        post1.setContents("hello");
        post1.setFilter(PostFilter.NONE);
        postRepository.save(post1);

        Post post2 = new Post();
        post2.setTitle("asdasd");
        post2.setContents("hello");
        post2.setFilter(PostFilter.NOTICE);
        postRepository.save(post2);

        Post post3 = new Post();
        post3.setTitle("ffff");
        post3.setContents("hello1");
        post3.setFilter(PostFilter.NOTICE);
        postRepository.save(post3);

        assertThat(postRepository.findByFilter(PostFilter.NONE).size()).isEqualTo(1);
        assertThat(postRepository.findByFilter(PostFilter.NOTICE).size()).isEqualTo(2);
    }
}