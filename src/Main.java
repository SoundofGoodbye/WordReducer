import java.util.Set;

public class Main {
    private static final String SCRABBLE_WORDS_URL = "https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt";

    public static void main(String[] args) {

        Set<String> allWords = null;

        try {
            allWords = WordLoader.loadWordsFromURL(SCRABBLE_WORDS_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long startTime = System.nanoTime();
        WordReducer wordReducer = new WordReducer(allWords);
        Set<String> riddleWords = wordReducer.findAllRiddleWords();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;
        System.out.println("Duration: " + duration + " ms");
        System.out.println("Number of riddle words: " + riddleWords.size());
    }
}