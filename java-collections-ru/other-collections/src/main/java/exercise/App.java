package exercise;

import java.util.Map;
import java.util.Set;
import java.util.Objects;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.LinkedHashMap;

class App {
    public static Map<String, String> genDiff(Map<String, Object> dict1, Map<String, Object> dict2) {
        Set<String> keySet = new TreeSet<>(Comparator.naturalOrder());
        Map<String, String> result = new LinkedHashMap<>();
        keySet.addAll(dict2.keySet());
        keySet.addAll(dict1.keySet());
        keySet.forEach(key -> analyze(key, dict1, dict2, result));
        return result;
    }

    private static void analyze(String key,
                                Map<String, Object> dict1,
                                Map<String, Object> dict2,
                                Map<String, String> result) {
        if (dict1.containsKey(key) && dict2.containsKey(key)) {
            String value = Objects.equals(dict1.get(key), dict2.get(key)) ? "unchanged" : "changed";
            result.put(key, value);
        } else {
            result.put(key, dict1.containsKey(key) ? "deleted" : "added");
        }
    }
}
