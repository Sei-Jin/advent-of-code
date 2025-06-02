package aoc.event.year2019;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test {
    
    @SafeVarargs
    public static <T> List<T> mutableListOf(T... elements) {
        var list = new ArrayList<T>(elements.length);
        list.addAll(Arrays.asList(elements));
        return list;
    }
    
    @Test
    void example1() {
        var input = mutableListOf(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50);
        assertEquals(List.of(3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50),
            Day02.runProgram(input));
    }
    
    @Test
    void example2() {
        var input = mutableListOf(1, 0, 0, 0, 99);
        assertEquals(List.of(2, 0, 0, 0, 99), Day02.runProgram(input));
    }
    
    @Test
    void example3() {
        var input = mutableListOf(2, 3, 0, 3, 99);
        assertEquals(List.of(2, 3, 0, 6, 99), Day02.runProgram(input));
    }
    
    @Test
    void example4() {
        var input = mutableListOf(2, 4, 4, 5, 99, 0);
        assertEquals(List.of(2, 4, 4, 5, 99, 9801), Day02.runProgram(input));
    }
    
    @Test
    void example5() {
        var input = mutableListOf(1, 1, 1, 4, 99, 5, 6, 0, 99);
        assertEquals(List.of(30, 1, 1, 4, 2, 5, 6, 0, 99), Day02.runProgram(input));
    }
}
