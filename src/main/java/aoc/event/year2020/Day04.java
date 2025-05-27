package aoc.event.year2020;

import aoc.DeprecatedSolver2;

import java.util.*;
import java.util.regex.Pattern;

/// # [2020-04: Passport Processing](https://adventofcode.com/2020/day/4)
public class Day04 implements DeprecatedSolver2 {
    
    private static final Set<String> mandatoryFields = createMandatoryFields();
    private static final Set<String> colourSet = createColourSet();
    
    private final List<Map<String, String>> fieldMaps;
    
    public Day04(String input) {
        fieldMaps = parse(input);
    }
    
    private static Set<String> createMandatoryFields() {
        final var fields = new HashSet<String>();
        
        for (final var field : Field.values()) {
            if (field.mandatory) {
                fields.add(field.key);
            }
        }
        
        return fields;
    }
    
    private static Set<String> createColourSet() {
        final var colours = new HashSet<String>();
        
        for (final var value : EyeColour.values()) {
            colours.add(value.colour);
        }
        
        return colours;
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
        var validCount = 0;
        
        for (final var fieldMap : fieldMaps) {
            if (!fieldMap.keySet().containsAll(mandatoryFields)) {
                continue;
            }
            
            if (Field.hasValidFields(fieldMap)) {
                validCount++;
            }
        }
        
        return validCount;
    }
    
    private enum Field {
        BIRTH_YEAR("byr", true),
        ISSUE_YEAR("iyr", true),
        EXPIRATION_YEAR("eyr", true),
        HEIGHT("hgt", true),
        HAIR_COLOR("hcl", true),
        EYE_COLOR("ecl", true),
        PASSPORT_ID("pid", true),
        COUNTRY_ID("cid", false);
        
        private final String key;
        private final boolean mandatory;
        
        Field(String key, boolean mandatory) {
            this.key = key;
            this.mandatory = mandatory;
        }
        
        private static final Pattern HEIGHT_PATTERN = Pattern.compile("^(\\d+)(\\w+)$");
        private static final Pattern HAIR_COLOR_PATTERN = Pattern.compile("^#[a-f0-9]{6}$");
        private static final Pattern PASSPORT_ID_PATTERN = Pattern.compile("^[0-9]{9}$");
        
        public static boolean hasValidFields(Map<String, String> fieldMap) {
            boolean validity = true;
            
            validity &= validBirthYear(fieldMap.get(Field.BIRTH_YEAR.key));
            validity &= validIssueYear(fieldMap.get(Field.ISSUE_YEAR.key));
            validity &= validExpirationYear(fieldMap.get(Field.EXPIRATION_YEAR.key));
            validity &= validHeight(fieldMap.get(Field.HEIGHT.key));
            validity &= validHairColor(fieldMap.get(Field.HAIR_COLOR.key));
            validity &= validEyeColor(fieldMap.get(Field.EYE_COLOR.key));
            validity &= validPassportId(fieldMap.get(Field.PASSPORT_ID.key));
            
            return validity;
        }
        
        private static boolean validBirthYear(String birthYear) {
            if (notNumeric(birthYear)) {
                return false;
            }
            
            final var year = Integer.parseInt(birthYear);
            return year >= 1920 && year <= 2002;
        }
        
        private static boolean validIssueYear(String issueYear) {
            if (notNumeric(issueYear)) {
                return false;
            }
            
            final var year = Integer.parseInt(issueYear);
            return year >= 2010 && year <= 2020;
        }
        
        private static boolean validExpirationYear(String expirationYear) {
            if (notNumeric(expirationYear)) {
                return false;
            }
            
            final var year = Integer.parseInt(expirationYear);
            return year >= 2020 && year <= 2030;
        }
        
        private static boolean validHeight(String height) {
            final var matcher = HEIGHT_PATTERN.matcher(height);
            
            if (!matcher.find()) {
                return false;
            }
            
            final var magnitude = Integer.parseInt(matcher.group(1));
            final var units = matcher.group(2);
            
            return switch (units) {
                case "cm" -> magnitude >= 150 && magnitude <= 193;
                case "in" -> magnitude >= 59 && magnitude <= 76;
                default -> false;
            };
        }
        
        private static boolean validHairColor(String hairColor) {
            return HAIR_COLOR_PATTERN.matcher(hairColor).find();
        }
        
        private static boolean validEyeColor(String eyeColor) {
            return colourSet.contains(eyeColor);
        }
        
        private static boolean validPassportId(String id) {
            return PASSPORT_ID_PATTERN.matcher(id).find();
        }
        
        private static boolean notNumeric(String string) {
            for (int i = 0; i < string.length(); i++) {
                final var character = string.charAt(i);
                
                if (!Character.isDigit(character)) {
                    return true;
                }
            }
            
            return false;
        }
    }
    
    private enum EyeColour {
        AMBER("amb"),
        BLUE("blu"),
        BROWN("brn"),
        GREY("gry"),
        GREEN("grn"),
        HAZEL("hzl"),
        OTHER("oth");
        
        private final String colour;
        
        EyeColour(String colour) {
            this.colour = colour;
        }
    }
}
