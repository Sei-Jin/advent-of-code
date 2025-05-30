package aoc.runner;

public class Main {
    
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No arguments given.");
        }
        else if (args.length >= 3) {
            System.out.println("Too many arguments given.");
        }
        var year = Integer.parseInt(args[0]);
        var day = Integer.parseInt(args[1]);
        Runner.execute(year, day);
    }
}
