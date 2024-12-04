package com.quinnbrockmyre.wordleproject.utils;

import java.util.*;

public class WordList { 
    /*A set of all 5 letter words */
    private final HashSet<String> possibleWords;
    /*A map of yellow letters sorted by each Integer position, keys are nums 1-5, values are sets of each character that cannot be there */
    private final HashMap<Integer, HashSet<String>> yellowMap;
    /*An array of 5 letters representing the current green letters positions */
    private final String[] green;
    /*Constructor for the class */
    public WordList() {
        this.green = new String[5];
        this.possibleWords = new HashSet<>();
        this.yellowMap = new HashMap<>();
    }

    /**
     * Takes letter and int for position and puts it into green array
     * does not take a char but a string for each letter
     * 
     * @param letter the letter to add
     * @param i the postition
     */
    public void addGreen(String letter, int i) {
        green[i] = letter;
    }

    /**
     * Takes a String array and adds given yellow letters to the yellowMap
     * 
     * @param yellowLetters the array of a certain number of yellow letters
     */
    public void add(String[] yellowLetters) {
        for(int i = 1; i <= yellowLetters.length; i++) {
            if(!Objects.equals(yellowLetters[i], "_")) {
                yellowMap.get(i).add(yellowLetters[i]);
            }
        }
    }

    /**
     * method to add a 5 letter word to the possible words set
     * 
     * @param word the word to add
     */
    public void add(String word) {
        possibleWords.add(word);
    }

    /**
     * gets size of possibleWords set
     * 
     * @return size of set
     */
    public int size() {
        return possibleWords.size();
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(String word : possibleWords) {
            builder.append(word).append("\n");
        }
        return builder.toString();
    }
}
