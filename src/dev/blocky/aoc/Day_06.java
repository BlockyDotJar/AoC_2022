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
