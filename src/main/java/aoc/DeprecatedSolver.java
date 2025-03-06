package aoc;

import java.util.List;

/// @deprecated because the new solver interface should be used instead.
@Deprecated(since = "2025-02-14", forRemoval = true)
public interface DeprecatedSolver {
    
    Object partOne(List<String> input);
    
    Object partTwo(List<String> input);
}
