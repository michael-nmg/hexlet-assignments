package exercise.controller;

import java.util.Collections;

import exercise.model.Post;
import exercise.util.NamedRoutes;
import exercise.dto.posts.PostPage;
import exercise.dto.posts.PostsPage;
import exercise.dto.posts.BuildPostPage;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;

public class PostsController {

    public static void build(Context ctx) {
        var page = new BuildPostPage();
        ctx.render("posts/build.jte", Collections.singletonMap("page", page));
    }

    // BEGIN
    public static void index(Context context) {
        var flash = (String) context.consumeSessionAttribute("flash");
        var alertStatus = (String) context.consumeSessionAttribute("alert-status");
        var posts = PostRepository.getEntities();
        var page = new PostsPage(posts);
        page.setFlash(flash);
        page.setAlertStatus(alertStatus);
        context.render("posts/index.jte", Collections.singletonMap("page", page));
    }

    public static void create(Context context) {
        try {
            var body = context.formParamAsClass("body", String.class).get();
            var name = context.formParamAsClass("name", String.class)
                    .check(value -> value.length() >= 2, "Very low title.")
                    .get();

            PostRepository.save(new Post(name, body));
            context.sessionAttribute("flash", "Пост был успешно создан!");
            context.sessionAttribute("alert-status", "success");
            context.redirect(NamedRoutes.postsPath());
        } catch (ValidationException exception) {
            var name = context.formParam("name");
            var body = context.formParam("body");
            context.sessionAttribute("flash", "Very low title.");
            context.sessionAttribute("alert-status", "danger");
            var page = new BuildPostPage(name, body, exception.getErrors());
            context.render("posts/build.jte", Collections.singletonMap("page", page));
        }
    }
    // END

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", Collections.singletonMap("page", page));
    }
}
