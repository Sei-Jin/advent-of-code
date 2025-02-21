package aoc.event.year2022.day02.rockPaperScissors;

import aoc.Runner;
import aoc.Solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution implements Solver {
    
    private final List<Round> rounds;
    
    /// Initializes the solution with the parsed round data.
    ///
    /// Each line of the puzzle input is in the form `A B`, where `A` and `B` are characters that
    /// represent the data for a round.
    ///
    /// @param input the puzzle input.
    public Solution(String input) {
        rounds = Collections.unmodifiableList(parse(input));
    }
    
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
    /// Given our choice and the opponents choice we can calculate the outcome for each round.
    /// The score of a round is calculated by adding up the score values for the outcome and our
    /// choice.
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
    
    private static Choice determineOurChoice(char character) {
        return switch (character) {
            case 'X' -> Choice.ROCK;
            case 'Y' -> Choice.PAPER;
            case 'Z' -> Choice.SCISSORS;
            default -> throw new IllegalStateException("Unexpected value: " + character);
        };
    }
    
    private static Outcome determineOutcome(Choice opponentsChoice, Choice ourChoice) {
        if (opponentsChoice.losesAgainst() == ourChoice) {
            return Outcome.WIN;
        } else if (opponentsChoice == ourChoice) {
            return Outcome.DRAW;
        } else {
            return Outcome.LOSS;
        }
    }
    
    /// Calculates the total score from all rounds played when given our opponents choices and
    /// the round outcomes.
    ///
    /// Given our opponents choice and the outcome of the round we can determine which choice we
    /// should make. The score of a round is calculated by adding up the score values for the
    /// outcome and our choice.
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
    
    private static Outcome determineOutcome(char character) {
        return switch (character) {
            case 'X' -> Outcome.LOSS;
            case 'Y' -> Outcome.DRAW;
            case 'Z' -> Outcome.WIN;
            default -> throw new IllegalStateException("Unexpected value: " + character);
        };
    }
    
    private static Choice determineOurChoice(Choice opponents, Outcome outcome) {
        return switch (outcome) {
            case WIN -> opponents.losesAgainst();
            case DRAW -> opponents;
            case LOSS -> opponents.winsAgainst();
        };
    }
    
    private enum Choice {
        ROCK(1),
        PAPER(2),
        SCISSORS(3);
        
        private final int score;
        
        Choice(int score) {
            this.score = score;
        }
        
        private Choice losesAgainst() {
            return switch (this) {
                case ROCK -> PAPER;
                case PAPER -> SCISSORS;
                case SCISSORS -> ROCK;
            };
        }
        
        private Choice winsAgainst() {
            return switch (this) {
                case ROCK -> SCISSORS;
                case PAPER -> ROCK;
                case SCISSORS -> PAPER;
            };
        }
    }
    
    private enum Outcome {
        WIN(6),
        LOSS(0),
        DRAW(3);
        
        private final int score;
        
        Outcome(int score) {
            this.score = score;
        }
    }
    
    private record Round(char first, char second) {}
    
    /// Runs the solution.
    public static void main(String[] args) {
        Runner.runAndPrint(2022, 2);
    }
}
