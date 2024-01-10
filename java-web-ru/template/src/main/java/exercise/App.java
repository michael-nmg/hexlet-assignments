package exercise;

import java.util.List;
import java.util.Collections;

import exercise.model.User;
import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", context -> {
            var usersPage = new UsersPage(USERS);
            context.render("users/index.jte", Collections.singletonMap("users", usersPage));
        });

        app.get("/users/{id}", context -> {
            var id = context.pathParamAsClass("id", Long.class).get();
            var user = USERS.stream()
                    .filter(usr -> usr.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new NotFoundResponse("User not found"));
            var userPage = new UserPage(user);
            context.render("users/show.jte", Collections.singletonMap("user", userPage));
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
