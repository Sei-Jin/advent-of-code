package aoc;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

public class InputRetriever {
    
    private static final String USER_AGENT =
        "github.com/Sei-Jin/Advent-of-Code by seijin.tufts@gmail.com";
    
    /// Retrieves the puzzle input.
    ///
    /// The puzzle input is retrieved from local storage if present. If the puzzle is not present,
    /// the puzzle input is retrieved from the website, stored in local storage, and then
    /// retrieved from local storage.
    ///
    /// @param puzzle the puzzle.
    /// @return the puzzle input.
    public static String retrieveInput(Puzzle puzzle) {
        final var inputPath = getInputPath(puzzle);
        
        if (!Files.exists(inputPath)) {
            final var input = getInputFromWebsite(puzzle);
            storeInput(inputPath, input);
        }
        
        return getInputFromLocalStorage(inputPath);
    }
    
    /// Returns the file path of the input file.
    ///
    /// @param puzzle the puzzle.
    /// @return the file path of the input file.
    private static Path getInputPath(Puzzle puzzle) {
        final var inputFileString = String.format(
            "input/year%d/day%s.txt",
            puzzle.year(),
            puzzle.getDayWithPadding()
        );
        
        return Path.of(inputFileString);
    }
    
    /// Retrieves the session id from a file.
    ///
    /// The session id must be retrieved from the browser and manually stored in the text file.
    ///
    /// TO find the session id in the browser look for the session cookie in local storage when
    /// visiting the advent of code page while logged in.
    ///
    /// @return the session id.
    private static String retrieveSessionId() {
        try {
            return Files.readString(Path.of("session.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    /// Retrieves the puzzle input for a given puzzle from the Advent of Code website.
    ///
    /// The input is retrieved from the website by making a GET request at the puzzle input URL.
    /// Puzzle inputs are user-specific, so a valid session cookie for a user must be provided.
    ///
    /// @param puzzle the puzzle.
    /// @return the puzzle input.
    private static String getInputFromWebsite(Puzzle puzzle) {
        final var inputURL = String.format(
            "https://adventofcode.com/%d/day/%d/input",
            puzzle.year(),
            puzzle.day()
        );
        
        final var request = HttpRequest.newBuilder()
            .uri(URI.create(inputURL))
            .header("User-Agent", USER_AGENT)
            .header("Cookie", "session=" + retrieveSessionId())
            .GET()
            .build();
        
        final var httpClient = HttpClient.newBuilder()
            .build();
        
        final HttpResponse<String> httpResponse;
        
        try {
            httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        
        if (httpResponse.statusCode() != 200) {
            throw new IllegalStateException("Error: Bad Request to website.");
        } else {
            System.out.println("Successfully retrieved the puzzle input from: " + inputURL);
        }
        
        return httpResponse.body();
    }
    
    /// Writes the puzzle input to a text file.
    ///
    /// @param inputPath the path to the puzzle input.
    /// @param input   the puzzle input.
    private static void storeInput(Path inputPath, String input) {
        try {
            Files.createFile(inputPath);
            System.out.println("Created file: " + inputPath.toAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        try (final var fileWriter = new FileWriter(inputPath.toAbsolutePath().toString())) {
            fileWriter.write(input);
            System.out.println("Wrote to file: " + inputPath.toAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    /// Retrieves the puzzle input from the puzzle input file.
    ///
    /// @param inputPath the path to the puzzle input.
    /// @return the puzzle input.
    private static String getInputFromLocalStorage(Path inputPath) {
        try {
            return Files.readString(inputPath).stripTrailing();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read the file: " + e);
        }
    }
}
