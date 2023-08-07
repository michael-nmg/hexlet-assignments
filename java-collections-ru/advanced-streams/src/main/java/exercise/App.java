package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

class App {
    private static String starter = "environment=\"";
    private static String varStarter = "X_FORWARDED_";
    private static String delimiter = ",";

    public static String getForwardedVariables(String config) {
        return config.lines()
                .filter(line -> line.startsWith(starter))
                .map(line -> line.substring(starter.length(), line.length() - 1))
                .flatMap(line -> Arrays.stream(line.split(",")))
                .filter(line -> line.contains(varStarter))
                .collect(Collectors.joining(delimiter))
                .replaceAll(varStarter, "");
    }
}
