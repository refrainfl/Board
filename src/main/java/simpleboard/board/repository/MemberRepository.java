package simpleboard.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simpleboard.board.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginId(String loginId);

}
