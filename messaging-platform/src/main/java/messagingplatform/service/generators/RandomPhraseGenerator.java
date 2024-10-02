package messagingplatform.service.generators;

import java.util.Random;

public class RandomPhraseGenerator {

    private static final int minLength = 2;
    private static final int maxLength = 10;

    public static String generate() {

        Random random = new Random();
        int phraseLength = random.nextInt(minLength, maxLength);
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < phraseLength; i++) {
            String randomWord = RandomWordGenerator.generate();
            stringBuilder.append(randomWord).append(" ");
        }

        return stringBuilder.toString().trim();
    }
}
