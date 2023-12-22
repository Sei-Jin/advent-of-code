package year2015.Day3;

import javax.swing.text.Position;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Part1 {
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

        Set<String> grid = new HashSet<>();
        grid.add(positionX + " " + positionY);

        int housesVisited = 1;

        for (int i = 0; i < moves.length(); i++) {
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

            String p = positionX + " " + positionY;

            if (!grid.contains(p)) {
                grid.add(p);
                housesVisited++;
            }
        }

        System.out.println(housesVisited);
    }
}
