package exercise;

import java.util.List;
import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppTest {
    List<Integer> list;
    Integer size;
    Integer count;
    Integer first;
    Integer last;

    @BeforeEach
    void listInitial() {
        this.list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        this.size = list.size();
        this.count = new Random().nextInt(size) + 1;
        this.first = list.get(0);
        this.last = list.get(count - 1);
    }

    @Test
    void testTake() {
        List<Integer> newList = App.take(list, count);
        Assertions.assertNotNull(newList);
        Assertions.assertEquals(newList.size(), count);
        Assertions.assertEquals(newList.get(0), first);
        Assertions.assertEquals(newList.get(count - 1), last);
    }

    @Test
    void testTakeConsistency() {
        boolean result = true;
        List<Integer> newList = App.take(list, count);

        for (int i = 0; i < count; ++i) {
            result &= newList.get(i).equals(list.get(i));
        }

        Assertions.assertTrue(result);
    }
}
