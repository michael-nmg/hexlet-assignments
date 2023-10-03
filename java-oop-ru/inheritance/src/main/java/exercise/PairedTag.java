package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

public class PairedTag extends Tag {

    private final String body;
    private final List<Tag> childes;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> childes) {
        super(name, attributes);
        this.body = body;
        this.childes = childes;
    }

    public String toString() {
        String name = super.getName();
        String tempChild = childesToString();
        String tempAttr = super.attributesToString();
        return String.format("<%s%s>%s%s</%s>", name, tempAttr, tempChild, body, name);
    }

    private String childesToString() {
        if (childes.isEmpty()) {
            return "";
        }

        return childes.stream()
                .map(Tag::toString)
                .collect(Collectors.joining());
    }

}
