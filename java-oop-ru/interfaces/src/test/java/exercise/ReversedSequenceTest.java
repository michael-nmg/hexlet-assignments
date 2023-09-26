package exercise;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReversedSequenceTest {

    private static int begin;
    private static int end;
    private static CharSequence reverseString;
    private static final String TESTED_FRASE = "ReversedSequence";
    private static final String TESTED_REVERSE_FRASE = "ecneuqeSdesreveR";


    @BeforeAll
    static void init() {
        reverseString = new ReversedSequence(TESTED_FRASE);
        begin = new Random().nextInt(reverseString.length());
        end = begin + new Random().nextInt(reverseString.length() - begin);
    }

    @Test
    void testLength() {
        int expected = TESTED_FRASE.length();
        int actual = reverseString.length();
        assertEquals(expected, actual);
    }

    @Test
    void testCharAt() {
        int expected = reverseString.charAt(begin);
        int actual = TESTED_REVERSE_FRASE.charAt(begin);
        assertEquals(expected, actual);
    }

    @Test
    void testSubSequence() {
        CharSequence actual = reverseString.subSequence(begin, end);
        CharSequence expected = TESTED_REVERSE_FRASE.subSequence(begin, end);
        assertEquals(expected, actual);
    }

    @Test
    void testToString() {
        String actual = reverseString.toString();
        assertEquals(TESTED_REVERSE_FRASE, actual);
    }

}
