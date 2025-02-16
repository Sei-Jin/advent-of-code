package aoc;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

public class PuzzleInputRetriever
{
    /// Retrieves the puzzle input.
    ///
    /// The puzzle input is retrieved from local storage if present. If the puzzle is not present,
    /// the puzzle input is retrieved from the website, stored in local storage, and then
    /// retrieved from local storage.
    ///
    /// @param puzzle the puzzle.
    /// @return the puzzle input.
    public static String retrievePuzzleInput(Puzzle puzzle)
    {
        Path inputFilePath = getInputFilePath(puzzle);
        
        if (!Files.exists(inputFilePath))
        {
            String puzzleInput = getPuzzleInputFromWebsite(puzzle);
            storePuzzleInput(inputFilePath, puzzleInput);
        }
        
        return getPuzzleInputFromLocalStorage(inputFilePath);
    }
    
    /// Returns the file path of the input file.
    ///
    /// @param puzzle the puzzle.
    /// @return the file path of the input file.
    private static Path getInputFilePath(Puzzle puzzle)
    {
        String inputFileString = String.format(
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
    private static String retrieveSessionId()
    {
        try
        {
            return Files.readString(Path.of("session.txt"));
        }
        catch (IOException e)
        {
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
    private static String getPuzzleInputFromWebsite(Puzzle puzzle)
    {
        String inputURL = String.format(
                "https://adventofcode.com/%d/day/%d/input",
                puzzle.year(),
                puzzle.day()
        );
        
        String userAgent = "github.com/Sei-Jin/Advent-of-Code by seijin.tufts@gmail.com";
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(inputURL))
                .header("User-Agent", userAgent)
                .header("Cookie", "session=" + retrieveSessionId())
                .GET()
                .build();
        
        HttpClient httpClient = HttpClient.newBuilder()
                .build();
        
        HttpResponse<String> httpResponse;
        
        try
        {
            httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        }
        catch (IOException | InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        
        if (httpResponse.statusCode() != 200)
        {
            throw new IllegalStateException("Error: Bad Request to website.");
        }
        else
        {
            System.out.println("Successfully retrieved the puzzle input from: " + inputURL);
        }
        
        return httpResponse.body();
    }
    
    /// Writes the puzzle input to a text file.
    ///
    /// @param inputFilePath the path to the puzzle input.
    /// @param puzzleInput the puzzle input.
    private static void storePuzzleInput(Path inputFilePath, String puzzleInput)
    {
        try
        {
            Files.createFile(inputFilePath);
            System.out.println("Created file: " + inputFilePath.toAbsolutePath());
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        
        try (FileWriter fileWriter = new FileWriter(inputFilePath.toAbsolutePath().toString()))
        {
            fileWriter.write(puzzleInput);
            System.out.println("Wrote to file: " + inputFilePath.toAbsolutePath());
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    /// Retrieves the puzzle input from the puzzle input file.
    ///
    /// @param fileInputPath the path to the puzzle input.
    /// @return the puzzle input.
    private static String getPuzzleInputFromLocalStorage(Path fileInputPath)
    {
        try
        {
            return Files.readString(fileInputPath);
        }
        catch (IOException e)
        {
            throw new RuntimeException("Failed to read the file: " + e);
        }
    }
}
