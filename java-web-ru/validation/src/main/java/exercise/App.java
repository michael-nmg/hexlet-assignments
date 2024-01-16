package exercise;

import java.util.List;
import java.util.Collections;

import io.javalin.Javalin;
import io.javalin.validation.ValidationException;

import exercise.model.Article;
import exercise.dto.articles.ArticlesPage;
import exercise.dto.articles.BuildArticlePage;

import exercise.repository.ArticleRepository;

public final class App {

    private static final Integer TITLE_MIN_SIZE = 2;
    private static final Integer TEXT_MIN_SIZE = 10;

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/articles", ctx -> {
            List<Article> articles = ArticleRepository.getEntities();
            var page = new ArticlesPage(articles);
            ctx.render("articles/index.jte", Collections.singletonMap("page", page));
        });

        // BEGIN
        app.post("/articles", context -> {
            try {
                var title = context.formParamAsClass("title", String.class)
                        .check(text -> text.length() >= TITLE_MIN_SIZE,
                                "Название не должно быть короче двух символов")
                        .check(text -> !ArticleRepository.existsByTitle(text),
                                "Статья с таким названием уже существует")
                        .get();

                var content = context.formParamAsClass("content", String.class)
                        .check(text -> text.length() >= TEXT_MIN_SIZE, "Статья должна быть не короче 10 символов")
                        .get();

                var article = new Article(title, content);
                ArticleRepository.save(article);
                context.redirect("/articles");
            } catch (ValidationException exception) {
                var title = context.formParam("title");
                var content = context.formParam("content");
                var build = new BuildArticlePage(title, content, exception.getErrors());
                context.status(422)
                        .render("articles/build.jte", Collections.singletonMap("build", build));
            }
        });

        app.get("/articles/build", context -> {
            var build = new BuildArticlePage();
            context.render("articles/build.jte", Collections.singletonMap("build", build));
        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
