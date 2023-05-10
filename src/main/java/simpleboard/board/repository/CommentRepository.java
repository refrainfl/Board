package simpleboard.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simpleboard.board.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
