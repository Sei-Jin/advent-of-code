package year2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day02
{
    public static void main(String[] args) throws IOException
    {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        partOne(inputLines);
        partTwo(inputLines);
    }
    
    
    public static void partOne(List<String> inputLines)
    {
        int totalScore = 0;
        
        HashMap<Character, Choice> opponentsPossibleChoices = getOpponentsPossibleChoices();
        HashMap<Character, Choice> ourPossibleChoices = getOurPossibleChoices();
        
        for (String line : inputLines)
        {
            List<Character> choices = getChoices(line);
            
            Choice opponentsChoice = opponentsPossibleChoices.get(choices.getFirst());
            Choice ourChoice = ourPossibleChoices.get(choices.getLast());
            
            Outcome outcome = determineOutcome(opponentsChoice, ourChoice);
            
            totalScore += outcome.score + ourChoice.score;
        }
        
        System.out.println("The total score if everything goes exactly according to the strategy guide is: " + totalScore);
    }
    
    
    private static void partTwo(List<String> inputLines)
    {
        int totalScore = 0;
        
        HashMap<Character, Choice> opponentsPossibleChoices = getOpponentsPossibleChoices();
        HashMap<Character, Outcome> possibleOutcomes = getPossibleOutcomes();
        
        for (String line : inputLines)
        {
            List<Character> choices = getChoices(line);
            
            Choice opponentsChoice = opponentsPossibleChoices.get(choices.getFirst());
            Outcome outcome = possibleOutcomes.get(choices.getLast());
            
            Choice ourChoice = determineChoice(opponentsChoice, outcome);
            
            totalScore += outcome.score + ourChoice.score;
        }
        
        System.out.println("With the Elf's updated instructions, the total score if everything goes exactly" +
                " according to the strategy guide is: " + totalScore);
    }
    
    
    private static List<Character> getChoices(String line)
    {
        return Arrays.stream((line.split(" ")))
                .map(s -> s.charAt(0))
                .toList();
    }
    
    
    public static Outcome determineOutcome(Choice opponentsChoice, Choice ourChoice)
    {
        Outcome outcome;
        
        if (opponentsChoice == Choice.ROCK && ourChoice == Choice.PAPER
                || opponentsChoice == Choice.PAPER && ourChoice == Choice.SCISSORS
                || opponentsChoice == Choice.SCISSORS && ourChoice == Choice.ROCK)
        {
            outcome = Outcome.WIN;
        }
        else if (opponentsChoice == ourChoice)
        {
            outcome = Outcome.DRAW;
        }
        else
        {
            outcome = Outcome.LOSS;
        }
        
        return outcome;
    }
    
    
    public static Choice determineChoice(Choice opponentsChoice, Outcome outcome)
    {
        Choice choice = null;
        
        switch (outcome)
        {
            case WIN -> choice = opponentsChoice.losesAgainst();
            case DRAW -> choice = opponentsChoice;
            case LOSS -> choice = opponentsChoice.winsAgainst();
        }
        
        return choice;
    }
    
    
    private static HashMap<Character, Choice> getOpponentsPossibleChoices()
    {
        HashMap<Character, Choice> opponentsPossibleChoices = new HashMap<>();
        
        opponentsPossibleChoices.put('A', Choice.ROCK);
        opponentsPossibleChoices.put('B', Choice.PAPER);
        opponentsPossibleChoices.put('C', Choice.SCISSORS);
        
        return opponentsPossibleChoices;
    }
    
    
    private static HashMap<Character, Choice> getOurPossibleChoices()
    {
        HashMap<Character, Choice> ourPossibleChoices = new HashMap<>();
        
        ourPossibleChoices.put('X', Choice.ROCK);
        ourPossibleChoices.put('Y', Choice.PAPER);
        ourPossibleChoices.put('Z', Choice.SCISSORS);
        
        return ourPossibleChoices;
    }
    
    
    private static HashMap<Character, Outcome> getPossibleOutcomes()
    {
        HashMap<Character, Outcome> possibleOutcomes = new HashMap<>();
        
        possibleOutcomes.put('X', Outcome.LOSS);
        possibleOutcomes.put('Y', Outcome.DRAW);
        possibleOutcomes.put('Z', Outcome.WIN);
        
        return possibleOutcomes;
    }
    
    
    public enum Choice
    {
        ROCK(1),
        PAPER(2),
        SCISSORS(3);
        
        final int score;
        
        Choice(int score)
        {
            this.score = score;
        }
        
        public Choice losesAgainst()
        {
            return values()[(this.ordinal() + 1) % (values().length)];
        }
        
        public Choice winsAgainst()
        {
            return values()[(values().length + this.ordinal() - 1) % values().length];
        }
    }
    
    
    public enum Outcome
    {
        WIN(6),
        LOSS(0),
        DRAW(3);
        
        final int score;
        
        Outcome(int score)
        {
            this.score = score;
        }
    }
}
