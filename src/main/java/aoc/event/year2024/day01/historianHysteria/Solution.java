package aoc.event.year2024.day01.historianHysteria;

import aoc.PuzzleRunner;
import aoc.PuzzleSolver;

import java.io.BufferedReader;
import java.util.*;
import java.util.regex.Pattern;

/// 2024 Day 1 - Historian Hysteria
public class Solution implements PuzzleSolver {

    /// Pattern for each line of the puzzle input
    private static final Pattern LINE_PATTERN = Pattern.compile("(\\d+)\\s+(\\d+)");

    private static IdLists ID_LISTS;

    /// This record class stores the data for the two lists of ids.
    ///
    /// @param firstList  the first list of ids.
    /// @param secondList the second list of ids.
    private record IdLists(List<Integer> firstList, List<Integer> secondList) {}

    /// Parses the puzzle input for the ids in each list.
    ///
    /// The puzzle input is in the form:
    ///
    /// ```
    /// A...B
    /// A...B
    /// A...B
    ///```
    ///
    /// Where:
    /// - Each `.` represents a space and should be ignored.
    /// - Each `A` represents an id in the first list.
    /// - Each `B` represents an id in the second list.
    ///
    /// The puzzle input is parsed line by line, where the first id is placed in the first list
    /// and the second id is placed in the second list. Also note that the ids can be longer
    /// than a single digit.
    ///
    /// @param reader the puzzle input.
    @Override
    public void parse(BufferedReader reader) {
        final var firstList = new ArrayList<Integer>();
        final var secondList = new ArrayList<Integer>();

        reader.lines()
                .forEach(line -> {
                    final var matcher = LINE_PATTERN.matcher(line);

                    if (matcher.find()) {
                        firstList.add(Integer.valueOf(matcher.group(1)));
                        secondList.add(Integer.valueOf(matcher.group(2)));
                    } else {
                        throw new IllegalArgumentException(
                                String.format("Unexpected format in input: %s", line)
                        );
                    }
                });

        ID_LISTS = new IdLists(firstList, secondList);
    }

    /// Calculates the total distance between the ids of the two lists.
    ///
    /// The total distance is calculated by comparing the ids in each list from smallest to
    /// largest, finding the difference between them, and adding it to a running total.
    ///
    /// Time Complexity: O(n log n)
    /// - The id lists are sorted when calculating the total distance.
    ///
    /// Space Complexity: O(n)
    /// - All ids from the puzzle input are parsed and then stored.
    ///
    /// @return the total distance between the two lists of ids.
    @Override
    public Object partOne() {
        Collections.sort(ID_LISTS.firstList());
        Collections.sort(ID_LISTS.secondList());

        var totalDistance = 0;

        for (var index = 0; index < ID_LISTS.firstList().size(); index++) {
            final var first = ID_LISTS.firstList().get(index);
            final var second = ID_LISTS.secondList().get(index);
            totalDistance += Math.abs(first - second);
        }

        return totalDistance;
    }

    /// Calculates the similarity score between the two lists of ids.
    ///
    /// To calculate the similarity score between the two lists, the ids in one list are
    /// multiplied by their frequency in the other list and added to a running total.
    ///
    /// Here each id in the first list is multiplied by its frequency in the second list, but
    /// the calculation could have been done the other way around, where each id in the second
    /// list is multiplied by its frequency in the first.
    ///
    /// Time Complexity: O(n)
    /// - All methods are done in linear or constant time.
    ///
    /// Space Complexity: O(n)
    /// - The entire puzzle input is stored.
    ///
    /// @return the similarity score between the two lists of ids.
    @Override
    public Object partTwo() {
        final var secondIdListFrequencies = calculateFrequencies(ID_LISTS.secondList);

        var similarityScore = 0;

        for (final var id : ID_LISTS.firstList) {
            final var frequency = secondIdListFrequencies.getOrDefault(id, 0);
            similarityScore += id * frequency;
        }

        return similarityScore;
    }

    /// Calculates a frequency map of the ids in the list.
    ///
    /// @param idList a list of ids.
    /// @return a frequency map of the ids in the list.
    private static Map<Integer, Integer> calculateFrequencies(List<Integer> idList) {
        final var idFrequencies = new HashMap<Integer, Integer>();

        for (final var id : idList) {
            final var frequency = idFrequencies.getOrDefault(id, 0) + 1;
            idFrequencies.put(id, frequency);
        }

        return idFrequencies;
    }

    public static void main(String[] args) {
        PuzzleRunner.runAndPrint(2024, 1);
    }
}
