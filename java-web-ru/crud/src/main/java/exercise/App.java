package exercise;

import io.javalin.Javalin;
import exercise.controller.PostsController;
import exercise.controller.RootController;

import static exercise.util.NamedRoutes.rootPath;
import static exercise.util.NamedRoutes.postsPath;
import static exercise.util.NamedRoutes.postPath;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get(rootPath(), RootController::index);

        // BEGIN
        app.get(postPath(), PostsController::show);
        app.get(postsPath(), PostsController::index);
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
