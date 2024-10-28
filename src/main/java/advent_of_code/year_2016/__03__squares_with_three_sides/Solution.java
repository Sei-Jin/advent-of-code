package advent_of_code.year_2016.__03__squares_with_three_sides;

import advent_of_code.PuzzleSolver;

import java.util.*;

public class Solution implements PuzzleSolver
{
    /**
     * @param inputLines the puzzle input.
     * @return the number of possible triangles.
     */
    @Override
    public Object partOne(List<String> inputLines)
    {
        List<List<Integer>> triangles = getInputValues(inputLines);
        
        int possibleTriangles = 0;
     
        for (List<Integer> triangle : triangles)
        {
            if (possibleTriangle(triangle))
            {
                possibleTriangles++;
            }
        }
        
        return possibleTriangles;
    }
    
    
    /**
     * @param inputLines the puzzle input.
     * @return the puzzle input as a {@code List} of {@code List} of {@code Integers}.
     */
    private static List<List<Integer>> getInputValues(List<String> inputLines)
    {
        List<List<Integer>> inputValues = new ArrayList<>();
        
        for (String line : inputLines)
        {
            inputValues.add(Arrays.stream(line.trim().split("\\s+"))
                    .map(Integer::parseInt)
                    .toList()
            );
        }
        
        return inputValues;
    }
    
    
    /**
     * Determines whether a triangle could be formed with the given {@code sideLengths} or not. A triangle is
     * considered possible when the sum of any two sides are larger than the remaining side.
     *
     * @param sideLengths the side lengths of a triangle.
     * @return true if the side lengths are possible, or false otherwise.
     */
    private static boolean possibleTriangle(List<Integer> sideLengths)
    {
        if (sideLengths.size() != 3)
        {
            throw new IllegalArgumentException("There must be exactly 3 sides to form a triangle.");
        }
        
        int side1 = sideLengths.get(0);
        int side2 = sideLengths.get(1);
        int side3 = sideLengths.get(2);
        
        return (side1 + side2 > side3) &&
                (side1 + side3 > side2) &&
                (side2 + side3 > side1);
    }
    
    
    /**
     * @param inputLines the puzzle input.
     * @return the number of possible triangles in the vertical groups.
     */
    @Override
    public Object partTwo(List<String> inputLines)
    {
        List<List<Integer>> inputValues = getInputValues(inputLines);
        
        int possibleTriangles = 0;
        
        for (int row = 0; row < inputValues.size(); row += 3)
        {
            for (int column = 0; column < inputValues.getFirst().size(); column++)
            {
                List<Integer> triangle = Arrays.asList(
                        inputValues.get(row).get(column),
                        inputValues.get(row + 1).get(column),
                        inputValues.get(row + 2).get(column)
                );
                
                if (possibleTriangle(triangle))
                {
                    possibleTriangles++;
                }
            }
        }
        
        return possibleTriangles;
    }
}
