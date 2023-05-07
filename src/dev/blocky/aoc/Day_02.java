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
