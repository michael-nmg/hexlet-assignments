package exercise.controller;

import java.util.Collections;

import exercise.model.Post;
import exercise.util.NamedRoutes;
import exercise.dto.posts.PostPage;
import exercise.dto.posts.PostsPage;
import exercise.dto.posts.EditPostPage;
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

    public static void create(Context ctx) {
        try {
            var name = ctx.formParamAsClass("name", String.class)
                    .check(value -> value.length() >= 2, "Название не должно быть короче двух символов")
                    .get();

            var body = ctx.formParamAsClass("body", String.class)
                    .check(value -> value.length() >= 10, "Пост должен быть не короче 10 символов")
                    .get();

            var post = new Post(name, body);
            PostRepository.save(post);
            ctx.redirect(NamedRoutes.postsPath());

        } catch (ValidationException e) {
            var name = ctx.formParam("name");
            var body = ctx.formParam("body");
            var page = new BuildPostPage(name, body, e.getErrors());
            ctx.render("posts/build.jte", Collections.singletonMap("page", page)).status(422);
        }
    }

    public static void index(Context ctx) {
        var posts = PostRepository.getEntities();
        var postPage = new PostsPage(posts);
        ctx.render("posts/index.jte", Collections.singletonMap("page", postPage));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", Collections.singletonMap("page", page));
    }

    // BEGIN
    public static void edit(Context context) {
        var id = context.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new EditPostPage(post, null);
        context.render("posts/edit.jte", Collections.singletonMap("page", page));
    }

    public static void patch(Context context) {
        try {
            var id = context.pathParamAsClass("id", Long.class).get();
            var name = context.formParamAsClass("name", String.class)
                    .check(value -> value.length() >= 2, "Название не должно быть короче двух символов")
                    .get();

            var body = context.formParamAsClass("body", String.class)
                    .check(value -> value.length() >= 10, "Пост должен быть не короче 10 символов")
                    .get();

            var post = PostRepository.find(id).get();
            post.setName(name);
            post.setBody(body);
            context.redirect("/posts");
        } catch (ValidationException exception) {
            var id = context.pathParamAsClass("id", Long.class).get();
            var name = context.formParam("name");
            var body = context.formParam("body");
            var post = new Post(id, name, body);
            var page = new EditPostPage(post, exception.getErrors());
            context.status(422)
                    .render("posts/edit.jte", Collections.singletonMap("page", page));
        }
    }
    // END
}
