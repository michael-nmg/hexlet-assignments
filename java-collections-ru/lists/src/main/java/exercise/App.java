package exercise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class App {
    public static boolean scrabble(String kit, String word) {
        List<String> wordList = Arrays.asList(word.toLowerCase().split(""));
        List<String> kitList = Arrays.asList(kit.toLowerCase().split(""));
        wordList.sort(Comparator.naturalOrder());
        kitList.sort(Comparator.naturalOrder());
        int count = 0;

        for (int i = 0, j = 0; i < kitList.size() && j < wordList.size(); ++i) {
            if (kitList.get(i).equals(wordList.get(j))) {
                ++j;
                ++count;
            }
        }

        return count == word.length();
    }
}
