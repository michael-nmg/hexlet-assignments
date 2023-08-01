package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class App {
    public static <T> List<Map<T, T>> findWhere(List<Map<T, T>> books, Map<T, T> dictionary) {
        List<Map<T, T>> result = new ArrayList<>();

        for (var book : books) {
            boolean consistency = true;

            for (var param : dictionary.entrySet()) {
                consistency &= book.containsKey(param.getKey());

                if (consistency) {
                    consistency = book.get(param.getKey()).equals(param.getValue());
                }
            }

            if (consistency) {
                result.add(book);
            }
        }

        return result;
    }
}
