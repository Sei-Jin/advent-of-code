package aoc.event.year2022.day02.rockPaperScissors;

import aoc.Runner;
import aoc.Solver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution implements Solver {
    
    private final List<Draw> draws;
    
    public Solution(String input) {
        draws = parse(input);
    }
    
    private static List<Draw> parse(String input) {
        final var draws = new ArrayList<Draw>();
        
        input.lines().forEach(line -> {
            final var values =  line.split(" ");
            
            final var first = values[0].charAt(0);
            final var second = values[1].charAt(0);
            
            draws.add(new Draw(first, second));
        });
        
        return draws;
    }
    
    /// @return the total score if everything goes exactly according to the strategy guide.
    @Override
    public Object partOne() {
        var totalScore = 0;
        
        final var opponentsPossibleChoices = getOpponentsPossibleChoices();
        final var ourPossibleChoices = getOurPossibleChoices();
        
        for (final var draw : draws) {
            final var opponentsChoice = opponentsPossibleChoices.get(draw.first);
            final var ourChoice = ourPossibleChoices.get(draw.second);
            
            final var outcome = determineOutcome(opponentsChoice, ourChoice);
            totalScore += outcome.score + ourChoice.score;
        }
        
        return totalScore;
    }
    
    /// @return the total score if everything goes exactly according to the strategy guide with the
    /// Elf's updated instructions.
    @Override
    public Object partTwo() {
        var totalScore = 0;
        
        final var opponentsPossibleChoices = getOpponentsPossibleChoices();
        final var possibleOutcomes = getPossibleOutcomes();
        
        for (final var draw : draws) {
            final var opponentsChoice = opponentsPossibleChoices.get(draw.first);
            final var outcome = possibleOutcomes.get(draw.second);
            
            final var ourChoice = determineChoice(opponentsChoice, outcome);
            
            totalScore += outcome.score + ourChoice.score;
        }
        
        return totalScore;
    }
    
    public static Outcome determineOutcome(Choice opponentsChoice, Choice ourChoice) {
        Outcome outcome;
        
        if (opponentsChoice == Choice.ROCK && ourChoice == Choice.PAPER
                || opponentsChoice == Choice.PAPER && ourChoice == Choice.SCISSORS
                || opponentsChoice == Choice.SCISSORS && ourChoice == Choice.ROCK) {
            outcome = Outcome.WIN;
        } else if (opponentsChoice == ourChoice) {
            outcome = Outcome.DRAW;
        } else {
            outcome = Outcome.LOSS;
        }
        
        return outcome;
    }
    
    public static Choice determineChoice(Choice opponentsChoice, Outcome outcome) {
        return switch (outcome) {
            case WIN -> opponentsChoice.losesAgainst();
            case DRAW -> opponentsChoice;
            case LOSS -> opponentsChoice.winsAgainst();
        };
    }
    
    private static HashMap<Character, Choice> getOpponentsPossibleChoices() {
        final var opponentsPossibleChoices = new HashMap<Character, Choice>();
        
        opponentsPossibleChoices.put('A', Choice.ROCK);
        opponentsPossibleChoices.put('B', Choice.PAPER);
        opponentsPossibleChoices.put('C', Choice.SCISSORS);
        
        return opponentsPossibleChoices;
    }
    
    private static HashMap<Character, Choice> getOurPossibleChoices() {
        final var ourPossibleChoices = new HashMap<Character, Choice>();
        
        ourPossibleChoices.put('X', Choice.ROCK);
        ourPossibleChoices.put('Y', Choice.PAPER);
        ourPossibleChoices.put('Z', Choice.SCISSORS);
        
        return ourPossibleChoices;
    }
    
    private static Map<Character, Outcome> getPossibleOutcomes() {
        final var possibleOutcomes = new HashMap<Character, Outcome>();
        
        possibleOutcomes.put('X', Outcome.LOSS);
        possibleOutcomes.put('Y', Outcome.DRAW);
        possibleOutcomes.put('Z', Outcome.WIN);
        
        return possibleOutcomes;
    }
    
    public enum Choice {
        ROCK(1),
        PAPER(2),
        SCISSORS(3);
        
        final int score;
        
        Choice(int score) {
            this.score = score;
        }
        
        public Choice losesAgainst() {
            return values()[(this.ordinal() + 1) % (values().length)];
        }
        
        public Choice winsAgainst() {
            return values()[(values().length + this.ordinal() - 1) % values().length];
        }
    }
    
    public enum Outcome {
        WIN(6),
        LOSS(0),
        DRAW(3);
        
        final int score;
        
        Outcome(int score) {
            this.score = score;
        }
    }
    
    private record Draw(char first, char second) {}
    
    public static void main(String[] args) {
        Runner.runAndPrint(2022, 2);
    }
}
