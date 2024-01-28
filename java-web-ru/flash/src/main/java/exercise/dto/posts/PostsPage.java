package exercise.dto.posts;

import java.util.List;
import exercise.model.Post;
import exercise.dto.BasePage;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

// BEGIN
@Getter
@Setter
@AllArgsConstructor
public class PostsPage extends BasePage {
    private List<Post> posts;
}
// END
