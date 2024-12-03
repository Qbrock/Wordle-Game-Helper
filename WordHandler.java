import java.io.*;
import java.net.URL; 
import java.util.*;

public class WordHandler {
    /**
     * Gets all used words for wordle EXCEPT the one used today
     * Uses inputStreamReader to get html from wordle answer website
     * 
     * @return returns a hashset of used words
     * @throws IOException throws IOE
     */
    public static HashSet<String> Used_Words() throws IOException{
        try(BufferedReader in = new BufferedReader(new InputStreamReader(new URL("https://www.rockpapershotgun.com/wordle-past-answers").openStream()))) {
            //get the current day of the month
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            String inputLine, last_input, most_recent_word = "";
            int day_of_month = 0;
            boolean updated = false;
            while ((inputLine = in.readLine()) != null) {
                if(inputLine.contains("Below you can find our list of the most recent previous Wordle solutions, both so you can see what Wordle is all about, and so you know which words to avoid guessing for future Wordles.")) {
                    in.readLine();
                    inputLine = in.readLine();
                    last_input = inputLine.substring(inputLine.indexOf("<strong>") + 8, inputLine.indexOf("("));
                    most_recent_word = inputLine.substring(inputLine.indexOf("</a>") - 5, inputLine.indexOf("</a>"));
                    day_of_month = Integer.parseInt(last_input.split(" ")[1].replace(",", ""));
                    if (day_of_month == day) {
                        updated = true;
                    }
                    break;
                }
            }
            in.readLine();
            in.readLine();
            in.readLine();
            in.readLine();
            in.readLine();
            in.readLine();
            HashSet<String> usedWords = new HashSet<>();
            while ((inputLine = in.readLine()) != null && !inputLine.contains("Wordle doesn't repeat words if it can help it. Perhaps one day the New York Times will need to start recycling old words")) {
                if(inputLine.contains("<li>")) {
                    String word = inputLine.substring(inputLine.indexOf("<li>") + 4, inputLine.indexOf("</li>"));
                    if(!usedWords.contains(word) && !word.equals(most_recent_word) && word.length() == 5) {
                        usedWords.add(word.toLowerCase());
                    }
                }
            }
            if(!updated) {
                usedWords.add(most_recent_word.toLowerCase());
            }
            in.close();
            return usedWords;
        }
    }

    /**
     * Takes all used words and compares them to every 5 letter word in existance
     * 
     * @param words the WordList instance we will add the possible words to
     * @throws IOException throws IOE
     */
    public static void possibleWords(WordList words) throws IOException{
        HashSet<String> used_words = Used_Words();
        File file = new File("src/sgb-words.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            if(!used_words.contains(st)) {
                words.add(st);
            }
        }
        br.close();
    }

    /**
     * Gets grey letters from user
     * 
     * @param sc scanner to use
     * @return returns array of grey letters
     */
    public static String[] getGreys(Scanner sc) {
        String letters = sc.nextLine();
        return letters.split("");
    }

    /**
     * Gets yellow letters from user and puts them into instance of WordList
     * 
     * @param sc scanner to use
     * @param MyList instance of WordList
     */
    public static void getYellows(Scanner sc, WordList MyList) {
        int num_of_yellows = Integer.parseInt(sc.nextLine());
        for(int i = 1; i <= num_of_yellows; i++) {
            System.out.println("In 5 chars, enter the letter for each place it is yellow, and a _ each place it is not.");
            String[] temp = sc.nextLine().split("");
            MyList.add(temp);
        }
    }

    /**
     * Gets green letters from user and puts them into instance of WordList 
     * 
     * @param sc scanner to use
     * @param MyList instance of WordList
     */
    public static void getGreens(Scanner sc, WordList MyList) {
        String[] green_letters = sc.nextLine().split("");
        for(int i = 0; i < green_letters.length; i++) {
            if(!green_letters[i].equals("_")) {
                MyList.addGreen(green_letters[i], i);
            }
        }
    }
}
