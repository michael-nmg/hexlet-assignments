package exercise;

import java.util.Map;
import java.util.List;
import java.util.Arrays;
import java.util.AbstractMap;
import java.lang.reflect.Field;
import java.util.stream.Collectors;

public class Validator {

    public static List<String> validate(Object object) throws SecurityException, IllegalArgumentException {
        Field[] fields = object.getClass().getDeclaredFields();

        return Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(NotNull.class))
                .filter(field -> isNull(field, object))
                .map(Field::getName)
                .toList();
    }

    public static Map<String, List<String>> advancedValidate(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();

        return Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(NotNull.class) || field.isAnnotationPresent(MinLength.class))
                .map(field -> fieldToEntry(field, object))
                .filter(entry -> entry.getValue() != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static boolean isNull(Field field, Object object) {
        boolean result = true;

        try {
            field.setAccessible(true);
            result = field.get(object) == null;
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException exeption) {
            exeption.printStackTrace();
        }

        return result;
    }

    private static Map.Entry<String, List<String>> fieldToEntry(Field field, Object object) {
        String key = field.getName();
        List<String> value = null;


        try {
            field.setAccessible(true);
            var notNullAnntt = field.getDeclaredAnnotation(NotNull.class);
            var minLengthAnntt = field.getDeclaredAnnotation(MinLength.class);

            if (notNullAnntt != null && field.get(object) == null) {
                value = List.of("can not be null");
            }

            if (minLengthAnntt != null && field.get(object) != null) {
                int expectedLength = field.getDeclaredAnnotation(MinLength.class).minLength();
                int actualLength = field.get(object).toString().length();
                if (actualLength < expectedLength) {
                    value = List.of(String.format("length less than %s", expectedLength));
                }
            }
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException exeption) {
            exeption.printStackTrace();
        }

        return new AbstractMap.SimpleEntry<>(key, value);
    }

}
