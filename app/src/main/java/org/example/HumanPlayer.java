package org.example;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private char symbol;
    private Scanner scanner;

    public HumanPlayer(char symbol) {
        this.symbol = symbol;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public int makeMove(Board board) {
        int pos;
        while (true) {
            System.out.print("Enter position (1-9): ");
            if (scanner.hasNextInt()) {
                pos = scanner.nextInt();
                if (pos >= 1 && pos <= 9 && board.isCellEmpty(pos)) {
                    break;
                }
            } else {
                scanner.next(); // consume invalid input
            }
            System.out.println("Invalid or occupied position. Try again.");
        }
        return pos;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }
}