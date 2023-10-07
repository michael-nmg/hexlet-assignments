package exercise;

import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;

public class App {

    public static void save(Path filePath, Car car) throws Exception {
        String jsonCar = car.serialize();
        Files.writeString(filePath, jsonCar);
    }

    public static Car extract(Path filepath) throws IOException {
        String data = Files.readString(filepath);
        return Car.unserialize(data);
    }

}
