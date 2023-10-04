package simpleboard.board.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import simpleboard.board.domain.MemberDetail;
import simpleboard.board.domain.Post;
import simpleboard.board.service.PostService;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor


@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @GetMapping("")
    public String list(@PageableDefault(size = 5) Pageable pageable, Model model) {
        Page<Post> posts = postService.findAllPostPageble(pageable);

        int startPage = Math.max(1, posts.getPageable().getPageNumber() - 4);
        int endPage = Math.min(posts.getPageable().getPageNumber() + 4, posts.getTotalPages());

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("posts", posts);


        return "posts/postList";
    }


    @GetMapping("/new")
    public String createForm(Model model, Authentication authentication) {
        MemberDetail memberDetail = (MemberDetail) authentication.getPrincipal();
        log.info("username={}",memberDetail.getUsername());

        model.addAttribute("postForm", new PostForm());
        return "posts/createPostForm";
    }


    @PostMapping("/new")
    public String create(PostForm postForm) {
        Post post = new Post();
        post.setTitle(postForm.getTitle());
        post.setContents(postForm.getContents());
        post.setFilter(postForm.getFilter());

        postService.savePost(post);
        return "redirect:/posts";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        Optional<Post> byId = postService.findById(id);
        Post post = byId.get();
        model.addAttribute("post", post);

        if (model == null) {
            return "redirect:/posts";
        }
        return "posts/detail";
    }

    @GetMapping("/{id}/edit")
    public String updatePostForm(@PathVariable Long id, Model model) {
        Optional<Post> byId = postService.findById(id);
        Post post = byId.get();

        PostForm postForm = new PostForm();
        postForm.setPostId(post.getPostId());
        postForm.setPostDate(post.getPostDate());
        postForm.setTitle(post.getTitle());
        postForm.setFilter(post.getFilter());
        postForm.setContents(post.getContents());

        model.addAttribute("postForm", postForm);
        return "posts/updatePostForm";
    }

    @PostMapping("/{id}/edit")
    public String updatePost(@ModelAttribute("postForm") PostForm postForm) {

        Post post = new Post();
        post.setPostId(postForm.getPostId());
        post.setPostDate(postForm.getPostDate());
        post.setTitle(postForm.getTitle());
        post.setFilter(postForm.getFilter());
        post.setContents(postForm.getContents());

        postService.savePost(post);
        return "redirect:/posts";
    }

    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        postService.deleteById(id);
        return "redirect:/posts";
    }
}
