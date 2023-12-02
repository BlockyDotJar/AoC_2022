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

public class Day_02
{
    public static void main(String[] args) throws IOException
    {
        int points = 0;
        int endPoints = 0;

        File file = new File("src/rsc/Day_02.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), UTF_8);

        for (String line : fileContent)
        {
            switch (line)
            {
                case "A X" ->
                {
                    points += 4;
                    endPoints += 3;
                }
                case "A Y" ->
                {
                    points += 8;
                    endPoints += 4;
                }
                case "A Z" ->
                {
                    points += 3;
                    endPoints += 8;
                }
                case "B X" ->
                {
                    points += 1;
                    endPoints += 1;
                }
                case "B Y" ->
                {
                    points += 5;
                    endPoints += 5;
                }
                case "B Z" ->
                {
                    points += 9;
                    endPoints += 9;
                }
                case "C X" ->
                {
                    points += 7;
                    endPoints += 2;
                }
                case "C Y" ->
                {
                    points += 2;
                    endPoints += 6;
                }
                case "C Z" ->
                {
                    points += 6;
                    endPoints += 7;
                }
            }
        }

        // Part 1 of the Challenge.
        System.out.println(points);

        // Part 2 of the Challenge.
        System.out.println(endPoints);
    }
}
