import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Set;
import java.util.stream.Collectors;

public class WordLoader {

    private WordLoader() {

    }

    public static Set<String> loadWordsFromURL(String url) throws IOException {
        URL wordsUrl = new URL(url);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(wordsUrl.
                openConnection().
                getInputStream()))) {
            return reader.lines().skip(2).collect(Collectors.toSet());
        }
    }
}
