package aoc.event.year2016.day04.securityThroughObscurity;

import aoc.PuzzleSolver;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution implements PuzzleSolver
{
    private static final Pattern ROOM_PATTERN = Pattern.compile("([\\w-]+)-(\\d+)\\[(\\w+)]");
    
    private static Room parseRoom(String line)
    {
        Matcher matcher = ROOM_PATTERN.matcher(line);
        
        if (matcher.find())
        {
            String encryptedName = matcher.group(1);
            int sectorId = Integer.parseInt(matcher.group(2));
            String checksum = matcher.group(3);
            
            encryptedName = encryptedName.replaceAll("-", "");
            
            return new Room(encryptedName, sectorId, checksum);
        }
        else
        {
            throw new IllegalArgumentException(
                    "Input line did not match expected pattern: " + line
            );
        }
    }
    
    record Room(String encryptedName, int sectorId, String checksum) {}
    
    @Override
    public Object partOne(List<String> inputLines)
    {
        int sectorIdSum = 0;
        
        for (String line : inputLines)
        {
            Room room = parseRoom(line);
            
            String encryptedName = room.encryptedName.replaceAll("-", "");
            Map<Character, Integer> letterCount = getLetterCount(encryptedName);
            
            List<Character> letters = new ArrayList<>(letterCount.keySet());
            
            Comparator<Character> comparator = getLetterComparator(letterCount);
            letters.sort(comparator);
            
            String checksum = getChecksum(letters);
            boolean realRoom = checksum.equals(room.checksum);
            
            if (realRoom)
            {
                sectorIdSum += room.sectorId;
            }
        }
        
        return sectorIdSum;
    }
    
    private static Map<Character, Integer> getLetterCount(String encryptedName)
    {
        Map<Character, Integer> letterCount = new HashMap<>();
        
        for (int index = 0; index < encryptedName.length(); index++)
        {
            char letter = encryptedName.charAt(index);
            int count = letterCount.getOrDefault(letter, 0) + 1;
            
            letterCount.put(letter, count);
        }
        
        return letterCount;
    }
    
    private static Comparator<Character> getLetterComparator(Map<Character, Integer> letterCount)
    {
        return (predecessor, successor) ->
        {
            int predecessorCount = letterCount.get(predecessor);
            int successorCount = letterCount.get(successor);
            
            if (predecessorCount < successorCount)
            {
                return 1;
            }
            else if (predecessorCount == successorCount)
            {
                return predecessor.compareTo(successor);
            }
            else
            {
                return -1;
            }
        };
    }
    
    private static String getChecksum(List<Character> letters)
    {
        StringBuilder checksumBuilder = new StringBuilder();
        
        for (int index = 0; index < 5; index++)
        {
            checksumBuilder.append(letters.get(index));
        }
        
        return checksumBuilder.toString();
    }
    
    @Override
    public Object partTwo(List<String> inputLines)
    {
        for (String line : inputLines)
        {
            Room room = parseRoom(line);
            String unencryptedName = decryptName(room);
            
            if (unencryptedName.contains("north") && unencryptedName.contains("pole"))
            {
                return room.sectorId;
            }
        }
        
        throw new IllegalArgumentException("No North Pole objects were found");
    }
    
    private static String decryptName(Room room)
    {
        StringBuilder decryptedNameBuilder = new StringBuilder();
        
        int sectorOffset = room.sectorId % 26;
        
        for (int index = 0; index < room.encryptedName.length(); index++)
        {
            char character = room.encryptedName.charAt(index);
            
            if (character == '-')
            {
                decryptedNameBuilder.append(" ");
            }
            else
            {
                char decryptedCharacter = (char) (character + sectorOffset);
                
                if (decryptedCharacter > 'z')
                {
                    decryptedCharacter -= 26;
                }
                
                decryptedNameBuilder.append(decryptedCharacter);
            }
        }
        
        return decryptedNameBuilder.toString();
    }
}
