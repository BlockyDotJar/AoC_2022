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
