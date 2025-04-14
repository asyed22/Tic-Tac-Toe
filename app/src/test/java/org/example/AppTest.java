package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    private App game;

    @BeforeEach
    void setUp() {
        game = new App();
        game.initializeBoard();
    }

    @Test
    void testInitializeBoard() {
        char[][] board = game.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(' ', board[i][j], "Board should be initialized with empty cells.");
            }
        }
    }

    @Test
    void testMakeMove() {
        // First move should be X
        game.makeMove(5);
        assertEquals('X', game.getBoard()[1][1], "Cell 5 should be marked with X.");

        // After switch, next move should be O
        game.switchPlayer();
        game.makeMove(1);
        assertEquals('O', game.getBoard()[0][0], "Cell 1 should be marked with O.");
    }

    @Test
    void testCheckWin() {
        // First move X
        game.makeMove(1); // X
        assertEquals('X', game.getCurrentPlayer());
        
        game.switchPlayer();
        game.makeMove(4); // O
        assertEquals('O', game.getCurrentPlayer());
        
        game.switchPlayer();
        game.makeMove(2); // X
        assertEquals('X', game.getCurrentPlayer());
        
        game.switchPlayer();
        game.makeMove(5); // O
        assertEquals('O', game.getCurrentPlayer());
        
        game.switchPlayer();
        game.makeMove(3); // X

        assertTrue(game.checkWin(), "Player X should win with a horizontal line.");
    }

    @Test
    void testIsBoardFull() {
        // Alternate moves between X and O
        game.makeMove(1); // X
        game.switchPlayer();
        game.makeMove(2); // O
        game.switchPlayer();
        game.makeMove(3); // X
        game.switchPlayer();
        game.makeMove(4); // O
        game.switchPlayer();
        game.makeMove(5); // X
        game.switchPlayer();
        game.makeMove(6); // O
        game.switchPlayer();
        game.makeMove(7); // X
        game.switchPlayer();
        game.makeMove(8); // O
        game.switchPlayer();
        game.makeMove(9); // X

        assertTrue(game.isBoardFull(), "Board should be full.");
    }

    @Test
    void testSwitchPlayer() {
        // Initial player should be X
        assertEquals('X', game.getCurrentPlayer(), "Initial player should be X.");

        game.switchPlayer();
        assertEquals('O', game.getCurrentPlayer(), "Player should switch to O.");

        game.switchPlayer();
        assertEquals('X', game.getCurrentPlayer(), "Player should switch back to X.");
    }
}