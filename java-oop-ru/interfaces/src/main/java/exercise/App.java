package exercise;

import java.util.List;
import java.util.Objects;

public class App {

    public static List<String> buildApartmentsList(List<Home> apartments, int count) {
        return apartments.stream()
                .sorted(Home::compareTo)
                .limit(count)
                .map(Objects::toString)
                .toList();
    }

}
