package exercise.controller;

import exercise.model.User;
import exercise.util.Security;
import exercise.util.NamedRoutes;

import java.util.Collections;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import exercise.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void create(Context context) {
        var firstName = StringUtils.capitalize(context.formParam("firstName"));
        var lastName = StringUtils.capitalize(context.formParam("lastName"));
        var email = context.formParam("email").trim().toLowerCase();
        var password = context.formParam("password");

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
                .orElseThrow(() -> new NotFoundResponse("User" + id + "not found"));

        if (token != null && token.equals(user.getToken())) {
            context.render("users/show.jte", Collections.singletonMap("user", user));
        } else {
            context.redirect(NamedRoutes.buildUserPath());
        }
    }
    // END
}
