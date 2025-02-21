package aoc.event.year2022.day02.rockPaperScissors;

import aoc.Runner;
import aoc.Solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution implements Solver {
    
    /// Stores the data for the rounds.
    private final List<Round> rounds;
    
    /// Initializes the solution.
    public Solution(String input) {
        rounds = Collections.unmodifiableList(parse(input));
    }
    
    /// Parses the input for the round data.
    ///
    /// Each line of the puzzle input is in the form `A B`, where `A` and `B` are characters.
    ///
    /// @param input the puzzle input.
    /// @return the data for the rounds.
    private static List<Round> parse(String input) {
        final var draws = new ArrayList<Round>();
        
        input.lines().forEach(line -> {
            final var values = line.split(" ");
            
            final var first = values[0].charAt(0);
            final var second = values[1].charAt(0);
            
            draws.add(new Round(first, second));
        });
        
        return draws;
    }
    
    /// Determines which of the opponents choices the given character maps to.
    ///
    /// @param character a character representing the opponents choice.
    /// @return the opponents choice.
    private static Choice determineOpponentsChoice(char character) {
        return switch (character) {
            case 'A' -> Choice.ROCK;
            case 'B' -> Choice.PAPER;
            case 'C' -> Choice.SCISSORS;
            default -> throw new IllegalStateException("Unexpected value: " + character);
        };
    }
    
    /// Calculates the total score from all rounds played when given our opponents choices and
    /// our own choices.
    ///
    /// @return the total score.
    @Override
    public Integer partOne() {
        var totalScore = 0;
        
        for (final var round : rounds) {
            final var opponentsChoice = determineOpponentsChoice(round.first);
            final var ourChoice = determineOurChoice(round.second);
            
            final var outcome = determineOutcome(opponentsChoice, ourChoice);
            
            totalScore += outcome.score + ourChoice.score;
        }
        
        return totalScore;
    }
    
    /// Determines which choice the given character maps to.
    ///
    /// @param character a character.
    /// @return our choice.
    private static Choice determineOurChoice(char character) {
        return switch (character) {
            case 'X' -> Choice.ROCK;
            case 'Y' -> Choice.PAPER;
            case 'Z' -> Choice.SCISSORS;
            default -> throw new IllegalStateException("Unexpected value: " + character);
        };
    }
    
    /// Determines the outcome of the round.
    ///
    /// @param opponentsChoice the opponents choice.
    /// @param ourChoice our choice.
    /// @return the outcome of the round.
    private static Outcome determineOutcome(Choice opponentsChoice, Choice ourChoice) {
        if (opponentsChoice.losesAgainst() == ourChoice) {
            return Outcome.WIN;
        } else if (opponentsChoice == ourChoice) {
            return Outcome.DRAW;
        } else {
            return Outcome.LOSS;
        }
    }
    
    /// Calculates the total score from all rounds played when given our opponents choices and the
    /// round outcomes.
    ///
    /// @return the total score.
    @Override
    public Integer partTwo() {
        var totalScore = 0;
        
        for (final var round : rounds) {
            final var opponentsChoice = determineOpponentsChoice(round.first);
            final var outcome = determineOutcome(round.second);
            
            final var ourChoice = determineOurChoice(opponentsChoice, outcome);
            
            totalScore += outcome.score + ourChoice.score;
        }
        
        return totalScore;
    }
    
    /// Determines the outcome that maps to the given character.
    ///
    /// @param character a character.
    /// @return the outcome.
    private static Outcome determineOutcome(char character) {
        return switch (character) {
            case 'X' -> Outcome.LOSS;
            case 'Y' -> Outcome.DRAW;
            case 'Z' -> Outcome.WIN;
            default -> throw new IllegalStateException("Unexpected value: " + character);
        };
    }
    
    /// Determines the choice we should make when knowing our opponents choice and the round
    /// outcome.
    ///
    /// @param opponents the choice our opponent will make.
    /// @param outcome   the outcome of the round.
    /// @return the choice we should make to ensure the correct round outcome.
    private static Choice determineOurChoice(Choice opponents, Outcome outcome) {
        return switch (outcome) {
            case WIN -> opponents.losesAgainst();
            case DRAW -> opponents;
            case LOSS -> opponents.winsAgainst();
        };
    }
    
    /// Stores the possible choices for a player with their score.
    private enum Choice {
        ROCK(1),
        PAPER(2),
        SCISSORS(3);
        
        private final int score;
        
        Choice(int score) {
            this.score = score;
        }
        
        /// @return the choice that loses against the current choice.
        private Choice losesAgainst() {
            return switch (this) {
                case ROCK -> PAPER;
                case PAPER -> SCISSORS;
                case SCISSORS -> ROCK;
            };
        }
        
        /// @return the choice that wins against the current choice.
        private Choice winsAgainst() {
            return switch (this) {
                case ROCK -> SCISSORS;
                case PAPER -> ROCK;
                case SCISSORS -> PAPER;
            };
        }
    }
    
    /// Stores the possible outcomes of a round with their score values.
    private enum Outcome {
        WIN(6),
        LOSS(0),
        DRAW(3);
        
        private final int score;
        
        Outcome(int score) {
            this.score = score;
        }
    }
    
    /// Stores the data for a round.
    private record Round(char first, char second) {}
    
    /// Runs the solution.
    public static void main(String[] args) {
        Runner.runAndPrint(2022, 2);
    }
}
