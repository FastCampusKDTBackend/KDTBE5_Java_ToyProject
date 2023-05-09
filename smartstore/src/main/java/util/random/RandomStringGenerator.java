package util.random;

import java.util.Random;

public interface RandomStringGenerator {
    static String getRandomAlphabetString() {
        Random rnd = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            stringBuilder.append(String.valueOf((char) ((int) (rnd.nextInt(26)) + 65)));
        }
        return stringBuilder.toString();
    }

}
