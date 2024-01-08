package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.IntStream;

import io.javalin.Javalin;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {
        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
            var page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
            var per = ctx.queryParamAsClass("per", Integer.class).getOrDefault(5);

            var start = per * page - per;
            var end = per * page;

            var result = IntStream.range(start, end)
                    .boxed()
                    .map(USERS::get)
                    .toList();

            ctx.json(result);
        });
        // END
        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
