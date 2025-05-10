package org.example;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to Tic-Tac-Toe!");
            boolean playAgain;

            do {
                System.out.println("\nWhat kind of game would you like to play?");
                System.out.println("1. Human vs. Human");
                System.out.println("2. Human vs. Computer (Human first)");
                System.out.println("3. Computer vs. Human (Computer first)");

                int gameChoice = 0;
                while (true) {
                    System.out.print("\nWhat is your selection? ");
                    if (scanner.hasNextInt()) {
                        gameChoice = scanner.nextInt();
                        if (gameChoice >= 1 && gameChoice <= 3) break;
                    } else {
                        scanner.next(); // consume invalid input
                    }
                    System.out.println("Invalid input! Please enter 1, 2, or 3.");
                }

                Game game = new Game();
                game.start(gameChoice);  // Now matches the method signature

                System.out.print("\nWould you like to play again (yes/no)? ");
                String again = scanner.next().trim().toLowerCase();
                while (!again.equals("yes") && !again.equals("no")) {
                    System.out.print("Invalid input! Please type 'yes' or 'no': ");
                    again = scanner.next().trim().toLowerCase();
                }
                playAgain = again.equals("yes");

            } while (playAgain);

            System.out.println("\nGoodbye!");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}