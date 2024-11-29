package advent_of_code;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PuzzleInput
{
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException
    {
        Puzzle puzzle = new Puzzle(2015, 7);
        String puzzleInput = getPuzzleInput(puzzle);
        storePuzzleInput(puzzle, puzzleInput);
    }
    
    /// This method retrieves the session cookie from a file. The session cookie must be manually stored in the
    /// file by pasting the "session" cookie value from the browser into the text file.
    ///
    /// @return the session value.
    static String getSessionID() throws IOException
    {
        return Files.readString(Path.of("session.txt"));
    }
    
    /// This method retrieves the puzzle input for a given puzzle from the Advent of Code website by making
    /// a GET request to the puzzle input URL.
    ///
    /// @param puzzle the puzzle.
    /// @return the puzzle input.
    static String getPuzzleInput(Puzzle puzzle) throws URISyntaxException, IOException, InterruptedException
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
    
    /// This method writes the puzzle input to a text file.
    ///
    /// @param puzzle the puzzle.
    /// @param puzzleInput the puzzle input.
    static void storePuzzleInput(Puzzle puzzle, String puzzleInput) throws IOException
    {
        String inputFileRelativePath = "input/" +
                "year_" +
                puzzle.getYear() +
                "/day" +
                puzzle.getDay() +
                ".txt";
        
        Path inputFilePath = Paths.get(inputFileRelativePath);
        Files.createFile(inputFilePath);
        
        FileWriter fileWriter = new FileWriter(inputFilePath.toAbsolutePath().toString());
        fileWriter.write(puzzleInput);
        fileWriter.close();
    }
}
