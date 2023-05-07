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
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Day_07
{
    private static final List<Dir> dirs = new ArrayList<>();
    private static final Dir root = new Dir("/");
    private static Dir currentDir;

    public static void main(String[] args) throws IOException
    {
        File file = new File("src/rsc/Day_07.txt");
        String fileContent = Files.readString(file.toPath(), UTF_8);

        currentDir = root;
        dirs.add(root);

        String[] cmds = fileContent.split("\r\n");

        for (String cmd : cmds)
        {
            if (cmd.contains("cd /"))
            {
                currentDir = root;
            }
            else if (cmd.startsWith("$ cd .."))
            {
                currentDir = currentDir.parent;
            }
            else if (cmd.startsWith("$ cd"))
            {
                String pos = cmd.split(" ")[2];
                Optional<Dir> subDir = currentDir.subDir.stream().filter(n -> n.name.equals(pos)).findFirst();
                subDir.ifPresent(Dir -> currentDir = Dir);
            }
            else if (cmd.startsWith("dir"))
            {
                Dir dir = new Dir(cmd.split(" ")[1]);
                dir.parent = currentDir;

                currentDir.subDir.add(dir);

                dirs.add(dir);
            }
            else if (Character.isDigit(cmd.charAt(0)))
            {
                String[] data = cmd.split(" ");

                Dir n = new Dir(data[1]);
                n.parent = currentDir;
                n.size = Integer.parseInt(data[0]);

                currentDir.subDir.add(n);
            }
        }

        int dirSizes = dirs.stream()
                .mapToInt(Dir::getRecursiveSize)
                .filter(i -> i < 100_000)
                .sum();

        // Part 1 of the Challenge.
        System.out.println(dirSizes);

        int totalSize = 70_000_000;
        int requiredSize = 30_000_000;
        int maxSize = totalSize - requiredSize;
        int totalRootSize = root.getRecursiveSize();
        int neededSize = totalRootSize - maxSize;

        Optional<Dir> lNRFD = dirs.stream()
                .filter(d -> d.getRecursiveSize() > neededSize)
                .min(Comparator
                        .comparingInt(Dir::getRecursiveSize));

        if (lNRFD.isPresent())
        {
            Dir del = lNRFD.get();

            int delSize = del.getRecursiveSize();

            // Part 2 of the Challenge.
            System.out.println(delSize);
        }
    }

    static class Dir
    {
        List<Dir> subDir = new ArrayList<>();
        String name;
        Dir parent;
        int size;

        Dir(String name)
        {
            this.name = name;
        }

        int getRecursiveSize()
        {
            return subDir.stream().mapToInt(n ->
            {
                if (n.subDir.isEmpty())
                {
                    return n.size;
                }
                else
                {
                    return n.getRecursiveSize();
                }
            }).sum();
        }
    }
}
