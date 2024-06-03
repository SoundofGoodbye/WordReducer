import java.util.*;
import java.util.stream.Collectors;

public class WordReducer {
    private Set<String> allWords;

    public WordReducer(Set<String> allWords) {
        this.allWords = allWords;
    }

    public Set<String> findNineLetterWords() {
        return Optional.ofNullable(allWords)
                .orElseGet(HashSet::new)
                .stream()
                .filter(currentWord -> currentWord.length() == 9)
                .collect(Collectors.toSet());
    }

    /**
     * Find all the nine-letter words that still remain words after we
     * remove a letter from them each time.
     * @return
     */
    public Set<String> findAllRiddleWords() {
        Map<String, Boolean> wordToIsReducible = new HashMap<>();

        Set<String> nineLetterWords = findNineLetterWords();
        for (String nineLetterWord : nineLetterWords) {
            boolean isReducible = isReducible(nineLetterWord);
            wordToIsReducible.put(nineLetterWord, isReducible);
        }

        Set<String> result = new HashSet<>();
        for(String key : wordToIsReducible.keySet()) {
            if(wordToIsReducible.get(key)) {
                result.add(key);
            }
        }

        return result;
    }

    public boolean isReducible(String word) {
        StringBuilder sb = new StringBuilder(word);

        for(int letterIndex = sb.length() - 1; letterIndex >= 0; letterIndex--) {
            char removedLetter = sb.charAt(letterIndex);
            sb.deleteCharAt(letterIndex);
            String reducedWord = sb.toString();

            if(reducedWord.equals("I") || reducedWord.equals("A")) {
                return true;
            }

            if(allWords.contains(reducedWord) && isReducible(reducedWord)) {
                return true;
            }

            sb.insert(letterIndex, removedLetter);
        }

        return false;
    }
}
