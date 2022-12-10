package dev.blocky.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Day_10
{
    private static final List<Integer> out = new ArrayList<>();

    public static void main(String[] args) throws IOException
    {
        final File file = new File("src/rsc/Day_10.txt");
        final List<String> fileContent = Files.readAllLines(file.toPath(), UTF_8);

        // Part 1 of the Challenge.
        System.out.println(part1(fileContent));

        // Part 2 of the Challenge.
        part2();
    }

    private static int part1(List<String> lines)
    {
        final List<Integer> ss = new ArrayList<>();
        final List<Integer> mp = new ArrayList<>(Arrays.asList(20, 60, 100, 140, 180, 220));

        int cycle = 1;
        int register = 1;

        out.add(register);

        for (String line : lines)
        {
            if (line.equals("noop"))
            {
                cycle += 1;

                out.add(register);

                if (mp.contains(cycle))
                {
                    ss.add(cycle * register);
                }
            }
            else if (line.startsWith("addx"))
            {
                int value = Integer.parseInt(line.substring(5));

                cycle += 1;

                out.add(register);

                if (mp.contains(cycle))
                {
                    ss.add(cycle * register);
                }

                cycle += 1;
                register += value;

                out.add(register);

                if (mp.contains(cycle))
                {
                    ss.add(cycle * register);
                }
            }
        }
        return ss.stream().mapToInt(Integer::intValue).sum();
    }

    private static void part2()
    {
        for (int i = 0; i <= 5; i++)
        {
            for (int x = 0; x <= 39; x++)
            {
                int sprite = out.get(x + i * 40);
                if (x == sprite || x == sprite - 1 || x == sprite + 1)
                {
                    System.out.print("#");
                }
                else
                {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
