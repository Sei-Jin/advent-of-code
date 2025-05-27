package aoc.event.year2016;

import aoc.DeprecatedSolver2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/// # [2016-12: Leonardo's Monorail](https://adventofcode.com/2016/day/12)
public class Day12 implements DeprecatedSolver2<Integer> {
    
    private final List<Instruction> instructions;
    
    public Day12(String input) {
        instructions = parse(input);
    }
    
    private static List<Instruction> parse(String input) {
        return input
            .lines()
            .map(Instruction::of)
            .toList();
    }
    
    private void runInstructions(HashMap<Character, Integer> registers) {
        for (int i = 0; i < instructions.size(); i++) {
            final var instruction = instructions.get(i);
            
            switch (instruction.opcode) {
                case COPY -> {
                    final var xRaw = instruction.arguments.get(0);
                    
                    final var y = instruction.arguments.get(1).charAt(0);
                    final var x = (xRaw.matches("-?\\d+"))
                        ? Integer.parseInt(xRaw) : registers.get(xRaw.charAt(0));
                    
                    registers.put(y, x);
                }
                case INCREASE -> {
                    final var register = instruction.arguments.get(0).charAt(0);
                    final var value = registers.get(register) + 1;
                    registers.put(register, value);
                }
                case DECREASE -> {
                    final var register = instruction.arguments.get(0).charAt(0);
                    final var value = registers.get(register) - 1;
                    registers.put(register, value);
                }
                case JUMP -> {
                    final var jumpRaw = instruction.arguments.get(0);
                    final var determineJump = jumpRaw.matches("-?\\d+") ?
                        Integer.parseInt(jumpRaw) : registers.get(jumpRaw.charAt(0));
                    
                    final var jumpRelative = Integer.parseInt(instruction.arguments.get(1));
                    
                    if (determineJump != 0) {
                        i += jumpRelative - 1;
                    }
                }
            }
        }
    }
    
    @Override
    public Integer partOne() {
        final var registers = new HashMap<Character, Integer>();
        registers.put('a', 0);
        registers.put('b', 0);
        registers.put('c', 0);
        registers.put('d', 0);
        
        runInstructions(registers);
        
        return registers.get('a');
    }
    
    @Override
    public Integer partTwo() {
        final var registers = new HashMap<Character, Integer>();
        registers.put('a', 0);
        registers.put('b', 0);
        registers.put('c', 1);
        registers.put('d', 0);
        
        runInstructions(registers);
        
        return registers.get('a');
    }
    
    static class Instruction {
        
        private final Opcode opcode;
        private final List<String> arguments;
        
        public Instruction(Opcode opcode, ArrayList<String> arguments) {
            this.opcode = opcode;
            this.arguments = arguments;
        }
        
        public static Instruction of(String string) {
            for (final var opcode : Opcode.values()) {
                final var matcher = opcode.pattern.matcher(string);
                
                if (matcher.find()) {
                    final var arguments = new ArrayList<String>();
                    
                    for (int i = 1; i <= matcher.groupCount(); i++) {
                        arguments.add(matcher.group(i));
                    }
                    
                    return new Instruction(opcode, arguments);
                }
                
            }
            
            throw new IllegalArgumentException("Input did not match any commands: " + string);
        }
        
        public enum Opcode {
            COPY("cpy (.+) (.+)"),
            INCREASE("inc ([a-z])"),
            DECREASE("dec ([a-z])"),
            JUMP("jnz (.+) (.+)");
            
            private final Pattern pattern;
            
            Opcode(String string) {
                this.pattern = Pattern.compile(string);
            }
        }
    }
}
