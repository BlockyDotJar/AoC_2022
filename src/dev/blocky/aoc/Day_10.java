/**
 * Copyright 2022-2023 Dominic R. (aka. BlockyDotJar)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
