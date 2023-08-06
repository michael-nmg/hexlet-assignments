package exercise;

import java.util.Map;
import java.util.List;
import java.time.LocalDate;

public class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        return users.stream()
                .filter(user -> user.get("gender").equals("male"))
                .sorted((u1, u2) -> dateCompare(u1.get("birthday"), u2.get("birthday")))
                .map(user -> user.get("name"))
                .toList();
    }

    private static int dateCompare(String date1, String date2) {
        return LocalDate.parse(date1).compareTo(LocalDate.parse(date2));
    }
}
