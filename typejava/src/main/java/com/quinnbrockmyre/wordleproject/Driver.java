package com.quinnbrockmyre.wordleproject;
import java.io.IOException;
import java.util.Scanner;

import com.quinnbrockmyre.wordleproject.utils.WordHandler;
import com.quinnbrockmyre.wordleproject.utils.WordList;
public class Driver {
    /**
     * Main driver method for running the WordleHelper
     * Will prompt user for input based on users current game of wordle
     * Will prompt for each grey letter, amount of yellow letters and their locations and all green letters
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

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the grey letters that have been used so far (not separated by spaces): ");
        String[] greys = WordHandler.getGreys(sc);

        System.out.println("How many different yellow letters do you have?");
        WordHandler.getYellows(sc, MyList);

        System.out.println("Enter the letters that are green so far (not separated by spaces) and use underscores for empty slots: ");
        WordHandler.getGreens(sc, MyList);

        MyList.calculateWords();

    }
}