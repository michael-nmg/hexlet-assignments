package exercise;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static exercise.Utils.readFile;
import static exercise.Utils.unserialize;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.HashMap;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import com.fasterxml.jackson.databind.ObjectMapper;

class FileKVTest {

    private KeyValueStorage storage;
    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.CREATE);
        storage = new FileKV(filepath.toString(), Map.of("key1", "value1"));
    }

    @Test
    void testSet() {
        storage.set("key2", "value2");
        var actual = unserialize(readFile(filepath.toString()));
        var expected = Map.of("key1", "value1", "key2", "value2");
        assertEquals(expected, actual);
    }

    @Test
    void testUnset() {
        storage.unset("key1");
        var actual = unserialize(readFile(filepath.toString()));
        var expected = Map.of();
        assertEquals(expected, actual);
    }

    @Test
    void testGet() {
        var actual = storage.get("key1", "default");
        var expected = "value1";
        assertEquals(expected, actual);
    }

    @Test
    void testGetDefault() {
        var actual = storage.get("key2", "default");
        var expected = "default";
        assertEquals(expected, actual);
    }

    @Test
    void testToMap() {
        var actual = storage.toMap();
        var expected = Map.of("key1", "value1");
        assertEquals(expected, actual);
    }

}
