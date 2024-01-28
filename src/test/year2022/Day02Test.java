package test.year2022;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static year2022.Day02.*;

public class Day02Test
{
    @Nested
    class DetermineOutcomeTest
    {
        @Test
        void pickRockWin()
        {
            assertEquals(determineOutcome(Choice.SCISSORS, Choice.ROCK), Outcome.WIN);
        }
        
        @Test
        void pickPaperWin()
        {
            assertEquals(determineOutcome(Choice.ROCK, Choice.PAPER), Outcome.WIN);
        }
        
        @Test
        void pickScissorsWin()
        {
            assertEquals(determineOutcome(Choice.PAPER, Choice.SCISSORS), Outcome.WIN);
        }
        
        
        @Test
        void pickRockDraw()
        {
            assertEquals(determineOutcome(Choice.ROCK, Choice.ROCK), Outcome.DRAW);
        }
        
        @Test
        void pickPaperDraw()
        {
            assertEquals(determineOutcome(Choice.PAPER, Choice.PAPER), Outcome.DRAW);
        }
        
        @Test
        void pickScissorsDraw()
        {
            assertEquals(determineOutcome(Choice.SCISSORS, Choice.SCISSORS), Outcome.DRAW);
        }
        
        
        @Test
        void pickRockLose()
        {
            assertEquals(determineOutcome(Choice.PAPER, Choice.ROCK), Outcome.LOSS);
        }
        
        @Test
        void pickPaperLose()
        {
            assertEquals(determineOutcome(Choice.SCISSORS, Choice.PAPER), Outcome.LOSS);
        }
        
        @Test
        void pickScissorsLose()
        {
            assertEquals(determineOutcome(Choice.ROCK, Choice.SCISSORS), Outcome.LOSS);
        }
    }
    
    
    @Nested
    class DetermineChoiceTest
    {
        @Test
        void winsAgainstRock()
        {
            assertEquals(determineChoice(Choice.ROCK, Outcome.WIN), Choice.PAPER);
        }
        
        @Test
        void winsAgainstPaper()
        {
            assertEquals(determineChoice(Choice.PAPER, Outcome.WIN), Choice.SCISSORS);
        }
        
        @Test
        void winsAgainstScissors()
        {
            assertEquals(determineChoice(Choice.SCISSORS, Outcome.WIN), Choice.ROCK);
        }
        
        
        @Test
        void drawsAgainstRock()
        {
            assertEquals(determineChoice(Choice.ROCK, Outcome.DRAW), Choice.ROCK);
        }
        
        @Test
        void drawsAgainstPaper()
        {
            assertEquals(determineChoice(Choice.PAPER, Outcome.DRAW), Choice.PAPER);
        }
        
        @Test
        void drawsAgainstScissors()
        {
            assertEquals(determineChoice(Choice.SCISSORS, Outcome.DRAW), Choice.SCISSORS);
        }
        
        
        @Test
        void losesAgainstRock()
        {
            assertEquals(determineChoice(Choice.ROCK, Outcome.LOSS), Choice.SCISSORS);
        }
        
        @Test
        void losesAgainstPaper()
        {
            assertEquals(determineChoice(Choice.PAPER, Outcome.LOSS), Choice.ROCK);
        }
        
        @Test
        void losesAgainstScissors()
        {
            assertEquals(determineChoice(Choice.SCISSORS, Outcome.LOSS), Choice.PAPER);
        }
    }
}
