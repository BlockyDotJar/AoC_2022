package dev.blocky.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Day_2
{

    public static void main(String[] args) throws IOException
    {
        int points = 0;

        final File file = new File("src/rsc/Day_2.txt");
        final List<String> fileContent = Files.readAllLines(file.toPath(), UTF_8);

        for (String line : fileContent)
        {
            switch (line)
            {
            case "A X" -> points += 4;
            case "A Y" -> points += 8;
            case "A Z" -> points += 3;
            case "B X" -> points += 1;
            case "B Y" -> points += 5;
            case "B Z" -> points += 9;
            case "C X" -> points += 7;
            case "C Y" -> points += 2;
            case "C Z" -> points += 6;
            }
        }

        // Part 1 of the Challenge.
        System.out.println(points);

        int endPoints = 0;

        for (String line : fileContent)
        {
            switch (line)
            {
            case "A X" -> endPoints += 3;
            case "A Y" -> endPoints += 4;
            case "A Z" -> endPoints += 8;
            case "B X" -> endPoints += 1;
            case "B Y" -> endPoints += 5;
            case "B Z" -> endPoints += 9;
            case "C X" -> endPoints += 2;
            case "C Y" -> endPoints += 6;
            case "C Z" -> endPoints += 7;
            }
        }

        // Part 2 of the Challenge.
        System.out.println(endPoints);
    }
}
