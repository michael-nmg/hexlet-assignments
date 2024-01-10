package exercise.dto.users;

import exercise.model.User;

import lombok.Getter;
import lombok.AllArgsConstructor;

// BEGIN
@Getter
@AllArgsConstructor
public class UserPage {
    private User user;
}
// END
