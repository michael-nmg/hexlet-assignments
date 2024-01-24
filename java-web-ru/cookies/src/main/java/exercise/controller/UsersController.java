package exercise.controller;

import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;

import java.util.Collections;

import exercise.repository.UserRepository;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void create(Context context) {
        var firstName = context.formParamAsClass("firstName", String.class).get();
        var lastName = context.formParamAsClass("lastName", String.class).get();
        var email = context.formParamAsClass("email", String.class).get();
        var password = context.formParamAsClass("password", String.class).get();

        var token = Security.generateToken();
        context.cookie("token", token);

        var user = new User(firstName, lastName, email, password, token);
        UserRepository.save(user);

        context.redirect(NamedRoutes.userPath(user.getId()));
    }

    public static void show(Context context) {
        var id = context.pathParamAsClass("id", Long.class).get();
        var token = context.cookie("token");

        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("User not found"));

        if (token != null && token.equals(user.getToken())) {
            context.render("users/show.jte", Collections.singletonMap("user", user));
        } else {
            context.redirect(NamedRoutes.buildUserPath());
        }
    }
    // END
}
