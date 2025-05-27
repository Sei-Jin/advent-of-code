package aoc.event.year2016;

import aoc.DeprecatedSolver2;

import java.util.List;
import java.util.regex.Pattern;

/// # [2016-08: Two-Factor Authentication](https://adventofcode.com/2016/day/8)
public class Day08 implements DeprecatedSolver2<Integer> {
    
    private static final int SCREEN_WIDTH = 50;
    private static final int SCREEN_HEIGHT = 6;
    
    private static List<String> lines;
    
    public Day08(String input) {
        lines = input.lines().toList();
    }
    
    @Override
    public Integer partOne() {
        final var screen = new int[SCREEN_HEIGHT][SCREEN_WIDTH];
        
        for (final var line : lines) {
            final var command = Command.of(line);
            
            switch (command) {
                case RECTANGLE -> {
                    final var columns = command.first();
                    final var rows = command.second();
                    
                    for (int row = 0; row < rows; row++) {
                        for (int column = 0; column < columns; column++) {
                            screen[row][column] = 1;
                        }
                    }
                }
                case ROTATE_ROW -> {
                    final var row = command.first();
                    final var offset = command.second();
                    final var normalizedOffset = offset % SCREEN_WIDTH;
                    
                    final var temp = new int[SCREEN_WIDTH];
                    
                    for (int column = 0; column < SCREEN_WIDTH; column++) {
                        final var newColumn = (column + normalizedOffset) % SCREEN_WIDTH;
                        temp[newColumn] = screen[row][column];
                    }
                    
                    for (int column = 0; column < temp.length; column++) {
                        screen[row][column] = temp[column];
                    }
                }
                case ROTATE_COLUMN -> {
                    final var column = command.first();
                    final var offset = command.second();
                    final var normalizedOffset = offset % SCREEN_HEIGHT;
                    
                    final var temp = new int[SCREEN_HEIGHT];
                    
                    for (int row = 0; row < SCREEN_HEIGHT; row++) {
                        final var newRow = (row + normalizedOffset) % SCREEN_HEIGHT;
                        temp[newRow] = screen[row][column];
                    }
                    
                    for (int row = 0; row < temp.length; row++) {
                        screen[row][column] = temp[row];
                    }
                }
            }
            
            for (int i = 1; i <= SCREEN_HEIGHT; i++) {
                for (int j = 1; j <= SCREEN_WIDTH; j++) {
                    System.out.print(screen[i - 1][j - 1] + " ");
                    if (j % 5 == 0) {
                        System.out.print("    ");
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
        
        var count = 0;
        
        for (int i = 0; i < SCREEN_HEIGHT; i++) {
            for (int j = 0; j < SCREEN_WIDTH; j++) {
                if (screen[i][j] > 0) {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    @Override
    public Integer partTwo() {
        return 0;
    }
    
    private enum Command {
        RECTANGLE("rect (\\d+)x(\\d+)"),
        ROTATE_COLUMN("rotate column x=(\\d+) by (\\d+)"),
        ROTATE_ROW("rotate row y=(\\d+) by (\\d+)");
        
        private final Pattern pattern;
        private int first;
        private int second;
        
        Command(String String) {
            this.pattern = Pattern.compile(String);
        }
        
        public int first() {
            return first;
        }
        
        public int second() {
            return second;
        }
        
        public static Command of(String string) {
            for (final var command : Command.values()) {
                if (command.parse(string)) {
                    return command;
                }
            }
            
            throw new IllegalArgumentException("Input did not match any commands: " + string);
        }
        
        private boolean parse(String string) {
            final var matcher = this.pattern.matcher(string);
            
            if (matcher.find()) {
                this.first = Integer.parseInt(matcher.group(1));
                this.second = Integer.parseInt(matcher.group(2));
                return true;
            }
            
            return false;
        }
    }
}
