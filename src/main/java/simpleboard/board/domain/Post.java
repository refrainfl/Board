package simpleboard.board.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String postDate;
    private String title;
    @Column(length = 10000)
    private String contents;
    @Enumerated(value = EnumType.STRING)
    private PostFilter filter;

    private String memberId;

    private String author;

    @ManyToOne
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

}
