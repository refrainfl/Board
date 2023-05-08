package simpleboard.board.web;

import lombok.Getter;
import lombok.Setter;
import simpleboard.board.domain.PostFilter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PostForm {
    private Long postId;
    private String postDate;
    @NotEmpty(message = "제목을 입력해주세요.")
    private String title;
    @NotEmpty(message = "내용을 입력해주세요.")
    private String contents;
    private PostFilter filter;
}
