package exercise;

import java.util.Map;

import static exercise.Utils.readFile;
import static exercise.Utils.writeFile;
import static exercise.Utils.serialize;
import static exercise.Utils.unserialize;

public class FileKV implements KeyValueStorage {

    private final String filePath;

    public FileKV(String filePath, Map<String, String> data) {
        this.filePath = filePath;
        var map = unserialize(readFile(filePath));
        map.putAll(data);
        writeFile(filePath, serialize(map));
    }

    public void set(String key, String value) {
        var map = unserialize(readFile(filePath));
        map.put(key, value);
        writeFile(filePath, serialize(map));
    }

    public void unset(String key) {
        var map = unserialize(readFile(filePath));
        System.out.println(map);
        map.remove(key);
        writeFile(filePath, serialize(map));
    }

    public String get(String key, String defaultValue) {
        var map = unserialize(readFile(filePath));
        return map.getOrDefault(key, defaultValue);
    }

    public Map<String, String> toMap() {
        return unserialize(readFile(filePath));
    }

}
