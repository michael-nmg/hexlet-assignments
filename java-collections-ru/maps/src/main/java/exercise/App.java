package exercise;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static Map<String, Integer> getWordCount(String text) {
        Map<String, Integer> result = new HashMap<>();

        if (text.isEmpty()) {
            return result;
        }

        String[] words = text.trim().toLowerCase().split(" ");

        for (String word : words) {
            result.compute(word, (k, v) -> (v == null) ? 1 : v + 1);
        }

        return result;
    }

    public static String toString(Map<String, Integer> dictionary) {
        if (dictionary.isEmpty()) {
            return "{}";
        }

        StringBuilder result = new StringBuilder("{\n");

        for (var pair : dictionary.entrySet()) {
            result.append(String.format("  %s: %s%n", pair.getKey(), pair.getValue()));
        }

        return result.append("}").toString();
    }
}
