package simpleboard.board.repository;

import org.springframework.stereotype.Repository;
import simpleboard.board.domain.Member;
import simpleboard.board.domain.Post;
import simpleboard.board.domain.PostFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PostRepository {
    @PersistenceContext
    EntityManager em;

    public Long save(Post post) {
        em.persist(post);
        return post.getPostId();
    }

    public Post findOne(Long postId) {
        return em.find(Post.class, postId);
    }

    public List<Post> findByTitle(String title) {
        return em.createQuery("select p from Post p where p.title like :title", Post.class)
                .setParameter("title", "%" + title + "%")
                .getResultList();
    }

    public List<Post> findByContents(String contents) {
        return em.createQuery("select p from Post p " +
                        "where p.contents like :contents", Post.class)
                .setParameter("contents", "%" + contents + "%")
                .getResultList();
    }


    public List<Post> findByTitleOrContests(String title, String contents) {
        return em.createQuery(
                        "select p from Post p " +
                                "where p.title like :title or p.contents like :contents", Post.class)
                .setParameter("title", "%" + title + "%")
                .setParameter("contents", "%" + contents + "%")
                .getResultList();
    }


    public List<Post> findByFilter(PostFilter filter) {
        return em.createQuery("select p from Post p " +
                        "where p.filter = :filter", Post.class)
                .setParameter("filter", filter)
                .getResultList();
    }


    public List<Post> findByMemberId(Long memberId) {
        return em.createQuery("select p from Post p " +
                        "where p.MemberId = :memberId", Post.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
