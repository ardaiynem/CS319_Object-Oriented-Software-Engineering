package com.bilport.demo.domain.model;

import java.util.Random;

public class PasswordGenerator {

    private final int PASSWORD_LENGTH = 8;

    private final String UPPER_CASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String LOWER_CASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private final String NUMBERS = "0123456789";
    //change special character set here
    private final String SPECIAL_CHARS = "!@#$%&*()_-<>?";

    /**
     * Creates a random string with a set length
     * @return the newly created random string
     */
    public String generatePassword(){
        StringBuilder password = new StringBuilder();
        Random rand = new Random();
        
        // Add one character from each character set
        password.append(getRandomChar(UPPER_CASE_CHARS, rand));
        password.append(getRandomChar(LOWER_CASE_CHARS, rand));
        password.append(getRandomChar(NUMBERS, rand));
        password.append(getRandomChar(SPECIAL_CHARS, rand));

        // Add remaining characters randomly
        for (int i = 4; i < PASSWORD_LENGTH; i++) {
            String charSet = getRandomCharSet(rand);
            password.append(getRandomChar(charSet, rand));
        }

        // Shuffle the password to increase randomness
        for (int i = 0; i < password.length(); i++) {
            int swapIndex = rand.nextInt(password.length());
            char temp = password.charAt(i);
            password.setCharAt(i, password.charAt(swapIndex));
            password.setCharAt(swapIndex, temp);
        }

        return password.toString();
    }

    /**
     * Returns a random character from the given character set
     * @param charSet string containing the character set
     * @param random Random object to generate a random index
     * @return a character from the given set
     */
    private char getRandomChar(String charSet, Random random) {
        int randomIndex = random.nextInt(charSet.length());
        return charSet.charAt(randomIndex);
    }

    /**
     * Chooses and returns a random character set between the declared sets of the class
     * @param random Random object to generate a random number
     * @return
     */
    private String getRandomCharSet(Random random) {
        int randomIndex = random.nextInt(4);
        switch (randomIndex) {
            case 0:
                return UPPER_CASE_CHARS;
            case 1:
                return LOWER_CASE_CHARS;
            case 2:
                return NUMBERS;
            case 3:
                return SPECIAL_CHARS;
            default:
                return "";
        }
    }
}
