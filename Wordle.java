/*
 * Wordle.java
 *
 * Version:
 *     17.0.3
 *
 * Revisions:
 *     3
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Class that represents the wordle game.
 *
 * @author      Vishal Panchidi
 * @author      Neeraj Bandi
 */

public class Wordle{
    /**
     * Indicates the number of words to play with
     */
    public static int soManyWordToPLayWith = 0;
    /**
     * String array that stores the program input.
     */
    public static final String[] theWords = new String[10231];
    /**
     * Scanner to read the userInput.
     */
    public static final Scanner readGuess = new Scanner(System.in);
    /**
     * Boolean value that represents whether the word is guessed.
     */
    public static boolean wordGuessed = false;

    /**
     * Reads the Words from file and accumulates the program Input.
     * @param fileName : name of the file in the program.
     */

    public static void readWordsFromFile(String fileName) {
        try (
                BufferedReader input = new BufferedReader( new FileReader(fileName))){
            int counter = 0;
            while ( ( theWords[counter++] = input.readLine() ) != null ){
                // Stores the number of words to play with.
                soManyWordToPLayWith ++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }}

    /**
     * Checks whether character is available in the entire String
     * @param userInput: Input entered by the user.
     * @param wordChar : Character array of Program Input
     * @return boolean value based on the character availability.
     */

    public static boolean isItAvailable(char userInput,char[]wordChar){
        for(int index=0;index<wordChar.length;index++){
            if(userInput==wordChar[index]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Compares the userInput and programInput and prints the appropriate characters as designated.
     * @param word : program input
     * @param userInput : Input entered by the User
     */
    public static void compareWords(String word, String userInput){
        System.out.println(userInput);
        // Converts the word to character array
        char [] wordChar = word.toCharArray();
        // Coverts the userInput to character array
        char [] userInputChar = userInput.toCharArray();
        for(int index=0;index<wordChar.length;index++){
            if(wordChar[index]==userInputChar[index]){
                // Prints * when the character position and value matches.
                System.out.print("*");
            }
            else{
                // prints _ when the character value matches but position doesn't match and x when character position and value doesn't match.
                System.out.print(isItAvailable(userInputChar[index],wordChar)?"_":"x");
            }
        }
        System.out.println();
        if(userInput.equals(word)){
            // boolean value to represent the guessed word.
            wordGuessed = true;
            System.out.println("well done");
        }
    }

    /**
     * Reads the user Input from console
     * @return User Input
     */
    public static String readUserInput() {
        String guess = "";
        System.out.print("> ");
        if ( readGuess.hasNext() ) {
            guess = readGuess.nextLine();
        }
        return guess;
    }

    /**
     * Starts the wordle game.
     */

    public static void start(){
        int count = 0;
        // Converts program input of the word into lower case to ensure that the lower cases are compared.
        String word = getWord().toLowerCase();
        while(count<5 && !wordGuessed){
            // Converts user input of the word into lower case.
            System.out.print(count+1);
            String userInput = readUserInput().toLowerCase();
            if(userInput.length() == 5){
                compareWords(word,userInput);
                count++;
            }
        }
    }

    /**
     * Randomly selects the word from the given file and return the word.
     * @return the randomly selected word from file.
     */
    public static String getWord() {
        return theWords[new Random().nextInt(soManyWordToPLayWith)];
    }

    /**
     * Prints the required statements and starts the game.
     */
    public static void playWordle() {
        System.out.println("_ indicates the letter is in the word but in the wrong");
        System.out.println("* indicates the letter is in the word and correct spot.");
        System.out.println("x indicates the letter is not in the word.");
        System.out.println("Try to guess the word in 5 tries.");
        start();
    }

    /**
     * reads the words from file and initiates the Wordle play.
     * @param args: Haven't used this attribute.
     */
    public static void main( String [] args ) {
        // reads the text file - this file has to be in the local directory
        readWordsFromFile("src/5_char_word");
        playWordle();
    }
}