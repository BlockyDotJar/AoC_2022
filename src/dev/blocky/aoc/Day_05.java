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
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Day_05
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("src/rsc/Day_05.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), UTF_8);

        // Part 1 of the Challenge.
        System.out.println(part1(fileContent));

        // Part 2 of the Challenge.
        System.out.println(part2(fileContent));
    }

    private static String part1(List<String> lines)
    {
        List<List<String>> col = new ArrayList<>();

        boolean rcs = true;

        for (String line : lines)
        {
            if (rcs)
            {
                int rows = 9;

                if (line.isEmpty())
                {
                    rows = 0;
                }

                int pos = 0;

                List<String> crRo = new ArrayList<>();

                for (int i = 0; i < rows; i++)
                {
                    String sub = line.substring(pos);

                    if (i != (rows - 1))
                    {
                        sub = sub.substring(0, 3);
                        pos += 4;
                    }

                    if (!Character.isDigit(sub.charAt(1)))
                    {
                        crRo.add(sub);
                    }
                }

                AtomicInteger counter = new AtomicInteger();

                crRo.forEach(row ->
                {
                    if (col.isEmpty())
                    {
                        for (int i = 0; i < 9; i++)
                        {
                            col.add(i, new ArrayList<>());
                        }
                    }

                    if (!row.equals("   "))
                    {
                        List<String> rl = col.get(counter.get());
                        rl.add(row);
                    }
                    counter.getAndIncrement();
                });

                if (line.isEmpty() || line.isBlank())
                {
                    rcs = false;
                }
            }
            else
            {
                String move = line.substring(0, line.indexOf("from") - 1)
                        .replace("move ", "");

                String from = line.substring(line.indexOf("from"), line.indexOf("to") - 1)
                        .replace("from ", "");

                String to = line.substring(line.indexOf("to"))
                        .replace("to ", "");

                int moveI = Integer.parseInt(move);
                int fromI = Integer.parseInt(from);
                int toI = Integer.parseInt(to);

                for (int i = 0; i < moveI; i++)
                {
                    List<String> lFrom = col.get(fromI - 1);
                    String moveStr = lFrom.get(0);

                    List<String> lTo = col.get(toI - 1);
                    lTo.add(0, moveStr);
                    lFrom.remove(moveStr);
                }
            }
        }
        StringBuilder strBuilder = new StringBuilder();

        col.forEach(crate -> strBuilder.append(crate.get(0).replace("[", "").replace("]", "")));

        return strBuilder.toString();
    }

    private static String part2(List<String> lines)
    {
        List<List<String>> col = new ArrayList<>();

        boolean rcs = true;

        for (String line : lines)
        {
            if (rcs)
            {
                int rows = 9;

                if (line.isEmpty())
                {
                    rows = 0;
                }

                int pos = 0;

                List<String> crRo = new ArrayList<>();

                for (int i = 0; i < rows; i++)
                {
                    String sub = line.substring(pos);

                    if (i != (rows - 1))
                    {
                        sub = sub.substring(0, 3);
                        pos += 4;
                    }

                    if (!Character.isDigit(sub.charAt(1)))
                    {
                        crRo.add(sub);
                    }
                }

                AtomicInteger counter = new AtomicInteger();

                crRo.forEach(row ->
                {
                    if (col.isEmpty())
                    {
                        for (int i = 0; i < 9; i++)
                        {
                            col.add(i, new ArrayList<>());
                        }
                    }

                    if (!row.equals("   "))
                    {
                        List<String> rl = col.get(counter.get());
                        rl.add(row);
                    }
                    counter.getAndIncrement();
                });

                if (line.isEmpty())
                {
                    rcs = false;
                }
            }
            else
            {
                String move = line.substring(0, line.indexOf("from") - 1)
                        .replace("move ", "");

                String from = line.substring(line.indexOf("from"), line.indexOf("to") - 1)
                        .replace("from ", "");

                String to = line.substring(line.indexOf("to"))
                        .replace("to ", "");

                int moveI = Integer.parseInt(move);
                int fromI = Integer.parseInt(from);
                int toI = Integer.parseInt(to);

                List<String> lFrom = col.get(fromI - 1);
                List<String> lMove = new ArrayList<>(lFrom.subList(0, moveI));

                List<String> lTo = col.get(toI - 1);
                lTo.addAll(0, new ArrayList<>(lMove));
                lMove.forEach(lFrom::remove);
            }
        }
        StringBuilder strBuilder = new StringBuilder();

        col.forEach(crate -> strBuilder.append(crate.get(0).replace("[", "").replace("]", "")));

        return strBuilder.toString();
    }
}
