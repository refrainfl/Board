package simpleboard.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simpleboard.board.domain.Member;
import simpleboard.board.domain.Post;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByLoginId(String loginId);

    List<Member> findByLoginIdAndPassword(String loginId, String password);
}
