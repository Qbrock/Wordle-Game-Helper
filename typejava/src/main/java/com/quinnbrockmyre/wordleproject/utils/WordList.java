package com.quinnbrockmyre.wordleproject.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class WordList { 
    /*A set of all 5 letter words */
    private final HashSet<String> possibleWords;
    /*A map of yellow letters sorted by each Integer position, keys are nums 1-5, values are sets of each character that cannot be there 
     * ie: yellowMap.get(1) = [a, b, c] means that the second letter cannot be a, b, or c
     */
    private final HashMap<Integer, HashSet<String>> yellowMap;
    /*A treeSet of the expected letters, using treeSet for unique 
     * ie: yellowLetters = [a, b, c] means that the word has to contain a, b, and c
    */
    private final HashSet<String> yellowLetters;
    /*An array of 5 letters representing the current green letters positions */
    private final String[] green;
    /*A set of all the grey letters */
    private final HashSet<String> greys;
    /*Constructor for the class */
    public WordList() {
        this.green = new String[5];
        this.possibleWords = new HashSet<>();
        this.yellowMap = new HashMap<>();
        this.yellowLetters = new HashSet<>();
        this.greys = new HashSet<>();
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
        for(int i = 0; i < yellowLetters.length; i++) {
            if(!yellowLetters[i].equals("_")) {
                this.yellowLetters.add(yellowLetters[i].toLowerCase());
                if(yellowMap.get(i) == null) {
                    yellowMap.put(i, new HashSet<>());
                }
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

    public boolean calculate(String word) {
        HashSet<String> otherYellowLetters = new HashSet<>(this.yellowLetters);
        boolean add = true;
        for(int i = 0; i < 5; i++) {
            String currentLetter = word.substring(i, i+1);
            if(greys.contains(currentLetter)) {
                add = false;
                break;
            }
            if(green[i] != null && !green[i].equals(currentLetter)) {
                add = false;
                break;
            }
            if(yellowMap.get(i) != null && yellowMap.get(i).contains(currentLetter)) {
                add = false;
                break;
            }
            //removing the letter from the yellowLetters set if it is in the word
            if(otherYellowLetters.contains(currentLetter)) {
                otherYellowLetters.remove(currentLetter);
            }
        }
        if(!otherYellowLetters.isEmpty()) {
            add = false;
        }
        return add;
    }

    public HashSet<String> getWords() {
        HashSet<String> words = new HashSet<>();
        for(String word : possibleWords) {
            boolean add = calculate(word);
            if(add) {
                words.add(word);
            }
        }
        return words;

    }

    public void printWords(HashSet<String> possibleWords) {
        possibleWords.stream().sorted().forEach(System.out::println);
    }

    public void addGrey(List<String> greyLetters) {
        greys.addAll(greyLetters);
    }

    public String[] getGreen() {
        return green;
    }
}
