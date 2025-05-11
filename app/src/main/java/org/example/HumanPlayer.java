package org.example;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private char symbol;
    private Scanner scanner;

    public HumanPlayer(char symbol, Scanner scanner) {
        this.symbol = symbol;
        this.scanner = scanner;
    }

    @Override
    public int makeMove(Board board) {
        while (true) {
            System.out.print("Enter position (1-9): ");
            String input = scanner.next();
            try {
                int pos = Integer.parseInt(input);
                if (pos >= 1 && pos <= 9 && board.isCellEmpty(pos)) {
                    return pos;
                }
            } catch (NumberFormatException e) {
                // Not an integer
            }
            System.out.println("Invalid input! Please enter an available number (1-9).");
        }
    }

    @Override
    public char getSymbol() {
        return symbol;
    }
}