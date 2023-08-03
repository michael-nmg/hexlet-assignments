package exercise;

import java.util.List;

public class App {
    public static final List<String> FREE_HOSTS = List.of("gmail.com", "yandex.ru", "hotmail.com");

    public static long getCountOfFreeEmails(List<String> emails) {
        return emails
                .stream()
                .map(email -> email.split("@")[1])
                .filter(FREE_HOSTS::contains)
                .count();
    }
}
