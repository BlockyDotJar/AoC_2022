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
import java.util.Collections;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Day_11
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("src/rsc/Day_11.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), UTF_8);

        // Part 1 of the Challenge.
        System.out.println(part1(fileContent));

        // Part 2 of the Challenge.
        System.out.println(part2(fileContent));
    }


    private static long part1(List<String> lines)
    {
        List<Monkey> monkeyL = new ArrayList<>();
        List<Long> itemL = new ArrayList<>();

        int[] i = new int[5];

        int op = 1;

        for (String line : lines)
        {
            if (line.isBlank() || line.startsWith("Monkey"))
            {
                continue;
            }

            String sub = line.substring(0, 4);
            String ifB = line.substring(4, 8);

            String[] split = line.split(": ");

            String it = split[1];

            String[] items = it.split(", ");
            String[] items2 = it.split(" ");

            switch (sub)
            {
                case "  St" ->
                {
                    for (String item : items)
                    {
                        long l = Long.parseLong(item);

                        itemL.add(l);
                    }
                }
                case "  Op" ->
                {
                    i[0] = items2[4].equals("old") ? 2 : items2[3].equals("+") ? 0 : 1;
                    i[1] = Integer.parseInt(items2[4].equals("old") ? "0" : items2[4]);
                }
                case "  Te" -> i[2] = Integer.parseInt(items2[2]);
                case "    " ->
                {
                    switch (ifB)
                    {
                        case "If t" -> i[3] = Integer.parseInt(items2[3]);
                        case "If f" ->
                        {
                            i[4] = Integer.parseInt(items2[3]);

                            Monkey monkey = new Monkey(itemL, i);
                            monkeyL.add(monkey);

                            op *= i[2];

                            itemL.clear();

                            i = new int[5];
                        }
                    }
                }
            }
        }

        for (int x = 0; x < 20; x++)
        {
            for (Monkey monkey : monkeyL)
            {
                monkey.turn(monkeyL, true, op);
            }
        }

        List<Long> countL = new ArrayList<>();

        for (Monkey monkey : monkeyL)
        {
            countL.add(monkey.count);
        }

        Collections.sort(countL);

        long eC1 = countL.get(countL.size() - 2);
        long eC2 = countL.get(countL.size() - 1);

        return eC1 * eC2;
    }

    private static long part2(List<String> lines)
    {
        List<Monkey> monkeyL = new ArrayList<>();
        List<Long> itemL = new ArrayList<>();

        int[] i = new int[5];

        int op = 1;

        for (String line : lines)
        {
            if (line.isBlank() || line.startsWith("Monkey"))
            {
                continue;
            }

            String sub = line.substring(0, 4);
            String ifB = line.substring(4, 8);

            String[] split = line.split(": ");

            String it = split[1];

            String[] items = it.split(", ");
            String[] items2 = it.split(" ");

            switch (sub)
            {
                case "  St" ->
                {
                    for (String item : items)
                    {
                        long l = Long.parseLong(item);

                        itemL.add(l);
                    }
                }
                case "  Op" ->
                {
                    i[0] = items2[4].equals("old") ? 2 : items2[3].equals("+") ? 0 : 1;
                    i[1] = Integer.parseInt(items2[4].equals("old") ? "0" : items2[4]);
                }
                case "  Te" -> i[2] = Integer.parseInt(items2[2]);
                case "    " ->
                {
                    switch (ifB)
                    {
                        case "If t" -> i[3] = Integer.parseInt(items2[3]);
                        case "If f" ->
                        {
                            i[4] = Integer.parseInt(items2[3]);

                            Monkey monkey = new Monkey(itemL, i);
                            monkeyL.add(monkey);

                            op *= i[2];

                            itemL.clear();

                            i = new int[5];
                        }
                    }
                }
            }
        }

        for (int x = 0; x < 10000; x++)
        {
            for (Monkey monkey : monkeyL)
            {
                monkey.turn(monkeyL, false, op);
            }
        }

        List<Long> countL = new ArrayList<>();

        for (Monkey monkey : monkeyL)
        {
            countL.add(monkey.count);
        }

        Collections.sort(countL);

        long eC1 = countL.get(countL.size() - 2);
        long eC2 = countL.get(countL.size() - 1);

        return eC1 * eC2;
    }

    static class Monkey
    {
        List<Long> itemL;
        int[] i;

        long count;

        Monkey(List<Long> itemL, int[] i)
        {
            this.itemL = new ArrayList<>();
            this.itemL.addAll(itemL);
            this.i = i;

            count = 0;
        }

        void turn(List<Monkey> monkeyL, boolean divide, int op)
        {
            for (Long item : itemL)
            {
                switch (i[0])
                {
                    case 0 -> item += i[1];
                    case 1 -> item *= i[1];
                    case 2 -> item *= item;
                }

                if (divide)
                {
                    item /= 3;
                }

                item %= op;

                monkeyL.get((item % i[2] == 0) ? i[3] : i[4]).itemL.add(item);

                count++;
            }
            itemL.clear();
        }
    }
}
