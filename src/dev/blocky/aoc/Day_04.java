package dev.blocky.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Day_04
{

    public static void main(String[] args) throws IOException
    {
        int count = 0;
        int endCount = 0;

        final File file = new File("src/rsc/Day_04.txt");
        final List<String> fileContent = Files.readAllLines(file.toPath(), UTF_8);

        for (String line : fileContent)
        {
            final String[] parts = line.split(",");

            final String[] part1 = parts[0].split("-");
            final String[] part2 = parts[1].split("-");

            final int p1n1 = Integer.parseInt(part1[0]);
            final int p1n2 = Integer.parseInt(part1[1]);

            final int p2n1 = Integer.parseInt(part2[0]);
            final int p2n2 = Integer.parseInt(part2[1]);


            if (p1n1 <= p2n1 && p1n2 >= p2n2)
            {
                count += 1;
            }
            else if (p1n1 >= p2n1 && p1n2 <= p2n2)
            {
                count += 1;
            }

            if (Math.max(p1n1, p2n1) <= Math.min(p1n2, p2n2))
            {
                endCount += 1;
            }
        }

        // Part 1 of the Challenge.
        System.out.println(count);

        // Part 2 of the Challenge.
        System.out.println(endCount);
    }
}
