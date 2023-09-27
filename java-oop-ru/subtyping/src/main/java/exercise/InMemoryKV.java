package exercise;

import java.util.Map;
import java.util.HashMap;

public class InMemoryKV implements KeyValueStorage {

    private final Map<String, String> data = new HashMap<>();

    public InMemoryKV(Map<String, String> data) {
        this.data.putAll(data);
    }

    public void set(String key, String value) {
        data.put(key, value);
    }

    public void unset(String key) {
        data.remove(key);
    }

    public String get(String key, String defaultValue) {
        return data.getOrDefault(key, defaultValue);
    }

    public Map<String, String> toMap() {
        return new HashMap<>(data);
    }

}
