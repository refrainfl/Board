package simpleboard.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simpleboard.board.domain.Comment;
import simpleboard.board.domain.MemberDetail;
import simpleboard.board.domain.Post;
import simpleboard.board.repository.CommentRepository;
import simpleboard.board.repository.MemberRepository;
import simpleboard.board.repository.PostRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Transactional(readOnly = true)
public class CommentService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Transactional
    public void saveComment(Comment comment, Post post) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MemberDetail memberDetail = (MemberDetail) principal;

        comment.setMember(memberDetail.getMember());
        comment.setPost(post);
        comment.setAuthor(memberDetail.getUsername());
        comment.setCommentDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yy.MM.dd")));

        commentRepository.save(comment);
    }
}
