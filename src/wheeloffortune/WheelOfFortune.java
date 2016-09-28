/*
* CMPSC 261, Section 1
* Fall 2016
* Instructor: Phil O'Connell
* Student: Courtney Soda
* ID: cjs5844
 */
package wheeloffortune;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

public class WheelOfFortune {

    // Read user input
    private static final Scanner _keyboard = new Scanner(System.in);
    //To get random values
    private static final Random _random = new Random();
    //To Hide letters
    private static boolean revealLetters = false;
    //Establish base winnings
    private static int _winnings = 0;

    private static final List<String> _wedges = Arrays.asList(
            "$5000", "$600", "$500", "$300", "$500", "$800", "$550", "$400", "$300",
            "$900", "$500", "$300", "$900", "BANKRUPT", "$600", "$400", "$300",
            "LOSE A TURN", "$800", "$350", "$450", "$700", "$300", "$600");

    private static final int _wedgeCount = _wedges.size();

    private static String chooseRandomWedgeValue() {
        int randomWedgeIndex = _random.nextInt(_wedgeCount);

        return _wedges.get(randomWedgeIndex);
    }

    //Method for Menu Choices Printing out
    private static final List<String> _menuChoices = Arrays.asList(
            "1. Spin the Wheel",
            "2. Buy a Vowel",
            "3. Solve the puzzle",
            "4. Quit the game"
    );
    //4 is number that signals quit
    private static final int _quitChoiceNumber = 4;
    //Different puzzle choices stored in a list
    private static final List<String> _puzzles = Arrays.asList(
            "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG",
            "PENN STATE ABINGTON",
            "INFORMATION SCIENCES AND TECHNOLOGY"
    );
    //Store size value
    private static final int _puzzlesCount = _puzzles.size();
    // key is guessed character, value will be boolean
    private static Map<Character, Boolean> guessedLetters = new HashMap<>();

    //Method to return a hidden lettered puzzle
    private static String maskPuzzle(String puzzle, boolean revealLetters) {
        StringBuilder maskedPuzzle = new StringBuilder();
        //for each letter in the puzzle 
        for (int i = 0; i < puzzle.length(); i++) {
            char c = puzzle.charAt(i);
            //reveal all letters or guess a letter
            boolean isLetterGuessed = revealLetters || guessedLetters.containsKey(c);
            //hide letter if it has not been guessed/ show blanks
            if (c != ' ' && !isLetterGuessed) {
                c = '_';
            }
            //puts a space between each char
            maskedPuzzle.append(c + " ");
        }
        //String builder to String
        return maskedPuzzle.toString();
    }

    //Method to choose random puzzle
    private static String chooseRandomPuzzle() {
        int randomPuzzleIndex = _random.nextInt(_puzzlesCount);
        //Returns a puzzle
        return _puzzles.get(randomPuzzleIndex);
    }

    //Method to make sure Menu Choice is valid
    private static boolean isValidMenuChoice(int choice) {
        //Makes sure that the number is withing the menuChoice
        if (choice < 1 || choice > _menuChoices.size()) {
            return false;
        }
        //Returns Menu Choice
        int index = choice - 1;
        String menuText = _menuChoices.get(index);
        return !menuText.equals("");
    }

    //Method for inputting a letter
    private static char inputLetter() {
        char letter = ' ';
        boolean finished = false;
        while (!finished) {
            //Ask user to input a letter
            System.out.println("Enter a letter:");
            //User inputs the letter called letterChoice
            String letterChoice = _keyboard.nextLine();
            //If letterChoice is greater than one letter, error
            if (letterChoice.length() != 1) {
                System.out.println("Enter just one letter");
            } else {
                //converts letter to uppercase
                letter = Character.toUpperCase(letterChoice.charAt(0));
                //If letterChoice is not a letter, error
                if (!Character.isLetter(letter)) {
                    System.out.println("That is not a letter");
                } else {
                    finished = true;
                }
            }
        }
        return letter;

    }

    //Method for game menu
    private static void gameMenu() {
        int choice = 1;
        //Input variable
        String line = "";
        //Loop for game
        boolean quit = false;
        String puzzle = chooseRandomPuzzle();
        while (!quit) {
            System.out.println("======================"
                    + "\n=  Wheel of Fortune  ="
                    + "\n======================");

            System.out.println("\n");
            //Prints out puzzle
            System.out.println(maskPuzzle(puzzle, revealLetters));
            System.out.println("\n");

            System.out.println("Winnings: " + _winnings);
            //For each choice in method menuChoices display menu choice
            for (String menuChoice : _menuChoices) {
                if (!menuChoice.equals("")) {

                    System.out.println(menuChoice);
                }
            }
            //Enter in user choice
            System.out.println("Enter choice:");
            //User input
            line = _keyboard.nextLine();
            //Makes sure input is a number
            try {
                choice = Integer.parseInt(line);
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid Input");
                continue;
            }
            //Checks to see if it is a valid choice based on size
            if (!isValidMenuChoice(choice)) {
                System.out.println("Not a menu choice");
                continue;
            }
            System.out.println("You chose: " + _menuChoices.get(choice - 1));

            switch (choice) {
                //When user chooses 4 they quit the game
                case _quitChoiceNumber:
                    quit = true;
                    break;
                case 1:
                    System.out.println("You landed on: " + chooseRandomWedgeValue());
                    char letter = inputLetter();
                    System.out.println("Your letter is: " + letter);
                    guessedLetters.put(letter, true);
                    break;

            }

        }

    }

    public static void main(String[] args) {

        gameMenu();

    }

}
