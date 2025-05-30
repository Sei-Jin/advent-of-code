package aoc.runner;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

class Input {
    
    private static final String USER_AGENT =
        "github.com/Sei-Jin/Advent-of-Code by seijin.tufts@gmail.com";
    private static final String SESSION_FILENAME = "session.txt";
    
    /// Retrieves the puzzle input.
    ///
    /// The puzzle input is retrieved from local storage if present. If the puzzle is not present,
    /// the puzzle input is retrieved from the website, stored in local storage, and then
    /// retrieved from local storage.
    public static String retrieve(Solution solution) {
        var path = determinePath(solution);
        if (!Files.exists(path)) {
            var input = fetchHttp(solution);
            cache(path, input);
        }
        return fetchLocal(path);
    }
    
    /// Returns the file path of the input file.
    private static Path determinePath(Solution solution) {
        var input = String.format(
            "input/year%d/day%02d.txt",
            solution.year(),
            solution.day()
        );
        return Path.of(input);
    }
    
    /// Retrieves the session id from a file.
    ///
    /// The session id must be retrieved from the browser and manually stored in the text file.
    ///
    /// To find the session id in the browser look for the session cookie in local storage when
    /// visiting the advent of code page while logged in.
    ///
    /// @return the session id.
    private static String retrieveSessionId() {
        try {
            return Files.readString(Path.of(SESSION_FILENAME));
        }
        catch (IOException e) {
            throw new IllegalStateException(
                "Failed to read the session id from: " + SESSION_FILENAME
            );
        }
    }
    
    /// Retrieves the puzzle input for a given puzzle from the Advent of Code website.
    ///
    /// The input is retrieved from the website by making a GET request at the puzzle input URL.
    /// Puzzle inputs are user-specific, so a valid session cookie for a user must be provided.
    ///
    /// @param solution the puzzle.
    /// @return the puzzle input.
    private static String fetchHttp(Solution solution) {
        var inputURL = String.format(
            "https://adventofcode.com/%d/day/%d/input",
            solution.year(),
            solution.day()
        );
        var request = HttpRequest.newBuilder()
            .uri(URI.create(inputURL))
            .header("User-Agent", USER_AGENT)
            .header("Cookie", "session=" + retrieveSessionId())
            .GET()
            .build();
        
        var client = HttpClient.newBuilder()
            .build();
        HttpResponse<String> response;
        
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }
        catch (IOException | InterruptedException e) {
            throw new IllegalStateException("Failed to send a request to the endpoint.");
        }
        
        if (response.statusCode() != 200) {
            throw new IllegalStateException("Error: Bad Request to website.");
        }
        else {
            System.out.println("Successfully retrieved the puzzle input from: " + inputURL);
        }
        return response.body();
    }
    
    /// Stores the puzzle input in a local file.
    private static void cache(Path path, String input) {
        if (!Files.exists(path.getParent())) {
            try {
                Files.createDirectories(path.getParent());
            }
            catch (IOException e) {
                throw new IllegalStateException(
                    "Parent file did not exist at the provided path: " + path
                );
            }
        }
        
        try {
            Files.createFile(path);
            System.out.println("Created file: " + path.toAbsolutePath());
        }
        catch (IOException e) {
            throw new IllegalStateException("Could not create the file at path: " + path);
        }
        
        var absolutePath = path.toAbsolutePath().toString();
        try (var fileWriter = new FileWriter(absolutePath)) {
            fileWriter.write(input);
            System.out.println("Wrote to file: " + absolutePath);
        }
        catch (IOException e) {
            throw new IllegalStateException(
                "Could not write to the file: " + absolutePath
            );
        }
    }
    
    /// Retrieves the puzzle input from the puzzle input file.
    private static String fetchLocal(Path path) {
        try {
            return Files.readString(path).stripTrailing();
        }
        catch (IOException e) {
            throw new IllegalStateException("Failed to read the file at path: " + path);
        }
    }
}
