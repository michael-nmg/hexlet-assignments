package exercise;

public class App {

    public static void swapKeyValue(KeyValueStorage dataBase) {
        dataBase.toMap().keySet().forEach(key -> {
            String value = dataBase.get(key, "");
            dataBase.unset(key);
            dataBase.set(value, key);
        });
    }

}
