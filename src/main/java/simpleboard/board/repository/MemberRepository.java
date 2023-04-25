package simpleboard.board.repository;

import org.springframework.stereotype.Repository;
import simpleboard.board.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {
    @PersistenceContext
    EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getMemberId();
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Member> findByLoginId(String loginId) {
        return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultList();
    }

    public List<Member> findByLoginIdAndPassword(String loginId, String password) {
        return em.createQuery("select m from Member m where m.loginId = :loginId and m.password = :password")
                .setParameter("loginId", loginId)
                .setParameter("password", password)
                .getResultList();
    }

}
