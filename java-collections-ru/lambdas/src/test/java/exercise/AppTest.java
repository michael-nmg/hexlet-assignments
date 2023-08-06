package exercise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static exercise.App.enlargeArrayImage;

class AppTest {
    String[][] image = {
            {"*", "*", "*", "*", "*"},
            {"*", " ", " ", " ", "*"},
            {"*", " ", "*", " ", "*"},
            {"*", " ", " ", " ", "*"},
            {"*", "*", "*", "*", "*"},
    };

    String[][] testForm = {
            {"*", "*", "*", "*", "*", "*", "*", "*", "*", "*"},
            {"*", "*", "*", "*", "*", "*", "*", "*", "*", "*"},
            {"*", "*", " ", " ", " ", " ", " ", " ", "*", "*"},
            {"*", "*", " ", " ", " ", " ", " ", " ", "*", "*"},
            {"*", "*", " ", " ", "*", "*", " ", " ", "*", "*"},
            {"*", "*", " ", " ", "*", "*", " ", " ", "*", "*"},
            {"*", "*", " ", " ", " ", " ", " ", " ", "*", "*"},
            {"*", "*", " ", " ", " ", " ", " ", " ", "*", "*"},
            {"*", "*", "*", "*", "*", "*", "*", "*", "*", "*"},
            {"*", "*", "*", "*", "*", "*", "*", "*", "*", "*"},
    };

    @Test
    void emptyImageTest() {
        String[][] newImage = enlargeArrayImage(new String[0][0]);
        Assertions.assertNotNull(newImage);
        Assertions.assertArrayEquals(new String[0][0], newImage);
    }

    @Test
    void standartImageTest() {
        String[][] newImage = enlargeArrayImage(image);
        Assertions.assertNotNull(newImage);
        Assertions.assertArrayEquals(testForm, newImage);
    }
}
