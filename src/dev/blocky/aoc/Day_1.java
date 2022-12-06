package dev.blocky.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Day_1
{
    private static final List<Integer> results = new ArrayList<>();

    public static void main(String[] args) throws IOException
    {
        final File file = new File("src/rsc/Day_1.txt");
        final List<String> fileContent = Files.readAllLines(file.toPath(), UTF_8);

        // Part 1 of the Challenge.
        System.out.println(part1(fileContent));

        // Part 2 of the Challenge.
        System.out.println(part2());
    }

    private static int part1(List<String> lines)
    {
        int current = 0;

        for (String line : lines)
        {
            if (line.isBlank())
            {
                results.add(current);
                current = 0;
                continue;
            }
            current += Integer.parseInt(line.strip());
        }
        Collections.sort(results);

        return Collections.max(results);
    }

    private static int part2()
    {
        final List<Integer> top3 = new ArrayList<>(results.subList(results.size() - 3, results.size()));
        return top3.stream().mapToInt(Integer::intValue).sum();
    }
}
