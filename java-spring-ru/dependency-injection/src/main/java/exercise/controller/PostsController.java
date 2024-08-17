package exercise.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import exercise.repository.CommentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    private PostRepository postRepository;
    private CommentRepository commentRepository;

    @Autowired
    public PostsController(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("")
    public List<Post> showAll() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post show(@PathVariable("id") Long postId) {
        return postRepository.findById(postId)
            .orElseThrow(() -> {
                var message = String.format("Post with id %s not found%n", postId);
                return new ResourceNotFoundException(message);
            });
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post data) {
        return postRepository.save(data);
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable("id") Long postId, @RequestBody Post data) {
        var post = postRepository.findById(postId)
        .orElseThrow(() -> {
            var message = String.format("Post with id %s not found%n", postId);
            return new ResourceNotFoundException(message);
        });
        post.setTitle(data.getTitle());
        post.setBody(data.getBody());
        return postRepository.save(post);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long postId) {
        postRepository.deleteById(postId);
        commentRepository.deleteByPostId(postId);
    }

}
// END
