package dev.blocky.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Day_1 {

    public static void main(String[] args) throws IOException {
        List<Integer> results = new ArrayList<>();
        int current = 0;

        final File file = new File("src/rsc/Day_1.txt");
        final List<String> fileContent = Files.readAllLines(file.toPath(), UTF_8);

        for (String line : fileContent) {
            if (line.isBlank()) {
                results.add(current);
                current = 0;
                continue;
            }
            current += Integer.parseInt(line.strip());
        }

        Collections.sort(results);

        // Part 1 of the Challenge.
        System.out.println(Collections.max(results));

        // Part 2 of the Challenge.
        final List<Integer> top3 = new ArrayList<>(results.subList(results.size() - 3, results.size()));

        System.out.println(top3.stream().mapToInt(Integer::intValue).sum());
    }
}
