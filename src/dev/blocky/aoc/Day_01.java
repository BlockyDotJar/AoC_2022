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
import java.util.Collections;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Day_01
{
    private static final List<Integer> results = new ArrayList<>();

    public static void main(String[] args) throws IOException
    {
        File file = new File("src/rsc/Day_01.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), UTF_8);

        // Part 1 of the Challenge.
        System.out.println(part1(fileContent));

        // Part 2 of the Challenge.
        System.out.println(part2());
    }

    private static int part1(List<String> lines)
    {
        int current = 0;

        for (String line : lines)
        {
            if (line.isBlank())
            {
                results.add(current);
                current = 0;
                continue;
            }
            current += Integer.parseInt(line.strip());
        }
        Collections.sort(results);

        return Collections.max(results);
    }

    private static int part2()
    {
        List<Integer> top3 = new ArrayList<>(results.subList(results.size() - 3, results.size()));
        return top3.stream().mapToInt(Integer::intValue).sum();
    }
}
