package aoc;

import java.io.BufferedReader;

public interface PuzzleSolver {
    void parse(BufferedReader reader);
    Object partOne();
    Object partTwo();
}
