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
