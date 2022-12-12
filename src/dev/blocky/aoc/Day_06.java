package dev.blocky.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Day_06
{

    public static void main(String[] args) throws IOException
    {
        final File file = new File("src/rsc/Day_06.txt");
        final String fileContent = Files.readString(file.toPath(), UTF_8);

        final char[] chars = fileContent.toCharArray();

        // Part 1 of the Challenge.
        System.out.println(part1(chars));

        // Part 2 of the Challenge.
        System.out.println(part2(chars));
    }

    private static int part1(char[] chars)
    {
        loop:
        for (int i = 4 - 1; i < chars.length; i++)
        {
            int count = 0;
            for (int x = i; x > i - 4; x--)
            {
                final int ch = chars[x] - 'a';
                final int count2 = 1 << ch;
                if (count == (count = count | count2))
                {
                    continue loop;
                }
            }
            return i + 1;
        }
        return -1;
    }

    private static int part2(char[] chars)
    {
        loop:
        for (int i = 14 - 1; i < chars.length; i++)
        {
            int count = 0;
            for (int x = i; x > i - 14; x--)
            {
                final int ch = chars[x] - 'a';
                final int count2 = 1 << ch;
                if (count == (count = count | count2))
                {
                    continue loop;
                }
            }
            return i + 1;
        }
        return -1;
    }
}
