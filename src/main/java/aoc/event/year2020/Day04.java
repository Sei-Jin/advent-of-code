package aoc.event.year2020;

import aoc.Solver;

import java.util.*;
import java.util.regex.Pattern;

/// # [2020-04: Passport Processing](https://adventofcode.com/2020/day/4)
public class Day04 implements Solver<Integer, Integer> {
    
    private final List<Map<String, String>> fieldMaps;
    
    public Day04(String input) {
        fieldMaps = parse(input);
    }
    
    private static List<Map<String, String>> parse(String input) {
        var fieldMaps = new ArrayList<Map<String, String>>();
        
        var passports = input
            .replaceAll("\n", " ")
            .split(" {2}");
        
        for (var passport : passports) {
            var fieldMap = new HashMap<String, String>();
            var pairs = passport.split(" ");
            for (var pair : pairs) {
                var elements = pair.split(":");
                fieldMap.put(elements[0], elements[1]);
            }
            fieldMaps.add(fieldMap);
        }
        return fieldMaps;
    }
    
    @Override
    public Integer partOne() {
        return (int) fieldMaps
            .stream()
            .filter(fieldMap -> fieldMap.keySet().containsAll(Field.mandatoryFields()))
            .count();
    }
    
    @Override
    public Integer partTwo() {
        return (int) fieldMaps
            .stream()
            .filter(fieldMap -> fieldMap.keySet().containsAll(Field.mandatoryFields()))
            .filter(Field::hasAllValidFields)
            .count();
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
        
        private static final Set<String> mandatoryFields = createMandatorySet();
        private static final Pattern HEIGHT_PATTERN = Pattern.compile("^(\\d+)(\\w+)$");
        private static final Pattern HAIR_COLOR_PATTERN = Pattern.compile("^#[a-f0-9]{6}$");
        private static final Pattern PASSPORT_ID_PATTERN = Pattern.compile("^[0-9]{9}$");
        private final String key;
        private final boolean mandatory;
        
        Field(String key, boolean mandatory) {
            this.key = key;
            this.mandatory = mandatory;
        }
        
        public static Set<String> mandatoryFields() {
            return mandatoryFields;
        }
        
        private static Set<String> createMandatorySet() {
            var fields = new HashSet<String>();
            for (var field : Field.values()) {
                if (field.mandatory) {
                    fields.add(field.key);
                }
            }
            return fields;
        }
        
        public static boolean hasAllValidFields(Map<String, String> fieldMap) {
            boolean validity = true;
            validity &= isValidBirthYear(fieldMap.get(Field.BIRTH_YEAR.key));
            validity &= isValidIssueYear(fieldMap.get(Field.ISSUE_YEAR.key));
            validity &= isValidExpirationYear(fieldMap.get(Field.EXPIRATION_YEAR.key));
            validity &= isValidHeight(fieldMap.get(Field.HEIGHT.key));
            validity &= isValidHairColor(fieldMap.get(Field.HAIR_COLOR.key));
            validity &= isValidEyeColor(fieldMap.get(Field.EYE_COLOR.key));
            validity &= isValidPassportId(fieldMap.get(Field.PASSPORT_ID.key));
            return validity;
        }
        
        private static boolean isValidBirthYear(String birthYear) {
            if (isNotNumeric(birthYear)) {
                return false;
            }
            var year = Integer.parseInt(birthYear);
            return year >= 1920 && year <= 2002;
        }
        
        private static boolean isValidIssueYear(String issueYear) {
            if (isNotNumeric(issueYear)) {
                return false;
            }
            var year = Integer.parseInt(issueYear);
            return year >= 2010 && year <= 2020;
        }
        
        private static boolean isValidExpirationYear(String expirationYear) {
            if (isNotNumeric(expirationYear)) {
                return false;
            }
            var year = Integer.parseInt(expirationYear);
            return year >= 2020 && year <= 2030;
        }
        
        private static boolean isValidHeight(String height) {
            var matcher = HEIGHT_PATTERN.matcher(height);
            if (!matcher.find()) {
                return false;
            }
            var magnitude = Integer.parseInt(matcher.group(1));
            var units = matcher.group(2);
            return switch (units) {
                case "cm" -> magnitude >= 150 && magnitude <= 193;
                case "in" -> magnitude >= 59 && magnitude <= 76;
                default -> false;
            };
        }
        
        private static boolean isValidHairColor(String hairColor) {
            return HAIR_COLOR_PATTERN.matcher(hairColor).find();
        }
        
        private static boolean isValidEyeColor(String eyeColor) {
            return EyeColour.colourSet().contains(eyeColor);
        }
        
        private static boolean isValidPassportId(String id) {
            return PASSPORT_ID_PATTERN.matcher(id).find();
        }
        
        private static boolean isNotNumeric(String string) {
            for (int i = 0; i < string.length(); i++) {
                var character = string.charAt(i);
                if (!Character.isDigit(character)) {
                    return true;
                }
            }
            return false;
        }
        
        private enum EyeColour {
            AMBER("amb"),
            BLUE("blu"),
            BROWN("brn"),
            GREY("gry"),
            GREEN("grn"),
            HAZEL("hzl"),
            OTHER("oth");
            
            private static final Set<String> colourSet = createColourSet();
            private final String colour;
            
            EyeColour(String colour) {
                this.colour = colour;
            }
            
            public static Set<String> colourSet() {
                return colourSet;
            }
            
            private static Set<String> createColourSet() {
                var colours = new HashSet<String>();
                for (var value : EyeColour.values()) {
                    colours.add(value.colour);
                }
                return colours;
            }
        }
    }
}
