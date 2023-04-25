package simpleboard.board.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    private Long memberId;
    private String loginId;
    private String password;
    private String name;
    private String eMail;

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();
}

