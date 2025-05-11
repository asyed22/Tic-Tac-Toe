package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void testHumanPlayerImplementsInterface() {
        HumanPlayer player = new HumanPlayer('X');
        assertTrue(player instanceof Player);
        assertEquals('X', player.getSymbol());
    }

    @Test
    void testComputerPlayerImplementsInterface() {
        ComputerPlayer player = new ComputerPlayer('O');
        assertTrue(player instanceof Player);
        assertEquals('O', player.getSymbol());
    }
}