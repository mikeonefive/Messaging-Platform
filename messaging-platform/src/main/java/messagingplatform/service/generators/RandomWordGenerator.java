package messagingplatform.service.generators;

import java.util.Random;

public class RandomWordGenerator {

    private static final String characters = "abcdefghijklmnopqrstuvxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()&%.,!?";
    private static final int minLength = 1;
    private static final int maxLength = 10;

    public static String generate() {

        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        int wordLength = random.nextInt(minLength, maxLength);

        for (int i = 0; i < wordLength; i++) {
            int randomIndex = random.nextInt(characters.length());
            stringBuilder.append(characters.charAt(randomIndex));
        }

        return stringBuilder.toString();
    }
}
