package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/posts")
    public List<Post> showAll(
        @RequestParam(defaultValue = "1") Integer page,
        @RequestParam(defaultValue = "10") Integer limit) {
        return posts.stream()
            .skip((page - 1) * limit)
            .limit(limit)
            .toList();
    }

    @GetMapping("/posts/{id}")
    public Optional<Post> show(@PathVariable("id") String postId) {
        return posts.stream()
            .filter(post -> post.getId().equals(postId))
            .findFirst();
    }

    @PostMapping("/posts")
    public Post createPost(@RequestBody Post post) {
        posts.add(post);
        return post;
    }
    
    @PutMapping("/posts/{id}")
    public Post updatePost(
            @PathVariable("id") String postId,
            @RequestBody Post newPost) {
        var oldPost = posts.stream()
            .filter(post -> post.getId().equals(postId))
            .findFirst();

        if (oldPost.isPresent()) {
            var post = oldPost.get();
            post.setId(newPost.getId());
            post.setTitle(newPost.getTitle());
            post.setBody(newPost.getBody());
        }

        return newPost;
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable("id") String postId) {
        posts.removeIf(post -> post.getId().equals(postId));
    }

}
