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
import java.util.Arrays;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Day_03
{
    public static void main(String[] args) throws IOException
    {
        int count = 0;
        int endCount = 0;

        File file = new File("src/rsc/Day_03.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), UTF_8);

        for (String line : fileContent)
        {
            char[] part1 = line.substring(0, line.length() / 2).toCharArray();
            char[] part2 = line.substring(line.length() / 2).toCharArray();

            Arrays.sort(part1);
            Arrays.sort(part2);

            char ch = 0;

            for (Character chr : part1)
            {
                for (char cha : part2)
                {
                    if (chr.equals(cha))
                    {
                        ch = cha;
                    }
                }
            }

            if (Character.isUpperCase(ch))
            {
                count += (int) ch - 38;
            }
            else if (Character.isLowerCase(ch))
            {
                count += (int) ch - 96;
            }
        }

        for (int i = 0; i < fileContent.size(); i += 3)
        {
            List<String> l = fileContent.subList(i, i + 3);

            char[] part1 = l.get(0).toCharArray();
            char[] part2 = l.get(1).toCharArray();
            char[] part3 = l.get(2).toCharArray();

            Arrays.sort(part1);
            Arrays.sort(part2);
            Arrays.sort(part3);

            char ch = 0;

            for (Character chr : part1)
            {
                for (char cha : part2)
                {
                    for (char c : part3)
                    {
                        if (chr.equals(cha) && chr.equals(c))
                        {
                            ch = c;
                        }
                    }
                }
            }

            if (Character.isUpperCase(ch))
            {
                endCount += (int) ch - 38;
            }
            else if (Character.isLowerCase(ch))
            {
                endCount += (int) ch - 96;
            }
        }

        // Part 1 of the Challenge.
        System.out.println(count);

        // Part 2 of the Challenge.
        System.out.println(endCount);
    }
}
