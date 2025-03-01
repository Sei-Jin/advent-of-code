package aoc.event.year2020.day04.passportProcessing;

import aoc.Runner;
import aoc.Solver;

import java.util.*;

public class Solution implements Solver {
    
    private final List<Map<String, String>> fieldMaps;
    private final Set<String> mandatoryFields;
    
    public Solution(String input) {
        fieldMaps = parse(input);
        mandatoryFields = createMandatoryFields();
    }
    
    private Set<String> createMandatoryFields() {
        final var fields = new HashSet<String>();
        
        fields.add("byr");
        fields.add("iyr");
        fields.add("eyr");
        fields.add("hgt");
        fields.add("hcl");
        fields.add("ecl");
        fields.add("pid");
        
        return fields;
    }
    
    private static List<Map<String, String>> parse(String input) {
        final var fieldMaps = new ArrayList<Map<String, String>>();
        
        final var passports = input
            .replaceAll("\n", " ")
            .split(" {2}");
        
        for (final var passport : passports) {
            final var fieldMap = new HashMap<String, String>();
            final var pairs = passport.split(" ");
            
            for (final var pair : pairs) {
                final var elements = pair.split(":");
                fieldMap.put(elements[0], elements[1]);
            }
            
            fieldMaps.add(fieldMap);
        }
        
        return fieldMaps;
    }
    
    @Override
    public Integer partOne() {
        var validCount = 0;
        
        for (final var fieldMap : fieldMaps) {
            if (fieldMap.keySet().containsAll(mandatoryFields)) {
                validCount++;
            }
        }
        
        return validCount;
    }
    
    @Override
    public Integer partTwo() {
        return 0;
    }
    
    public static void main(String[] args) {
        Runner.runAndPrint(2020, 4);
    }
}
