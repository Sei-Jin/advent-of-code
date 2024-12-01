package aoc;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PuzzleInputRetriever
{
    /// Retrieves the puzzle input.
    ///
    /// The puzzle input is retrieved from local storage if present. If the puzzle is not present, the puzzle input is
    /// retrieved from the website, stored in local storage, and then retrieved from local storage.
    ///
    /// @param puzzle the puzzle.
    /// @return the lines of input from the puzzle.
    public static List<String> retrievePuzzleInput(Puzzle puzzle) throws URISyntaxException, IOException, InterruptedException
    {
        Path getInputFilePath = getInputFilePath(puzzle);
        
        if (!Files.exists(getInputFilePath))
        {
            String puzzleInput = getPuzzleInputFromWebsite(puzzle);
            storePuzzleInput(puzzle, puzzleInput);
        }
        
        return getPuzzleInputFromLocalStorage(puzzle);
    }
    
    /// Returns the file path of the input file.
    ///
    /// @param puzzle the puzzle.
    /// @return the file path of the input file.
    private static Path getInputFilePath(Puzzle puzzle)
    {
        String inputFileString = "input/year" +
                puzzle.getYear() +
                "/day" +
                puzzle.getDayWithPadding() +
                ".txt";
        
        return Path.of(inputFileString);
    }
    
    /// This method retrieves the session cookie from a file.
    ///
    /// The session cookie value must be retrieved from the browser and manually stored in the text file.
    ///
    /// @return the session value.
    static String getSessionID() throws IOException
    {
        return Files.readString(Path.of("session.txt"));
    }
    
    /// Retrieves the puzzle input for a given puzzle from the Advent of Code website.
    ///
    /// The input is retrieved from the website by making a GET request at the puzzle input URL. Puzzle inputs are
    /// user-specific, so a valid session cookie for a user must be provided.
    ///
    /// @param puzzle the puzzle.
    /// @return the puzzle input.
    static String getPuzzleInputFromWebsite(Puzzle puzzle) throws URISyntaxException, IOException, InterruptedException
    {
        String inputURL = "https://adventofcode.com/" +
                puzzle.getYear() +
                "/day/" +
                puzzle.getDay() +
                "/input";
        
        HttpRequest request =  HttpRequest.newBuilder()
                .uri(new URI(inputURL))
                .header("User-Agent", "github.com/Sei-Jin/Advent-of-Code by seijin.tufts@gmail.com")
                .header("Cookie", "session=" + getSessionID())
                .GET()
                .build();
        
        HttpClient httpClient = HttpClient.newBuilder()
                .build();
        
        HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        return httpResponse.body();
    }
    
    /// Writes the puzzle input to a text file.
    ///
    /// @param puzzle the puzzle.
    /// @param puzzleInput the puzzle input.
    static void storePuzzleInput(Puzzle puzzle, String puzzleInput) throws IOException
    {
        Path inputFilePath = getInputFilePath(puzzle);
        Files.createFile(inputFilePath);
        
        FileWriter fileWriter = new FileWriter(inputFilePath.toAbsolutePath().toString());
        fileWriter.write(puzzleInput);
        fileWriter.close();
    }
    
    /// Retrieves the puzzle input from the puzzle input file.
    ///
    /// @param puzzle the puzzle.
    /// @return the puzzle input.
    private static List<String> getPuzzleInputFromLocalStorage(Puzzle puzzle) throws IOException
    {
        Path fileInputPath = getInputFilePath(puzzle);
        
        return Files.readAllLines(fileInputPath);
    }
}
