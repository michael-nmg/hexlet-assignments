package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exercise.Data;
import exercise.model.Post;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {
    private List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    public List<Post> showAllPostsByUserId(@PathVariable("id") Integer userId) {
        return posts.stream()
            .filter(post -> post.getUserId() == userId)
            .toList();
    }

    @PostMapping("/users/{id}/posts")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Post creatPostByUserId(
            @PathVariable("id") Integer userId,
            @RequestBody Post data) {
        data.setUserId(userId);
        posts.add(data);
        return data;
    }
}
// END
