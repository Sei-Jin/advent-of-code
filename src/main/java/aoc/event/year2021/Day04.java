package aoc.event.year2021;

import aoc.Solver;

import java.util.*;
import java.util.stream.Gatherers;
import java.util.stream.IntStream;

/// # [2021-04: Giant Squid](https://adventofcode.com/2021/day/4)
public class Day04 implements Solver<Integer, Integer> {
    
    private static final int BOARD_SIZE = 5;
    private final List<Integer> winners;
    private final List<BiHashMap<Point, Integer>> boards;
    
    public Day04(String input) {
        var lines = input.lines().toList();
        var winningStrings = lines
            .getFirst()
            .split(",");
        winners = Arrays.stream(winningStrings)
            .map(Integer::parseInt)
            .toList();
        boards = parseBoards(lines.subList(2, lines.size()));
    }
    
    private static List<BiHashMap<Point, Integer>> parseBoards(List<String> lines) {
        return lines
            .stream()
            .filter(line -> !line.isEmpty())
            .gather(Gatherers.windowFixed(BOARD_SIZE))
            .map(window -> {
                var board = new BiHashMap<Point, Integer>();
                for (int y = 0; y < window.size(); y++) {
                    var numberStrings = window
                        .get(y)
                        .stripLeading()
                        .split("\\s+");
                    var numbers = Arrays
                        .stream(numberStrings)
                        .map(Integer::parseInt)
                        .toList();
                    for (int x = 0; x < numbers.size(); x++) {
                        board.put(new Point(x, y), numbers.get(x));
                    }
                }
                return board;
            })
            .toList();
    }
    
    private static boolean isBingo(int number, BiHashMap<Point, Integer> board, Set<Integer> seen) {
        var point = board.getKeyByValue(number);
        
        var isHorizontal = IntStream
            .range(0, BOARD_SIZE)
            .allMatch(x -> seen.contains(board.getValueByKey(new Point(x, point.y))));
        
        var isVertical = IntStream
            .range(0, BOARD_SIZE)
            .allMatch(y -> seen.contains(board.getValueByKey(new Point(point.x, y))));
        
        return isHorizontal || isVertical;
    }
    
    private static int calculateFinalScore(
        int winningNumber,
        Set<Integer> boardNumbers,
        Set<Integer> seen
    ) {
        var sumUnmarked = boardNumbers
            .stream()
            .filter(number -> !seen.contains(number))
            .reduce(Integer::sum)
            .orElse(0);
        return sumUnmarked * winningNumber;
    }
    
    /// Calculates the final score of the first winning board.
    @Override
    public Integer partOne() {
        var seen = new HashSet<Integer>();
        for (var number : winners) {
            seen.add(number);
            for (var board : boards) {
                if (!board.containsValue(number)) {
                    continue;
                }
                if (isBingo(number, board, seen)) {
                    return calculateFinalScore(number, board.valueSet(), seen);
                }
            }
        }
        throw new IllegalStateException("No winning bingo boards were found!");
    }
    
    /// Calculates the final score of the last winning board.
    @Override
    public Integer partTwo() {
        var lastWinningNumber = 0;
        BiHashMap<Point, Integer> lastWinningBoard = null;
        
        var potentialBoards = new HashSet<>(boards);
        var seen = new HashSet<Integer>();
        
        outer:
        for (int number : winners) {
            seen.add(number);
            
            var it = potentialBoards.iterator();
            while (it.hasNext()) {
                var board = it.next();
                if (!board.containsValue(number)) {
                    continue;
                }
                if (isBingo(number, board, seen)) {
                    if (potentialBoards.size() == 1) {
                        lastWinningNumber = number;
                        lastWinningBoard = board;
                        break outer;
                    }
                    it.remove();
                }
            }
        }
        if (lastWinningBoard == null) {
            throw new IllegalStateException("No winning bingo boards were found!");
        }
        return calculateFinalScore(lastWinningNumber, lastWinningBoard.valueSet(), seen);
    }
    
    private record Point(int x, int y) {
        
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point(int x1, int y1))) return false;
            return x == x1 && y == y1;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    
    /// Bi-directional HashMap implementation.
    private static class BiHashMap<K, V> extends AbstractMap<K, V> {
        
        private final Map<K, V> keyToValueMap = new HashMap<>();
        private final Map<V, K> valueToKeyMap = new HashMap<>();
        
        @Override
        public V put(K key, V value) {
            if (keyToValueMap.containsKey(key)) {
                V oldValue = keyToValueMap.get(key);
                valueToKeyMap.remove(oldValue);
            }
            if (valueToKeyMap.containsKey(value)) {
                K oldKey = valueToKeyMap.get(value);
                keyToValueMap.remove(oldKey);
            }
            keyToValueMap.put(key, value);
            valueToKeyMap.put(value, key);
            return value;
        }
        
        public V getValueByKey(K key) {
            return keyToValueMap.get(key);
        }
        
        public K getKeyByValue(V value) {
            return valueToKeyMap.get(value);
        }
        
        @Override
        public boolean containsKey(Object key) {
            return keyToValueMap.containsKey(key);
        }
        
        @Override
        public boolean containsValue(Object value) {
            return valueToKeyMap.containsKey(value);
        }
        
        @Override
        public Set<K> keySet() {
            return keyToValueMap.keySet();
        }
        
        public Set<V> valueSet() {
            return valueToKeyMap.keySet();
        }
        
        @Override
        public Collection<V> values() {
            return valueToKeyMap.keySet();
        }
        
        @Override
        public Set<Entry<K, V>> entrySet() {
            return new HashSet<>(keyToValueMap.entrySet());
        }
    }
}
