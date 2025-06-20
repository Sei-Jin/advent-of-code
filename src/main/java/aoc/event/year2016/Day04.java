package aoc.event.year2016;

import aoc.Solver;
import aoc.util.Common;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/// # [2016-04: Security Through Obscurity](https://adventofcode.com/2016/day/4)
public class Day04 implements Solver<Integer, Integer> {
    
    private static final Pattern ROOM_PATTERN = Pattern.compile("([\\w-]+)-(\\d+)\\[(\\w+)]");
    private final List<Room> rooms;
    
    public Day04(String input) {
        rooms = parse(input);
    }
    
    /// Parses the puzzle input for the room data.
    ///
    /// An example room input is in the format: `aa-bbb-cc-dd-e-836[abcde]`.
    /// - The first part of the string, `aa-bbb-cc-dd-e` is the room name.
    /// - The second part of the string, `836` is the sector id.
    /// - The third part of the string, `abcde` is the checksum.
    ///
    /// @param input the puzzle input.
    /// @return a list of rooms from the parsed data.
    private static List<Room> parse(String input) {
        return input
            .lines()
            .map(ROOM_PATTERN::matcher)
            .flatMap(Matcher::results)
            .map(result -> {
                var encryptedName = result.group(1);
                var sectorId = Integer.parseInt(result.group(2));
                var checksum = result.group(3);
                return new Room(encryptedName, sectorId, checksum);
            })
            .toList();
    }
    
    /// Calculates the sum of the sector ids of the real rooms.
    ///
    /// A room is real if its checksum matches.
    ///
    /// @return the sum of the sector ids of the real rooms
    @Override
    public Integer partOne() {
        return rooms
            .stream()
            .filter(room -> {
                var encryptedName = room.encryptedName.replaceAll("-", "");
                var realChecksum = generateChecksum(encryptedName);
                return realChecksum.equals(room.checksum);
            })
            .mapToInt(Room::sectorId)
            .sum();
    }
    
    /// Generates a checksum from the encrypted name.
    ///
    /// A checksum is generated by appending the 5 most common letters in the encrypted name,
    /// with ties broken by alphabetical order.
    ///
    /// @param encryptedName the encrypted name of a room.
    /// @return the checksum of the encrypted name.
    private static String generateChecksum(String encryptedName) {
        var letterCounts = Common.countCharacters(encryptedName);
        var letters = new ArrayList<>(letterCounts.keySet());
        
        var frequencyComparator = createFrequencyComparator(letterCounts);
        letters.sort(frequencyComparator);
        
        var checksumBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            checksumBuilder.append(letters.get(i));
        }
        return checksumBuilder.toString();
    }
    
    /// Creates a comparator to compare the letters by their counts, with ties broken by
    /// alphabetical order.
    ///
    /// The letters should be compared such that the letters with higher counts are placed before
    /// letters with lower counts (non-increasing order). If two letters have the same count,
    /// then ordering is decided alphabetically.
    ///
    /// @param letterCount a map of the characters and their counts.
    /// @return a comparator for the letters and their counts.
    private static Comparator<Character> createFrequencyComparator(
        Map<Character, Integer> letterCount
    ) {
        return (predecessor, successor) -> {
            var predecessors = letterCount.get(predecessor);
            var successors = letterCount.get(successor);
            
            if (predecessors < successors) {
                return 1;
            }
            else if (predecessors.equals(successors)) {
                return predecessor.compareTo(successor);
            }
            else {
                return -1;
            }
        };
    }
    
    /// Finds the sector id of the room that stores North Pole objects.
    ///
    /// All rooms have encrypted names, and each room name must be decrypted before it can be
    /// determined what type of room it is.
    ///
    /// @return the sector id of the North Pole object storage.
    /// @throws IllegalArgumentException if there are no North Pole objects.
    @Override
    public Integer partTwo() {
        return rooms
            .stream()
            .filter(room -> {
                var name = decryptName(room);
                return name.contains("north") && name.contains("pole");
            })
            .findFirst()
            .map(Room::sectorId)
            .orElseThrow(() ->
                new IllegalArgumentException("The North Pole object storage was not found")
            );
    }
    
    /// Decrypts the encrypted name of the room.
    ///
    /// Decryption is performed by implementing a shift-cipher, where each character in the room
    /// name is shifted forward `m` times, where `m` is the sector id of the room.
    ///
    /// Each shift is performed such that `A` turns to `B`, `B` turns to `C`, `Z` turns to `A`,
    /// etc. Dashes are converted to spaces.
    ///
    /// @param room a room.
    /// @return the decrypted name of the room.
    private static String decryptName(Room room) {
        var name = new StringBuilder();
        var offset = room.sectorId % 26;
        
        for (var i = 0; i < room.encryptedName.length(); i++) {
            var character = room.encryptedName.charAt(i);
            
            if (character == '-') {
                name.append(" ");
            }
            else {
                var decrypted = (char) (character + offset);
                if (decrypted > 'z') {
                    decrypted -= 26;
                }
                name.append(decrypted);
            }
        }
        return name.toString();
    }
    
    record Room(String encryptedName, int sectorId, String checksum) {}
}
