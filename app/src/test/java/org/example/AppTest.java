package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void appHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }

    @Test
    void testInitializeBoard() {
        App game = new App();
        game.initializeBoard();

        char[][] board = game.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(' ', board[i][j], "Board should be initialized with empty cells.");
            }
        }
    }

    @Test
    void testMakeMove() {
        App game = new App();
        game.initializeBoard();

        game.makeMove(5);
        assertEquals('X', game.getBoard()[1][1], "Cell 5 should be marked with X.");

        game.switchPlayer();
        game.makeMove(1);
        assertEquals('O', game.getBoard()[0][0], "Cell 1 should be marked with O.");
    }

    @Test
    void testCheckWin() {
        App game = new App();
        game.initializeBoard();

        // Simulate a winning condition for Player X
        game.makeMove(1);
        game.makeMove(4);
        game.makeMove(2);
        game.makeMove(5);
        game.makeMove(3);

        assertTrue(game.checkWin(), "Player X should win with a horizontal line.");
    }

    @Test
    void testIsBoardFull() {
        App game = new App();
        game.initializeBoard();

        // Fill the board without a winner
        game.makeMove(1);
        game.makeMove(2);
        game.makeMove(3);
        game.makeMove(4);
        game.makeMove(5);
        game.makeMove(6);
        game.makeMove(7);
        game.makeMove(8);
        game.makeMove(9);

        assertTrue(game.isBoardFull(), "Board should be full.");
    }

    @Test
    void testSwitchPlayer() {
        App game = new App();
        game.initializeBoard();

        assertEquals('X', game.getCurrentPlayer(), "Initial player should be X.");

        game.switchPlayer();
        assertEquals('O', game.getCurrentPlayer(), "Player should switch to O.");

        game.switchPlayer();
        assertEquals('X', game.getCurrentPlayer(), "Player should switch back to X.");
    }
}
