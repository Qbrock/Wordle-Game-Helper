package com.quinnbrockmyre.wordleproject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.quinnbrockmyre.wordleproject.utils.WordHandler;
import com.quinnbrockmyre.wordleproject.utils.WordList;

public class Driver {
    /**
     * Main driver method for running the WordleHelper
     * Will prompt user for input based on users current game of wordle
     * Will prompt for each grey letter, amount of yellow letters and their
     * locations and all green letters
     * 
     * @param args Unused System.in args
     */
    public static void main(String[] args) {
        WordList MyList = new WordList();
        try {
            WordHandler.possibleWords(MyList);
        } catch (IOException e) {
            System.out.println("IOError, " + e);
        }

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter the grey letters: ");
            WordHandler.getGreys(sc, MyList);

            System.out.println("How many different yellow letters do you have?");
            WordHandler.getYellows(sc, MyList);

            System.out.println(
                    "Enter the letters that are green: ");
            WordHandler.getGreens(sc, MyList);
        }

        // List<String> greys = Arrays.asList("a", "d", "i", "u", "e", "k", "n", "t");
        // MyList.addGrey(greys);
        // MyList.addGreen("o", 2);
        // MyList.add(new String[] { "_", "o", "_", "_", "_" });

        // boolean result = MyList.calculate("proms");
        // System.out.println(result);
        System.out.println("Here are the possible words: ");
        MyList.printWords(MyList.getWords());
    }
}