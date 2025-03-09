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

        // Check that the board is initialized correctly (all cells are empty)
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

        // Make a move and check if the board is updated correctly
        game.makeMove(5); // Player X moves to position 5
        assertEquals('X', game.getBoard()[1][1], "Cell 5 should be marked with X.");

        game.switchPlayer();
        game.makeMove(1); // Player O moves to position 1
        assertEquals('O', game.getBoard()[0][0], "Cell 1 should be marked with O.");
    }

    @Test
    void testCheckWin() {
        App game = new App();
        game.initializeBoard();

        // Simulate a winning condition for Player X
        game.makeMove(1); // X
        game.makeMove(4); // O
        game.makeMove(2); // X
        game.makeMove(5); // O
        game.makeMove(3); // X

        assertTrue(game.checkWin(), "Player X should win with a horizontal line.");
    }

    @Test
    void testIsBoardFull() {
        App game = new App();
        game.initializeBoard();

        // Fill the board without a winner
        game.makeMove(1); // X
        game.makeMove(2); // O
        game.makeMove(3); // X
        game.makeMove(4); // O
        game.makeMove(5); // X
        game.makeMove(6); // O
        game.makeMove(7); // X
        game.makeMove(8); // O
        game.makeMove(9); // X

        assertTrue(game.isBoardFull(), "Board should be full.");
    }

    @Test
    void testSwitchPlayer() {
        App game = new App();
        game.initializeBoard();

        // Initial player should be X
        assertEquals('X', game.getCurrentPlayer(), "Initial player should be X.");

        // Switch to O
        game.switchPlayer();
        assertEquals('O', game.getCurrentPlayer(), "Player should switch to O.");

        // Switch back to X
        game.switchPlayer();
        assertEquals('X', game.getCurrentPlayer(), "Player should switch back to X.");
    }
}