package exercise.controller;

import java.util.ArrayList;
import java.util.Collections;

import exercise.dto.posts.PostPage;
import exercise.dto.posts.PostsPage;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    private static final int POST_PER_SHEET = 5;

    // BEGIN
    public static void index(Context context) {
        var posts = PostRepository.getEntities();
        var page = context.queryParamAsClass("page", Integer.class).getOrDefault(1);
        var countSheets = posts.size() / POST_PER_SHEET;

        if (page < 1 || countSheets < page) {
            throw new NotFoundResponse("Page not found");
        }

        var start = (page - 1) * POST_PER_SHEET;
        var end = page * POST_PER_SHEET;
        var result = new ArrayList<>(posts.subList(start, end));
        var postsPage = new PostsPage(result, page);
        context.render("posts/index.jte", Collections.singletonMap("page", postsPage));
    }

    public static void show(Context context) {
        var id = context.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id);

        if (post.isEmpty()) {
            throw new NotFoundResponse("Page not found");
        }

        var page = new PostPage(post.get());
        context.render("posts/show.jte", Collections.singletonMap("page", page));
    }
    // END
}
