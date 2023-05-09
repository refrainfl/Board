package simpleboard.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import simpleboard.board.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByOrderByPostIdDesc(Pageable pageable);
}
