package aoc.event.year2015;

import aoc.Solver;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

/// # [2015-04: The Ideal Stocking Stuffer](https://adventofcode.com/2015/day/4)
public class Day04 implements Solver<Integer, Integer> {
    
    private final String secretKey;
    
    public Day04(String input) {
        secretKey = input;
    }
    
    private static int findLowestSuffix(String secretKey, String prefix) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Invalid algorithm", e);
        }
        
        var i = 0;
        var checksum = "";
        while (!checksum.startsWith(prefix)) {
            i++;
            var message = secretKey + i;
            md.update(message.getBytes());
            var digest = md.digest();
            checksum = HexFormat.of().formatHex(digest);
        }
        return i;
    }
    
    @Override
    public Integer partOne() {
        return findLowestSuffix(secretKey, "00000");
    }
    
    @Override
    public Integer partTwo() {
        return findLowestSuffix(secretKey, "000000");
    }
}
