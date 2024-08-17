package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("")
    public List<Comment> showAll() {
        return commentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Comment show(@PathVariable("id") Long commentId) {
        return commentRepository.findById(commentId)
            .orElseThrow(() -> {
                var message = String.format("Comment with id %s not found%n", commentId);
                return new ResourceNotFoundException(message);
        });
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@RequestBody Comment data) {
        return commentRepository.save(data);
    }

    @PutMapping("/{id}")
    public Comment update(@PathVariable("id") Long commentId, @RequestBody Comment data) {
        var comment = commentRepository.findById(commentId)
        .orElseThrow(() -> {
            var message = String.format("Comment with id %s not found%n", commentId);
            return new ResourceNotFoundException(message);
        });
        comment.setBody(data.getBody());
        comment.setPostId(data.getPostId());
        return commentRepository.save(comment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long commentId) {
        commentRepository.deleteById(commentId);
    }

}
// END
