package dev.blocky.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * I tried  it...
 */
public class Day_5
{

    public static void main(String[] args) throws IOException
    {
        final File file = new File("src/rsc/Day_5.txt");
        final List<String> fileContent = Files.readAllLines(file.toPath(), UTF_8);

        List<String> l1 = new ArrayList<>();
        List<String> l2 = new ArrayList<>();
        List<String> l3 = new ArrayList<>();
        List<String> l4 = new ArrayList<>();
        List<String> l5 = new ArrayList<>();
        List<String> l6 = new ArrayList<>();
        List<String> l7 = new ArrayList<>();
        List<String> l8 = new ArrayList<>();
        List<String> l9 = new ArrayList<>();

        for (String line : fileContent)
        {
            if (!line.startsWith("move") && !line.startsWith(" ") && !line.isBlank())
            {
                final String ls1 = line.substring(0, 3);

                if (!ls1.contains("   "))
                {
                    l1.add(ls1);
                }

                final String ls2 = line.substring(4, 7);

                if (!ls2.contains("   "))
                {
                    l2.add(ls2);
                }

                final String ls3 = line.substring(8, 11);

                if (!ls3.contains("   "))
                {
                    l3.add(ls3);
                }

                final String ls4 = line.substring(12, 15);

                if (!ls4.contains("   "))
                {
                    l4.add(ls4);
                }

                final String ls5 = line.substring(16, 19);

                if (!ls5.contains("   "))
                {
                    l5.add(ls5);
                }

                final String ls6 = line.substring(20, 23);

                if (!ls6.contains("   "))
                {
                    l6.add(ls6);
                }

                final String ls7 = line.substring(24, 27);

                if (!ls7.contains("   "))
                {
                    l7.add(ls7);
                }

                final String ls8 = line.substring(28, 31);

                if (!ls8.contains("   "))
                {
                    l8.add(ls8);
                }

                final String ls9 = line.substring(32, 35);

                if (!ls9.contains("   "))
                {
                    l9.add(ls9);
                }
            }

            if (line.startsWith("move"))
            {
                final String es = line.replace("move ", "")
                        .replace(" from ", "-")
                        .replace(" to ", "-");

                final String[] ints = es.split("-");

                final int n1 = Integer.parseInt(ints[0]);
                final int n2 = Integer.parseInt(ints[1]);
                final int n3 = Integer.parseInt(ints[2]);

                List<String> from = new ArrayList<>();
                List<String> to = new ArrayList<>();

                switch (n2)
                {
                case 1 -> from = l1;
                case 2 -> from = l2;
                case 3 -> from = l3;
                case 4 -> from = l4;
                case 5 -> from = l5;
                case 6 -> from = l6;
                case 7 -> from = l7;
                case 8 -> from = l8;
                case 9 -> from = l9;
                }

                switch (n3)
                {
                case 1 -> to = l1;
                case 2 -> to = l2;
                case 3 -> to = l3;
                case 4 -> to = l4;
                case 5 -> to = l5;
                case 6 -> to = l6;
                case 7 -> to = l7;
                case 8 -> to = l8;
                case 9 -> to = l9;
                }

                for (int i = 0; i < n1; i++)
                {
                    to.add(from.get(i));

                    // We must remove the items from the list without throwing an 'IndexOutOfBoundsException'
                    if (i == 0)
                    {
                        from.remove(i);
                    }
                }
            }
        }

        Collections.reverse(l1);
        Collections.reverse(l2);
        Collections.reverse(l3);
        Collections.reverse(l4);
        Collections.reverse(l5);
        Collections.reverse(l6);
        Collections.reverse(l7);
        Collections.reverse(l8);
        Collections.reverse(l9);

        final String e1 = l1.get(0).replace("[", "").replace("]", "");
        final String e2 = l2.get(0).replace("[", "").replace("]", "");
        final String e3 = l3.get(0).replace("[", "").replace("]", "");
        final String e4 = l4.get(0).replace("[", "").replace("]", "");
        final String e5 = l5.get(0).replace("[", "").replace("]", "");
        final String e6 = l6.get(0).replace("[", "").replace("]", "");
        final String e7 = l7.get(0).replace("[", "").replace("]", "");
        final String e8 = l8.get(0).replace("[", "").replace("]", "");
        final String e9 = l9.get(0).replace("[", "").replace("]", "");

        System.out.println(e1 + e2 + e3 + e4 + e5 + e6 + e7 + e8 + e9);
    }
}
