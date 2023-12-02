/**
 * VorteX - General utility program written in Java.
 * Copyright (C) 2022 BlockyDotJar (aka. Dominic R.)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package dev.blocky.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Day_06
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("src/rsc/Day_06.txt");
        String fileContent = Files.readString(file.toPath(), UTF_8);

        char[] chars = fileContent.toCharArray();

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
                int ch = chars[x] - 'a';
                int count2 = 1 << ch;
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
                int ch = chars[x] - 'a';
                int count2 = 1 << ch;
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
