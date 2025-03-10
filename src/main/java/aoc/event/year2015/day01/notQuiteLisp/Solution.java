package aoc.event.year2015.day01.notQuiteLisp;

import aoc.Runner;
import aoc.Solver;

public class Solution implements Solver {
    
    private static String line;
    
    public Solution(String input) {
        line = input;
    }
    
    /// @return the floor level the instructions take Santa to.
    @Override
    public Integer partOne() {
        var floorLevel = 0;
        
        for (var i = 0; i < line.length(); i++) {
            switch (line.charAt(i)) {
                case '(' -> floorLevel++;
                case ')' -> floorLevel--;
            }
        }
        
        return floorLevel;
    }
    
    /// @return the position of the character that causes Santa to first enter the basement, or -1
    /// if Santa never enters the basement.
    @Override
    public Integer partTwo() {
        var floorLevel = 0;
        
        for (var i = 0; i < line.length(); i++) {
            switch (line.charAt(i)) {
                case '(' -> floorLevel++;
                case ')' -> floorLevel--;
            }
            
            if (floorLevel < 0) {
                return i + 1;
            }
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        Runner.runAndPrint(2015, 1);
    }
}
