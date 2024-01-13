package exercise;

import io.javalin.Javalin;

import java.util.List;
import java.util.Collections;

import exercise.model.User;
import exercise.dto.users.UsersPage;

import static org.apache.commons.lang3.StringUtils.startsWithIgnoreCase;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", context -> {
            List<User> result;
            var firstName = context.queryParam("term");

            if (firstName == null) {
                result = USERS;
            } else {
                result = USERS.stream()
                        .filter(user -> startsWithIgnoreCase(user.getFirstName(), firstName))
                        .toList();
            }

            var page = new UsersPage(result, firstName);
            context.render("users/index.jte", Collections.singletonMap("page", page));
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
