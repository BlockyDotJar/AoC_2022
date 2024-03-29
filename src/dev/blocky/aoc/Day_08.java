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

public class Day_08
{
    public static void main(String[] args) throws IOException
    {
        int count;
        int endCount;

        File file = new File("src/rsc/Day_08.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), UTF_8);

        int[][] trees = new int[fileContent.size()][];

        Arrays.setAll(trees, line -> new int[fileContent.get(0).trim().length()]);

        for (int i = 0; i < fileContent.size(); i++)
        {
            String line = fileContent.get(i);

            for (int x = 0; x < line.trim().length(); x++)
            {
                trees[i][x] = Character.getNumericValue(line.charAt(x));
            }
        }

        int visible = 0;
        int ts = 1;

        for (int i = 0; i < trees.length; i++)
        {
            for (int x = 0; x < trees[i].length; x++)
            {
                for (Position pos : Position.values())
                {
                    int current = trees[i][x];

                    boolean isVisible = true;

                    for (int z = x, y = i;
                         z > 0 && z < trees[i].length - 1 && y > 0 && y < trees.length - 1;
                         z += pos.x, y += pos.y)
                    {
                        int tree = trees[y + pos.y][z + pos.x];

                        if (current <= tree)
                        {
                            isVisible = false;
                            break;
                        }
                    }

                    if (isVisible)
                    {
                        visible++;
                        break;
                    }
                }

                int score = 1;

                for (Position pos : Position.values())
                {
                    score *= score(pos, trees, x, i);
                }

                ts = Math.max(ts, score);
            }
        }

        count = visible;
        endCount = ts;

        // Part 1 of the Challenge.
        System.out.println(count);

        // Part 2 of the Challenge.
        System.out.println(endCount);
    }

    static int score(Position pos, int[][] trees, int x, int y)
    {
        int current = trees[y][x];
        int score = 0;

        for (int z = x, xy = y;
             z > 0 && z < trees[xy].length - 1 && xy > 0 && xy < trees.length - 1;
             z += pos.x, xy += pos.y)
        {
            score++;

            if (current <= trees[xy + pos.y][z + pos.x])
            {
                break;
            }
        }
        return score;
    }

    enum Position
    {
        WEST(-1, 0),
        EAST(1, 0),
        NORTH(0, -1),
        SOUTH(0, 1);

        final int x;
        final int y;

        Position(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
}
