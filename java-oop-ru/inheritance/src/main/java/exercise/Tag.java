package exercise;

import java.util.Map;
import java.util.stream.Collectors;

public abstract class Tag {

    private final String name;
    private final Map<String, String> attributes;

    protected Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    public abstract String toString();

    protected String attributesToString() {
        if (attributes.isEmpty()) {
            return "";
        }

        return " " + attributes.entrySet().stream()
                .map(this::entryToString)
                .collect(Collectors.joining(" "));
    }

    private String entryToString(Map.Entry<String, String> entry) {
        var attrName = entry.getKey();
        var attrValue = entry.getValue();
        return String.format("%s=\"%s\"", attrName, attrValue);
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getAttribute() {
        return attributes;
    }

}
