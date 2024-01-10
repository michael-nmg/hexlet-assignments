package exercise.dto.users;

import java.util.List;
import exercise.model.User;

import lombok.Getter;
import lombok.AllArgsConstructor;

// BEGIN
@Getter
@AllArgsConstructor
public class UsersPage {
    private List<User> users;
}
// END
