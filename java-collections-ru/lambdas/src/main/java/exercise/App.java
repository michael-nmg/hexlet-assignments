package exercise;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class App {
    public static String[][] enlargeArrayImage(String[][] image) {
        return arrayToMulti(image)
                .map(row -> arrayToMulti(row).toArray(String[]::new))
                .toArray(String[][]::new);
    }

    private static <T> Stream<T> arrayToMulti(T[] array) {
        return Arrays.stream(array).mapMulti(doubling());
    }

    private static <T> BiConsumer<T, Consumer<T>> doubling() {
        return (part, consumer) -> {
            consumer.accept(part);
            consumer.accept(part);
        };
    }
}
