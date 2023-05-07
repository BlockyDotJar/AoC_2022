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
import java.util.*;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Day_09
{
    public static void main(String[] args) throws IOException
    {
        int count;
        int endCount;

        File file = new File("src/rsc/Day_09.txt");

        try (Stream<String> fileContent = Files.lines(file.toPath(), UTF_8))
        {
            HashSet<Position> ch1 = new HashSet<>();
            Position po1 = new Position(0, 0), tail = new Position(0, 0);
            ch1.add(new Position(0, 0));

            HashSet<Position> ch2 = new HashSet<>();
            Position po2 = new Position(0, 0);
            List<Position> positions = new ArrayList<>
                    (
                            Arrays.asList
                                    (
                                            new Position(0, 0), new Position(0, 0), new Position(0, 0), new Position(0, 0),
                                            new Position(0, 0), new Position(0, 0), new Position(0, 0), new Position(0, 0),
                                            new Position(0, 0)
                                    )
                    );

            fileContent.map(l -> Arrays.stream(l.split(" ")).toList())
                    .forEachOrdered(l ->
                    {
                        int loop = Integer.parseInt(l.get(1));

                        for (int i = 0; i < loop; i++)
                        {
                            switch (l.get(0))
                            {
                            case "L" -> po1.x = po1.x - 1;
                            case "R" -> po1.x = po1.x + 1;
                            case "D" -> po1.y = po1.y - 1;
                            case "U" -> po1.y = po1.y + 1;
                            }

                            move(po1, tail);

                            ch1.add(new Position(tail.x, tail.y));

                            switch (l.get(0))
                            {
                            case "L" -> po2.x = po2.x - 1;
                            case "R" -> po2.x = po2.x + 1;
                            case "D" -> po2.y = po2.y - 1;
                            case "U" -> po2.y = po2.y + 1;
                            }

                            move(po2, positions.get(0));

                            for (int x = 1; x < positions.size(); x++)
                            {
                                move(positions.get(x - 1), positions.get(x));
                            }

                            ch2.add(new Position(positions.get(positions.size() - 1).x, positions.get(positions.size() - 1).y));
                        }
                    });

            count = ch1.size();
            endCount = ch2.size();

            // Part 1 of the Challenge.
            System.out.println(count);

            // Part 1 of the Challenge.
            System.out.println(endCount);
        }
    }

    static void move(Position h, Position t)
    {
        if (Math.abs(h.x - t.x) == 2 && Math.abs(h.y - t.y) == 1)
        {
            t.x = t.x + (t.x - h.x) / -2;
            t.y = t.y + (t.y - h.y) * -1;
        }
        else if (Math.abs(h.y - t.y) == 2 && Math.abs(h.x - t.x) == 1)
        {
            t.x = t.x + (t.x - h.x) * -1;
            t.y = t.y + (t.y - h.y) / -2;
        }
        else if (Math.abs(h.x - t.x) == 2 || Math.abs(h.y - t.y) == 2)
        {
            t.x = t.x + (t.x - h.x) / -2;
            t.y = t.y + (t.y - h.y) / -2;
        }
    }

    static class Position
    {
        int x;
        int y;

        Position(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object obj)
        {
            if (obj instanceof Position)
            {
                return x == ((Position) obj).x && y == ((Position) obj).y;
            }
            return false;
        }
    }
}
