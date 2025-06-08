package aoc.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Common {
    
    public static Map<Character, Integer> countCharacters(String string) {
        var counts = new HashMap<Character, Integer>();
        for (var i = 0; i < string.length(); i++) {
            var letter = string.charAt(i);
            var count = counts.getOrDefault(letter, 0) + 1;
            counts.put(letter, count);
        }
        return counts;
    }
    
    public static <T> Map<T, Integer> countEntries(List<T> list) {
        var counts = new HashMap<T, Integer>();
        for (var element : list) {
            var count = counts.getOrDefault(element, 0) + 1;
            counts.put(element, count);
        }
        return counts;
    }
}
