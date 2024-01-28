package test.year2022;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static year2022.Day02.*;

public class Day02Tests
{
    
    @Test
    void pickRockWin()
    {
        assertEquals(determineWinner(Choice.SCISSORS, Choice.ROCK), Outcome.WIN);
    }
    
    @Test
    void pickRockDraw()
    {
        assertEquals(determineWinner(Choice.ROCK, Choice.ROCK), Outcome.DRAW);
    }
    
    @Test
    void pickRockLose()
    {
        assertEquals(determineWinner(Choice.PAPER, Choice.ROCK), Outcome.LOSS);
    }
    
    
    
    @Test
    void pickPaperWin()
    {
        assertEquals(determineWinner(Choice.ROCK, Choice.PAPER), Outcome.WIN);
    }
    
    @Test
    void pickPaperDraw()
    {
        assertEquals(determineWinner(Choice.PAPER, Choice.PAPER), Outcome.DRAW);
    }
    
    @Test
    void pickPaperLose()
    {
        assertEquals(determineWinner(Choice.SCISSORS, Choice.PAPER), Outcome.LOSS);
    }
    
    
    
    @Test
    void pickScissorsWin()
    {
        assertEquals(determineWinner(Choice.PAPER, Choice.SCISSORS), Outcome.WIN);
    }
    
    @Test
    void pickScissorsDraw()
    {
        assertEquals(determineWinner(Choice.SCISSORS, Choice.SCISSORS), Outcome.DRAW);
    }
    
    @Test
    void pickScissorsLose()
    {
        assertEquals(determineWinner(Choice.ROCK, Choice.SCISSORS), Outcome.LOSS);
    }
}
