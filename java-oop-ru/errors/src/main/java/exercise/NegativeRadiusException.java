package exercise;

public class NegativeRadiusException extends Exception {

    private final String message;

    public NegativeRadiusException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
