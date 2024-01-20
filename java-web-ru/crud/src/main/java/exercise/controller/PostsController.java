package exercise.controller;

import java.util.List;
import java.util.Collections;

import exercise.model.Post;
import exercise.dto.posts.PostPage;
import exercise.dto.posts.PostsPage;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    private static final int POST_PER_SHEET = 5;

    // BEGIN
    public static void index(Context context) {
        List<Post> result;
        var posts = PostRepository.getEntities();
        var page = context.queryParamAsClass("page", Integer.class).getOrDefault(1);
        var start = (page - 1) * POST_PER_SHEET;
        var end = start + POST_PER_SHEET;

        if (start < 0) {
            result = posts.subList(0, POST_PER_SHEET);
        } else if (end > posts.size()) {
            result = posts.subList(posts.size() - POST_PER_SHEET + 1, posts.size());
        } else {
            result = posts.subList(start, end);
        }

        var postsPage = new PostsPage(result, page);
        context.render("posts/index.jte", Collections.singletonMap("page", postsPage));
    }

    public static void show(Context context) {
        var id = context.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id).orElseThrow(() -> new NotFoundResponse("Page not found"));
        var page = new PostPage(post);
        context.render("posts/show.jte", Collections.singletonMap("page", page));
    }
    // END
}
