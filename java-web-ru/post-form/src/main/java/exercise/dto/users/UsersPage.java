package exercise.dto.users;

import exercise.model.User;
import java.util.List;

import lombok.Getter;
import lombok.AllArgsConstructor;

// BEGIN
@Getter
@AllArgsConstructor
public class UsersPage {
    private List<User> users;
}
// END
