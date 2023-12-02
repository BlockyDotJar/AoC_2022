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
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Day_04
{
    public static void main(String[] args) throws IOException
    {
        int count = 0;
        int endCount = 0;

        File file = new File("src/rsc/Day_04.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), UTF_8);

        for (String line : fileContent)
        {
            String[] parts = line.split(",");

            String[] part1 = parts[0].split("-");
            String[] part2 = parts[1].split("-");

            int p1n1 = Integer.parseInt(part1[0]);
            int p1n2 = Integer.parseInt(part1[1]);

            int p2n1 = Integer.parseInt(part2[0]);
            int p2n2 = Integer.parseInt(part2[1]);

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
