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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Day_10
{
    private static final List<Integer> out = new ArrayList<>();

    public static void main(String[] args) throws IOException
    {
        File file = new File("src/rsc/Day_10.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), UTF_8);

        // Part 1 of the Challenge.
        System.out.println(part1(fileContent));

        // Part 2 of the Challenge.
        part2();
    }

    private static int part1(List<String> lines)
    {
        List<Integer> ss = new ArrayList<>();
        List<Integer> mp = new ArrayList<>(Arrays.asList(20, 60, 100, 140, 180, 220));

        int c = 1;
        int r = 1;

        out.add(r);

        for (String line : lines)
        {
            if (line.equals("noop"))
            {
                c += 1;

                out.add(r);

                if (mp.contains(c))
                {
                    ss.add(c * r);
                }
            }
            else if (line.startsWith("addx"))
            {
                int val = Integer.parseInt(line.substring(5));

                c += 1;

                out.add(r);

                if (mp.contains(c))
                {
                    ss.add(c * r);
                }

                c += 1;
                r += val;

                out.add(r);

                if (mp.contains(c))
                {
                    ss.add(c * r);
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
                int s = out.get(x + i * 40);

                if (x == s || x == s - 1 || x == s + 1)
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
