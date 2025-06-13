package aoc.event.year2016;

import aoc.Solver;

import java.util.List;
import java.util.regex.Pattern;

/// # [2016-08: Two-Factor Authentication](https://adventofcode.com/2016/day/8)
public class Day08 implements Solver<Integer, String> {
    
    private static final int SCREEN_WIDTH = 50;
    private static final int SCREEN_HEIGHT = 6;
    
    private static List<Command> commands;
    
    public Day08(String input) {
        commands = parse(input);
    }
    
    private static List<Command> parse(String input) {
        return input
            .lines()
            .map(Command::parse)
            .toList();
    }
    
    private static int[][] applyCommands(List<Command> commands) {
        var screen = new int[SCREEN_HEIGHT][SCREEN_WIDTH];
        for (var command : commands) {
            var type = command.commandType;
            switch (type) {
                case RECTANGLE -> {
                    var columns = command.first;
                    var rows = command.second;
                    
                    for (int row = 0; row < rows; row++) {
                        for (int column = 0; column < columns; column++) {
                            screen[row][column] = 1;
                        }
                    }
                }
                case ROTATE_ROW -> {
                    var row = command.first;
                    var offset = command.second;
                    var normalizedOffset = offset % SCREEN_WIDTH;
                    
                    var temp = new int[SCREEN_WIDTH];
                    for (int column = 0; column < SCREEN_WIDTH; column++) {
                        var newColumn = (column + normalizedOffset) % SCREEN_WIDTH;
                        temp[newColumn] = screen[row][column];
                    }
                    System.arraycopy(temp, 0, screen[row], 0, temp.length);
                }
                case ROTATE_COLUMN -> {
                    var column = command.first;
                    var offset = command.second;
                    var normalizedOffset = offset % SCREEN_HEIGHT;
                    
                    var temp = new int[SCREEN_HEIGHT];
                    for (int row = 0; row < SCREEN_HEIGHT; row++) {
                        var newRow = (row + normalizedOffset) % SCREEN_HEIGHT;
                        temp[newRow] = screen[row][column];
                    }
                    for (int row = 0; row < temp.length; row++) {
                        screen[row][column] = temp[row];
                    }
                }
            }
        }
        return screen;
    }
    
    @Override
    public Integer partOne() {
        var screen = applyCommands(commands);
        return count(screen);
    }
    
    private static int count(int[][] screen) {
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
    public String partTwo() {
        var screen = applyCommands(commands);
        print(screen);
        return "Output";
    }
    
    private static void print(int[][] screen) {
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
    
    private enum CommandType {
        
        RECTANGLE("rect (\\d+)x(\\d+)"),
        ROTATE_COLUMN("rotate column x=(\\d+) by (\\d+)"),
        ROTATE_ROW("rotate row y=(\\d+) by (\\d+)");
        
        private final Pattern pattern;
        
        CommandType(String regex) {
            this.pattern = Pattern.compile(regex);
        }
    }
    
    private record Command(CommandType commandType, int first, int second) {
        
        public static Command parse(String input) {
            for (var command : CommandType.values()) {
                var matcher = command.pattern.matcher(input);
                if (matcher.matches()) {
                    int first = Integer.parseInt(matcher.group(1));
                    int second = Integer.parseInt(matcher.group(2));
                    return new Command(command, first, second);
                }
            }
            throw new IllegalArgumentException("Input did not match any commands: " + input);
        }
    }
}
