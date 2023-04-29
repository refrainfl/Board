package simpleboard.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simpleboard.board.domain.Post;
import simpleboard.board.domain.PostFilter;
import simpleboard.board.repository.PostRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostService {

    @Autowired
    PostRepository postRepository;


    @Transactional
    public Long savePost(Post post) {
        post.setPostDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
        if (post.getFilter() == null) {
            post.setFilter(PostFilter.NONE);
        }
        return postRepository.save(post);
    }

    public List<Post> findAllPost() {
        return postRepository.findAll();
    }
}
