package com.mparaske.SpellingWordGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private final List<Word> words;
    private final Random random;

    public Game(String fileName) throws IOException {
        words = new ArrayList<>();
        random = new Random();

        BufferedReader reader = new BufferedReader(new java.io.FileReader(fileName));
        String line;

        while ((line = reader.readLine()) != null) {
            words.add(new Word(line));
        }

        reader.close();
    }

    public void play(int numberOfWords) {
        Scanner scanner = new Scanner(System.in);
        int correctWords = 0;
        int points = 0;

        for (int i = 0; i < numberOfWords; i++) {
            Word randomWord = words.get(random.nextInt(words.size()));
            randomWord.speak();

            String input = scanner.nextLine();

            if (input.equalsIgnoreCase(randomWord.getWord())) {
                randomWord.setCorrect(true);
                correctWords++;
                points += 10;
                System.out.println("Correct!");
                System.out.println("Points: " + points);
            } else {
                System.out.println("Incorrect.");
            }
        }
        System.out.println("Game Over!");
        System.out.println("You got " + correctWords + " out of " + numberOfWords + " correct.");
    }
}
