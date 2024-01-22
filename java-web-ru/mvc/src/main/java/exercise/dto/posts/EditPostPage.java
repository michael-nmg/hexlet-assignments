package exercise.dto.posts;

import java.util.Map;
import java.util.List;

import exercise.model.Post;
import io.javalin.validation.ValidationError;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

// BEGIN
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EditPostPage {
    private Post post;
    private Map<String, List<ValidationError<Object>>> errors;
}
// END
