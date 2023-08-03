package exercise;

import java.util.List;

public class App {

    public static final List<String> FREE = List.of("gmail.com", "yandex.ru", "hotmail.com");

    public static int getCountOfFreeEmails(List<String> emails) {
        return (int) emails
                .stream()
                .filter(email -> FREE.contains(email.split("@")[1]))
                .count();
    }
}
