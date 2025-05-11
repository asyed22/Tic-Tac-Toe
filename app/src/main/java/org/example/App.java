package org.example;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Tic-Tac-Toe!");
        boolean playAgain;

        do {
            System.out.println("\nWhat kind of game would you like to play?");
            System.out.println("1. Human vs. Human");
            System.out.println("2. Human vs. Computer (Human first)");
            System.out.println("3. Computer vs. Human (Computer first)");

            int gameChoice = getValidIntegerInput(scanner, 1, 3, 
                "What is your selection? ", 
                "Invalid input! Please enter 1, 2, or 3.");

            // Start the selected game
            Game game = new Game(scanner);
            game.start(gameChoice);

            // Get valid play again response (yes/no)
            playAgain = getYesNoInput(scanner, 
                "\nWould you like to play again (yes/no)? ");

        } while (playAgain);

        System.out.println("\nGoodbye!");
        scanner.close();
    }

    private static int getValidIntegerInput(Scanner scanner, int min, int max, 
                                         String prompt, String errorMessage) {
        int input;
        while (true) {
            System.out.print(prompt);
            try {
                input = Integer.parseInt(scanner.next());
                if (input >= min && input <= max) {
                    return input;
                }
            } catch (NumberFormatException e) {
                // Not an integer
            }
            System.out.println(errorMessage);
        }
    }

    private static boolean getYesNoInput(Scanner scanner, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.next().trim().toLowerCase();
            if (input.equals("yes")) {
                return true;
            } else if (input.equals("no")) {
                return false;
            }
            System.out.print("Invalid input! Please type 'yes' or 'no': ");
        }
    }
}