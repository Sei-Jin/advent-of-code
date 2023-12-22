package year2023.Day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * --- Day 2: Cube Conundrum ---
 */
public class Part1 {
    public static void main(String[] args) throws FileNotFoundException {

        File inFile = new File("input.txt");
        Scanner in = new Scanner(inFile);

        int gameID = 1;
        int totalID = 0;

        // Read in each line one at a time
        while (in.hasNextLine()) {
            String line = in.nextLine();

            // Create and initialize hashmap
            HashMap<String, Integer> cubes = new HashMap<>();
            cubes.put("red", 0);
            cubes.put("green", 0);
            cubes.put("blue", 0);

            // Split the line into subsets of cubes
            String[] subset = line.split("[:;]");
            for (int i = 1; i < subset.length; i++) {

                // Split the subsets of the cubes into sets of cubes
                String[] set = subset[i].split(",");

                for (String s : set) {
                    Scanner cubeScan = new Scanner(s);

                    int cubeCount = cubeScan.nextInt();
                    String cubeColour = cubeScan.next();

                    if (cubes.get(cubeColour) < cubeCount) {
                        cubes.replace(cubeColour, cubeCount);
                    }

                    cubeScan.close();
                }
            }

            // If the game was possible, add its ID value to the running total
            if (cubes.get("red") <= 12 && cubes.get("green") <= 13 && cubes.get("blue") <= 14) {
                totalID += gameID;
            }

            gameID++;
        }

        System.out.println(totalID);

        in.close();
    }
}