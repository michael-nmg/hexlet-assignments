package exercise;

import java.net.URI;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> showAll() {
        return ResponseEntity
            .ok()
            .header("X-Total-Count", String.valueOf(posts.size()))
            .body(posts);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> show(@PathVariable("id") String postId) {
        var actual = posts.stream()
            .filter(post -> post.getId().equals(postId))
            .findFirst();
            return ResponseEntity.of(actual);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> create(@RequestBody Post post) {
        posts.add(post);
        var location = URI.create("/posts");
        return ResponseEntity.created(location).body(post);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> update(
            @PathVariable("id") String postId,
            @RequestBody Post newPost) {
        var actual = posts.stream()
            .filter(post -> post.getId().equals(postId))
            .findFirst();

        if (actual.isPresent()) {
            var post = actual.get();
            post.setId(newPost.getId());
            post.setTitle(newPost.getTitle());
            post.setBody(newPost.getBody());
            return ResponseEntity.ok(newPost);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
