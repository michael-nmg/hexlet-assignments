package exercise;

public final class LabelTag implements TagInterface {

    private final String value;
    private final TagInterface tag;

    public LabelTag(String value, TagInterface tag) {
        this.value = value;
        this.tag = tag;
    }

    public String render() {
        String tagValue = tag.render();
        return String.format("<label>%s%s</label>", value, tagValue);
    }

}
