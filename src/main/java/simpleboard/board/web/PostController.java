package simpleboard.board.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import simpleboard.board.domain.Post;
import simpleboard.board.service.PostService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public String list(Model model) {
        List<Post> posts = postService.findAllPost();
        model.addAttribute("posts", posts);
        return "posts/postList";
    }


    @GetMapping("/posts/new")
    public String createForm(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "posts/createPostForm";
    }


    @PostMapping("/posts/new")
    public String create(PostForm postForm) {
        Post post = new Post();
        post.setTitle(postForm.getTitle());
        post.setContents(postForm.getContents());
        post.setFilter(postForm.getFilter());

        postService.savePost(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}")
    public String findById(@PathVariable Long id, Model model) {
        Post post = postService.findById(id);
        model.addAttribute("post", post);

        if (model == null) {
            return "redirect:/posts";
        }
        return "posts/detail";
    }

    @GetMapping("/posts/{id}/edit")
    public String updatePostForm(@PathVariable Long id, Model model) {
        Post post = postService.findById(id);
        PostForm postForm = new PostForm();
        postForm.setPostId(post.getPostId());
        postForm.setPostDate(post.getPostDate());
        postForm.setTitle(post.getTitle());
        postForm.setFilter(post.getFilter());
        postForm.setContents(post.getContents());

        model.addAttribute("postForm", postForm);
        return "posts/updatePostForm";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@ModelAttribute("postForm") PostForm postForm) {

        Post post = new Post();
        post.setPostId(postForm.getPostId());
        post.setPostDate(postForm.getPostDate());
        post.setTitle(postForm.getTitle());
        post.setFilter(postForm.getFilter());
        post.setContents(postForm.getContents());

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        postService.savePost(post);
        return "redirect:/posts";
    }
}
