package aoc;

import java.io.BufferedReader;

public interface Solver {
    void parse(BufferedReader reader);
    Object partOne();
    Object partTwo();
}
