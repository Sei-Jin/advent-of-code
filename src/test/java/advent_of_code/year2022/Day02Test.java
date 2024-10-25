package advent_of_code.year2022;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test
{
    @Test
    void DetermineOutcomeTest()
    {
        assertEquals(Outcome.WIN, determineOutcome(Choice.SCISSORS, Choice.ROCK));
        assertEquals(Outcome.WIN, determineOutcome(Choice.ROCK, Choice.PAPER));
        assertEquals(Outcome.WIN, determineOutcome(Choice.PAPER, Choice.SCISSORS));
        assertEquals(Outcome.DRAW, determineOutcome(Choice.ROCK, Choice.ROCK));
        assertEquals(Outcome.DRAW, determineOutcome(Choice.PAPER, Choice.PAPER));
        assertEquals(Outcome.DRAW, determineOutcome(Choice.SCISSORS, Choice.SCISSORS));
        assertEquals(Outcome.LOSS, determineOutcome(Choice.PAPER, Choice.ROCK));
        assertEquals(Outcome.LOSS, determineOutcome(Choice.SCISSORS, Choice.PAPER));
        assertEquals(Outcome.LOSS, determineOutcome(Choice.ROCK, Choice.SCISSORS));
    }
    
    
    @Test
    void DetermineChoiceTest()
    {
        assertEquals(Choice.PAPER, determineChoice(Choice.ROCK, Outcome.WIN));
        assertEquals(Choice.SCISSORS, determineChoice(Choice.PAPER, Outcome.WIN));
        assertEquals(Choice.ROCK, determineChoice(Choice.SCISSORS, Outcome.WIN));
        assertEquals(Choice.ROCK, determineChoice(Choice.ROCK, Outcome.DRAW));
        assertEquals(Choice.PAPER, determineChoice(Choice.PAPER, Outcome.DRAW));
        assertEquals(Choice.SCISSORS, determineChoice(Choice.SCISSORS, Outcome.DRAW));
        assertEquals(Choice.SCISSORS, determineChoice(Choice.ROCK, Outcome.LOSS));
        assertEquals(Choice.ROCK, determineChoice(Choice.PAPER, Outcome.LOSS));
        assertEquals(Choice.PAPER, determineChoice(Choice.SCISSORS, Outcome.LOSS));
    }
}
