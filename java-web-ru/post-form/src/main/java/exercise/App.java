package exercise;

import io.javalin.Javalin;

import java.util.List;
import java.util.Collections;

import exercise.model.User;
import exercise.dto.users.UsersPage;
import exercise.repository.UserRepository;

import static exercise.util.Security.encrypt;
import static org.apache.commons.lang3.StringUtils.capitalize;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/users", ctx -> {
            List<User> users = UserRepository.getEntities();
            var page = new UsersPage(users);
            ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });

        // BEGIN
        app.post("/users", context -> {
            var firstname = context.formParam("firstName");
            var lastname = context.formParam("lastName");
            var email = context.formParam("email").trim().toLowerCase();
            var password = context.formParam("password");
            var user = new User(capitalize(firstname), capitalize(lastname), email, encrypt(password));

            UserRepository.save(user);
            context.redirect("/users");
        });

        app.get("/users/build", context -> {
            context.render("users/build.jte");
        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
