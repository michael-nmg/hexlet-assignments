package exercise;

public class ReversedSequence implements CharSequence {

    private final CharSequence reverseStr;

    public ReversedSequence(String str) {
        this.reverseStr = new StringBuilder(str).reverse();
    }

    @Override
    public int length() {
        return reverseStr.length();
    }

    @Override
    public char charAt(int i) {
        return reverseStr.charAt(i);
    }

    @Override
    public CharSequence subSequence(int begin, int end) {
        return reverseStr.subSequence(begin, end);
    }

    @Override
    public String toString() {
        return reverseStr.toString();
    }
}
