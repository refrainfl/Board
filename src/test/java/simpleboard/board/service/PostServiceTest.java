package simpleboard.board.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import simpleboard.board.domain.Post;
import simpleboard.board.domain.PostFilter;
import simpleboard.board.repository.PostRepository;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
class PostServiceTest {

    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;

    @Test
    public void postSaveTest() {


        Post post = new Post();
        post.setTitle("Post");
        post.setFilter(PostFilter.NOTICE);
        String text = "Post or POST commonly refers to:\n" +
                "\n" +
                "Mail, the postal system, especially in Commonwealth of Nations countries\n" +
                "An Post, the Irish national postal service\n" +
                "Canada Post, Canadian postal service\n" +
                "Deutsche Post, German postal service\n" +
                "Iraqi Post, Iraqi postal service\n" +
                "Russian Post, Russian postal service\n" +
                "Hotel post, a service formerly offered by remote Swiss hotels for the carriage of mail to the nearest official post office\n" +
                "United States Postal Service or USPS\n" +
                "Parcel post, a postal service for mail that is heavier than ordinary letters\n" +
                "Post, a job or occupation\n" +
                "Post, POST, or posting may also refer to:";
        post.setContents(text);

        Long savedId = postService.savePost(post);
        assertThat(post).isSameAs(postRepository.findOne(savedId));
    }

}