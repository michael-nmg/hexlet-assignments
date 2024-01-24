package exercise.controller;

import java.util.Collections;

import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.util.NamedRoutes;
import exercise.repository.UsersRepository;

import static exercise.util.Security.encrypt;

import io.javalin.http.Context;
import io.javalin.validation.ValidationException;

public class SessionsController {

    // BEGIN
    public static void main(Context context) {
        var page = new MainPage(context.sessionAttribute("name"));
        context.render("index.jte", Collections.singletonMap("page", page));
    }

    public static void build(Context context) {
        var page = new LoginPage(context.sessionAttribute("name"), null);
        context.render("build.jte", Collections.singletonMap("page", page));
    }

    public static void login(Context context) {
        try {
            var name = context.formParamAsClass("name", String.class)
                    .check(UsersRepository::existsByName, "Wrong username or password")
                    .get();

            var user = UsersRepository.findByName(name);

            context.formParamAsClass("password", String.class)
                    .check(value -> user.getPassword().equals(encrypt(value)), "Wrong username or password")
                    .get();

            context.sessionAttribute("name", name);
            context.redirect(NamedRoutes.rootPath());
        } catch (ValidationException exception) {
            var login = context.formParam("login");
            var page = new LoginPage(login, exception.getErrors());
            context.render("build.jte", Collections.singletonMap("page", page));
        }
    }

    public static void logout(Context context) {
        context.sessionAttribute("name", null);
        context.redirect(NamedRoutes.rootPath());
    }
    // END
}
