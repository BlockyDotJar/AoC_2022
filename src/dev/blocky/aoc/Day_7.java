package dev.blocky.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Day_7
{
    private static final List<Dir> dirs = new ArrayList<>();
    private static final Dir root = new Dir("/");
    private static Dir currentDir;

    public static void main(String[] args) throws IOException
    {
        final File file = new File("src/rsc/Day_7.txt");
        final String fileContent = Files.readString(file.toPath(), UTF_8);

        currentDir = root;
        dirs.add(root);

        final String[] cmds = fileContent.split("\r\n");

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
                final String pos = cmd.split(" ")[2];
                final Optional<Dir> subDir = currentDir.subDir.stream().filter(n -> n.name.equals(pos)).findFirst();

                subDir.ifPresent(Dir -> currentDir = Dir);
            }
            else if (cmd.startsWith("dir"))
            {
                final Dir Dir = new Dir(cmd.split(" ")[1]);

                Dir.parent = currentDir;

                currentDir.subDir.add(Dir);
                dirs.add(Dir);
            }
            else if (Character.isDigit(cmd.charAt(0)))
            {
                final String[] data = cmd.split(" ");
                final Dir n = new Dir(data[1]);

                n.parent = currentDir;

                n.size = Integer.parseInt(data[0]);

                currentDir.subDir.add(n);
            }
        }

        final int dirSizes = dirs.stream()
                .mapToInt(Dir::getRecursiveSize)
                .filter(i -> i < 100_000)
                .sum();

        // Part 1 of the Challenge.
        System.out.println(dirSizes);

        final int totalSize = 70_000_000;
        final int requiredSize = 30_000_000;
        final int maxSize = totalSize - requiredSize;
        final int totalRootSize = root.getRecursiveSize();
        final int neededSize = totalRootSize - maxSize;

        final Optional<Dir> lNRFD = dirs.stream()
                .filter(d -> d.getRecursiveSize() > neededSize)
                .min(Comparator
                        .comparingInt(Dir::getRecursiveSize));

        if (lNRFD.isPresent())
        {
            final Dir del = lNRFD.get();

            final int delSize = del.getRecursiveSize();

            // Part 2 of the Challenge.
            System.out.println(delSize);
        }
    }

    static class Dir
    {
        Dir parent;
        String name;
        int size;
        List<Dir> subDir = new ArrayList<>();

        public Dir(String name)
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
