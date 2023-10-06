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
                .peek(field -> field.setAccessible(true))
                .filter(field -> field.getDeclaredAnnotation(NotNull.class) != null)
                .filter(field -> isNull(field, object))
                .map(Field::getName)
                .toList();
    }

    public static Map<String, List<String>> advancedValidate(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();

        return Arrays.stream(fields)
                .peek(field -> field.setAccessible(true))
                .filter(field -> field.getDeclaredAnnotation(NotNull.class) != null
                        || field.getDeclaredAnnotation(MinLength.class) != null)
                .map(field -> fieldToEntry(field, object))
                .filter(entry -> entry.getValue() != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static boolean isNull(Field field, Object object) {
        boolean result = true;

        try {
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
            if (field.getDeclaredAnnotation(NotNull.class) != null) {
                if (field.get(object) == null) {
                    value = List.of("can not be null");
                    return new AbstractMap.SimpleEntry<String, List<String>>(key, value);
                }
            }

            if (field.getDeclaredAnnotation(MinLength.class) != null) {
                int length = field.getDeclaredAnnotation(MinLength.class).minLength();
                if (field.get(object).toString().length() < length) {
                    value = List.of(String.format("length less than %s", length));
                    return new AbstractMap.SimpleEntry<String, List<String>>(key, value);
                }
            }
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException exeption) {
            exeption.printStackTrace();
        }

        return new AbstractMap.SimpleEntry<String, List<String>>(key, value);
    }

}
