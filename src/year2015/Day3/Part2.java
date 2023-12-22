package year2015.Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {

        File inputFile = new File("input.txt");
        Scanner in = new Scanner(inputFile);

        String moves = "";
        while (in.hasNextLine()) {
            moves = in.nextLine();
        }
        in.close();

        int positionX = 0;
        int positionY = 0;
        int positionXRobot = 0;
        int positionYRobot = 0;

        Set<String> grid = new HashSet<>();
        grid.add(positionX + " " + positionY);

        int housesVisited = 1;

        for (int i = 0; i < moves.length(); i++) {

            String p = "";

            if (i % 2 == 0) {
                switch (moves.charAt(i)) {
                    case '>': positionX++;
                        break;
                    case '<': positionX--;
                        break;
                    case '^': positionY++;
                        break;
                    case 'v': positionY--;
                        break;
                }
                p = positionX + " " + positionY;

            } else {
                switch (moves.charAt(i)) {
                    case '>': positionXRobot++;
                        break;
                    case '<': positionXRobot--;
                        break;
                    case '^': positionYRobot++;
                        break;
                    case 'v': positionYRobot--;
                        break;
                }
                p = positionXRobot + " " + positionYRobot;
            }

            if (!grid.contains(p)) {
                grid.add(p);
                housesVisited++;
            }
        }

        System.out.println(housesVisited);
    }
}
