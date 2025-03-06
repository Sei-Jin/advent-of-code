package aoc;

import java.util.Scanner;

public class CommandLineRunner {
    
    public static void main(String[] args) {
        UserInput userInput = retrieveUserInput();
        Runner.runAndPrint(userInput.year(), userInput.day());
    }
    
    /// Retrieves the user input from the command line and stores the data in a new record class.
    ///
    /// @return a new record class that stores the user input.
    private static UserInput retrieveUserInput() {
        Scanner commandLineInput = new Scanner(System.in);
        
        System.out.print("Enter the year for the puzzle you would like to run: ");
        int year = commandLineInput.nextInt();
        
        System.out.print("Enter the day for the puzzle you would like to run: ");
        int day = commandLineInput.nextInt();
        
        commandLineInput.close();
        
        return new UserInput(year, day);
    }
    
    /// Stores the data entered by the user.
    ///
    /// @param year the year of the puzzle.
    /// @param day  the day of the puzzle.
    private record UserInput(int year, int day) {}
}
