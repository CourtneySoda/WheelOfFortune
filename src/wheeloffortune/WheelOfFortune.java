/*
* CMPSC 261, Section 1
* Fall 2016
* Instructor: Phil O'Connell
* Student: Courtney Soda
* ID: cjs5844
 */
package wheeloffortune;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class WheelOfFortune {

    private static final Scanner _keyboard = new Scanner(System.in);

    //Method for Menu Choices Printing out
    private static final List<String> _menuChoices = Arrays.asList(
            "1. Spin the Wheel",
            "2. Buy a Vowel",
            "3. Solve the puzzle",
            "4. Quit the game",
            "", //5
            "", //6
            "", //7
            "8. Toggle puzzle reveal",
            "9. Test letter input"
    );
    //4 is number that signals quit
    private static final int _quitChoiceNumber = 4;

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
    //Allows _guessedLetter to be used in multiple methods
    private static String _guessedLetter = " ";
    
    //Method for putting the puzzle on the screen
    private static void puzzleDisplay() {
        String firstPuzzle = "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG";
        //Iterate over the letters
        for (char puzzleLetter : firstPuzzle.toCharArray()) {
            //Prints out the blanks in the puzzle
            if (_guessedLetter.indexOf(puzzleLetter) == -1) {
                System.out.print("-");
            } else { //Puts puzzleLetter in the puzzle
                System.out.print(puzzleLetter);
            }
        }
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
            //Add letterChoice to _guessedLetter to print out in puzzleDisplay()
            _guessedLetter += letterChoice;
            //If letterChoice is greater than one letter, error
            if (letterChoice.length() != 1) {
                System.out.println("Enter just one letter");
            } else {
                letter = letterChoice.charAt(0);
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

    //Method for Spinning the Wheel to land on a wedge
    private static void spinTheWheel() {
        //Store 24 values in the array
        int wedgeValues[] = {5000, 600, 500, 300, 500, 800, 550, 400, 300, 900, 
            500,300, 900, 600, 400, 300, 800, 350, 450, 700, 300, 600, 0, 1};
        //Randomly selects a wedge value based on place value
        int randomSpinValue = (int)(Math.random() * 23);
        //DISPLAY what they landed
        //Assign 0 equal to bankrupt
        if (randomSpinValue == 0) {
            System.out.println("You spinned Bankrupt");
          //Assign 1 equal to lose a turn
        } else if (randomSpinValue == 1) {
            System.out.println("You spinned Lose a Turn");
        } else {
            //Prints out the value you spinned - a random choice from array
            System.out.println("You Spinned " + wedgeValues[randomSpinValue]);
        }

    }

    //Method for game menu
    private static void gameMenu() {
        int choice = 1;
        //Input variable
        String line = "";
        //Loop for game
        boolean quit = false;
        while (!quit) {
            System.out.println("======================"
                    + "\n=  Wheel of Fortune  ="
                    + "\n======================");

            System.out.println("\n");
            //Prints out puzzle
            puzzleDisplay();
            System.out.println("\n");
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
                    //Method spinTheWheel  when user chooses 1
                    spinTheWheel();
                case 8:
                    //Display Puzzle
                  
                    System.out.println("THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG");
                  
                    
                case 9:
                    //InputLetter returns letter chosen
                    System.out.println("Your letter is: " + inputLetter());
                    break;
            }

        }

    }

    public static void main(String[] args) {

        gameMenu();

    }

}
