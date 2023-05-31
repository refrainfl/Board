package simpleboard.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simpleboard.board.domain.MemberDetail;
import simpleboard.board.domain.Post;
import simpleboard.board.repository.PostRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Transactional
    public void savePost(Post post) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MemberDetail memberDetail = (MemberDetail) principal;

        post.setPostDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yy.MM.dd")));
        post.setContents(post.getContents().replaceAll(System.lineSeparator(), "<br>"));
        post.setAuthor(memberDetail.getUsername());
        postRepository.save(post);
    }

    public Optional<Post> findById(Long Id) {
        return postRepository.findById(Id);
    }

    @Transactional
    public void deleteById(Long Id) {
        postRepository.deleteById(Id);
    }


    public Page<Post> findAllPostPageble(Pageable pageable) {
        return postRepository.findAllByOrderByPostIdDesc(pageable);
    }
}
