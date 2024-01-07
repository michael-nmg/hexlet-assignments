package exercise;

import io.javalin.Javalin;

import java.util.List;

import static exercise.Data.getPhones;
import static exercise.Data.getDomains;

public final class App {

    public static Javalin getApp() {
        List<String> phones = getPhones();
        List<String> domains = getDomains();
        Javalin javalin = Javalin.create(config -> config.plugins.enableDevLogging());
        javalin.get("/phones", context -> context.json(phones));
        javalin.get("/domains", context -> context.json(domains));
        return javalin;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
