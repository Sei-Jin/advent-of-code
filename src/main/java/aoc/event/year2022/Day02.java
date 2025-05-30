package aoc.event.year2022;

import aoc.Solver;

import java.util.List;

/// # [2022-02: Rock Paper Scissors](https://adventofcode.com/2022/day/2)
public class Day02 implements Solver<Integer, Integer> {
    
    private final List<Round> rounds;
    
    public Day02(String input) {
        rounds = parse(input);
    }
    
    private static List<Round> parse(String input) {
        return input
            .lines()
            .map(line -> {
                var values = line.split(" ");
                var first = values[0].charAt(0);
                var second = values[1].charAt(0);
                return new Round(first, second);
            })
            .toList();
    }
    
    /// Calculates the total score from all rounds played when given our opponents choices and
    /// our own choices.
    @Override
    public Integer partOne() {
        return rounds
            .stream()
            .mapToInt(round -> {
                var opponents = Choice.parseOpponents(round.first);
                var ours = Choice.parseOurs(round.second);
                
                var outcome = Outcome.determine(opponents, ours);
                return outcome.getScore() + ours.getScore();
            })
            .sum();
    }
    
    /// Calculates the total score from all rounds played when given our opponents choices and
    /// the round outcomes.
    @Override
    public Integer partTwo() {
        return rounds
            .stream()
            .mapToInt(round -> {
                var opponents = Choice.parseOpponents(round.first);
                var outcome = Outcome.parse(round.second);
                
                var ours = Choice.determine(opponents, outcome);
                return outcome.getScore() + ours.getScore();
            })
            .sum();
    }
    
    private enum Choice {
        ROCK(1),
        PAPER(2),
        SCISSORS(3);
        
        private final int score;
        
        Choice(int score) {
            this.score = score;
        }
        
        public int getScore() {
            return score;
        }
        
        public static Choice winsAgainst(Choice choice) {
            return switch (choice) {
                case ROCK -> PAPER;
                case PAPER -> SCISSORS;
                case SCISSORS -> ROCK;
            };
        }
        
        public static Choice losesAgainst(Choice choice) {
            return switch (choice) {
                case ROCK -> SCISSORS;
                case PAPER -> ROCK;
                case SCISSORS -> PAPER;
            };
        }
        
        public static Choice parseOpponents(char character) {
            return switch (character) {
                case 'A' -> Choice.ROCK;
                case 'B' -> Choice.PAPER;
                case 'C' -> Choice.SCISSORS;
                default -> throw new IllegalStateException("Unexpected value: " + character);
            };
        }
        
        public static Choice parseOurs(char character) {
            return switch (character) {
                case 'X' -> Choice.ROCK;
                case 'Y' -> Choice.PAPER;
                case 'Z' -> Choice.SCISSORS;
                default -> throw new IllegalStateException("Unexpected value: " + character);
            };
        }
        
        public static Choice determine(Choice choice, Outcome outcome) {
            return switch (outcome) {
                case WIN -> Choice.winsAgainst(choice);
                case DRAW -> choice;
                case LOSS -> Choice.losesAgainst(choice);
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
        
        public int getScore() {
            return score;
        }
        
        public static Outcome determine(Choice opponents, Choice ours) {
            if (ours == Choice.winsAgainst(opponents)) {
                return Outcome.WIN;
            }
            else if (opponents == ours) {
                return Outcome.DRAW;
            }
            else {
                return Outcome.LOSS;
            }
        }
        
        public static Outcome parse(char character) {
            return switch (character) {
                case 'X' -> Outcome.LOSS;
                case 'Y' -> Outcome.DRAW;
                case 'Z' -> Outcome.WIN;
                default -> throw new IllegalStateException("Unexpected value: " + character);
            };
        }
    }
    
    private record Round(char first, char second) {}
}
