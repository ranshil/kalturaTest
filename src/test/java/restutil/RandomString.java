package restutil;

import java.util.Random;

public class RandomString {

    public static final String SOURCES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    public static Random random = new Random();


    static public String generateString(int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = SOURCES.charAt(random.nextInt(SOURCES.length()));
        }
        return new String(text);
    }

    public static void main(String[] args) {
        System.out.printf(RandomString.generateString(5));
    }
}


