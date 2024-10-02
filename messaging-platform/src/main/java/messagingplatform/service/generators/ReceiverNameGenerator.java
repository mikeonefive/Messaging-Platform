package messagingplatform.service.generators;

import java.util.List;
import java.util.Random;

public class ReceiverNameGenerator {

    private static final List<Character> firstCharacters = List.of('A', 'B', 'C');

    public static String generate() {

        Random random = new Random();
        char firstRandomChar = firstCharacters.get(random.nextInt(firstCharacters.size()));

        String randomName = firstRandomChar + RandomWordGenerator.generate();

        return randomName;
    }
}