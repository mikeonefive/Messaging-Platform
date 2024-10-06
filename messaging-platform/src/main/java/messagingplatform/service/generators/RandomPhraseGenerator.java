package messagingplatform.service.generators;

import java.util.Random;

public class RandomPhraseGenerator {

    private static final int minLength = 1;
    private static final int maxLength = 10;

    public static String generate() {

        Random random = new Random();
        int phraseLength = random.nextInt(minLength, maxLength);
        StringBuilder phrase = new StringBuilder();

        for (int i = 0; i < phraseLength; i++) {
            String randomWord = RandomWordGenerator.generate();
            phrase.append(randomWord).append(" ");
        }

        return phrase.toString().trim();
    }
}
