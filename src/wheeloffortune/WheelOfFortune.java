/*
* CMPSC 261, Section 1
* Fall 2016
* Instructor: Phil O'Connell
* Student: Courtney Soda
* ID: cjs5844
 */
package wheeloffortune;

import java.util.Scanner;

public class WheelOfFortune {

    public static void main(String[] args) {
        System.out.println("Welcome to my Wheel of Fortune game!");
        Scanner keyboard = new Scanner(System.in);
        String userChoice = "";
        //Text menu of choices: spin the wheel, buy a vowel, solve the puzzle
        // or quit the game quit has functionality EXIT PROGRAM

        System.out.println("Choose the appropriate number for selection: \n"
                + "A. Spin the Wheel \nB. Buy a vowel \nC. Solve the puzzle \n"
                + "D. Quit the Game\nE. Test letter input");

        userChoice = keyboard.nextLine();
        // print back what choice they made
        if (userChoice.equals("A")) {
            System.out.println("You choose to Spint the Wheel");
        } else if (userChoice.equals("B")) {
            System.out.println("You choose to Buy a Vowel");
        } else if (userChoice.equals("C")) {
            System.out.println("You choose to Solve the Puzzle");
        } else if (userChoice.equals("D")) {
            System.out.println("You choose to Quit the Game");
            //Quit the game
            
        } //Testing option
        else if (userChoice.equals("E")) {
            System.out.println("This is test letter input");
            String letterChoice = "";
            //Prompt user to enter a letter
            System.out.println("Please choose a letter");
            //ERROR if not a letter
            while (!keyboard.hasNext("[A-Za-z]")) {
                System.out.println("Error, Please enter a letter!");
                //read input
                keyboard.next();
            }
            //read input
            letterChoice = keyboard.next();
            //Print letter back to screen
            System.out.println(letterChoice);

        }
    }

}
