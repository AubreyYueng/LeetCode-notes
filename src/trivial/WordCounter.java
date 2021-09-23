package trivial;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByKey;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

/**
 * Concurrent-read exclusive-write
 */
public class WordCounter {

    // The following are the ONLY variables we will modify for grading.
    // The rest of your code must run with no changes.
    public static final Path FOLDER_OF_TEXT_FILES  = Paths.get("doc"); // path to the folder where input text files are located
    public static final Path WORD_COUNT_TABLE_FILE = Paths.get("result.txt"); // path to the output plain-text (.txt) file
    public static final int  NUMBER_OF_THREADS     = 3;                // max. number of threads to spawn

    public static void main(String... args) throws IOException, ExecutionException, InterruptedException {
        // your implementation of how to run the WordCounter as a stand-alone multi-threaded program
        // init a threadpool with max NUMBER_OF_THREADS
        ExecutorService pool = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        List<Path> paths = Files.list(FOLDER_OF_TEXT_FILES).collect(Collectors.toList());
        // word -> (filename -> cnt)
        Map<String, Map<String, Integer>> wordCount = new ConcurrentHashMap<>();
        List<String> fileNames = new ArrayList<>();

        List<Future<?>> futures = new ArrayList<>();
        for (Path path : paths) {
            futures.add(pool.submit(() -> {
                try {
                    List<String> content = Files.readAllLines(path);
                    Map<String, Integer> result = content.stream().flatMap(c -> Arrays.stream(c.split("\\W+")))
                            .filter(s -> !s.equals(""))
                            .map(String::toLowerCase)
                            .collect(groupingBy(identity(), summingInt(e -> 1)));

                    synchronized (wordCount) {
                        String fileName = getFileName(path);
                        fileNames.add(fileName);
                        result.forEach((k, v) -> {
                            wordCount.putIfAbsent(k, new ConcurrentHashMap<>());
                            Map<String, Integer> subCnt = wordCount.get(k);
                            subCnt.put(fileName, v);
                        });
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
        }
        // wait for completion
        for (Future<?> future : futures) {
            future.get();
        }

        // sort filenames and words
        Collections.sort(fileNames);
        List<Map.Entry<String, Map<String, Integer>>> sorted
                = wordCount.entrySet().stream().sorted(comparingByKey()).collect(Collectors.toList());

        // figure out length of each column
        int maxKeyLen = 0;
        for (Map.Entry<String, Map<String, Integer>> en : sorted) {
            maxKeyLen = Math.max(maxKeyLen, en.getKey().length());
        }
        List<Integer> colLen = new ArrayList<>();
        colLen.add(maxKeyLen+1);
        fileNames.forEach(f -> colLen.add(f.length()+4));

        // format of each line
        StringBuilder formatSb = new StringBuilder();
        for (Integer i : colLen) {
            formatSb.append("%-").append(i).append("s");
        }
        formatSb.append("%-5s").append("\n");    // 5 for the last column of "total"
        String format = formatSb.toString();

        // write results
        try (BufferedWriter writer = Files.newBufferedWriter(WORD_COUNT_TABLE_FILE))
        {
            // write first line: the title of filenames
            String[] strs = new String[fileNames.size()+2];
            strs[0] = "";
            for (int i = 0; i < fileNames.size(); i++) {
                strs[i+1] = fileNames.get(i);
            }
            strs[strs.length-1] = "total";
            writer.write(String.format(format, strs));

            // write each line
            for (Map.Entry<String, Map<String, Integer>> entry : sorted) {
                String[] line = new String[fileNames.size()+2];
                line[0] = entry.getKey();
                int total = 0;
                for (int i = 0; i < fileNames.size(); i++) {
                    int cnt = entry.getValue().getOrDefault(fileNames.get(i), 0);
                    total += cnt;
                    line[i+1] = String.valueOf(cnt);
                }
                line[line.length-1] = String.valueOf(total);
                writer.write(String.format(format, line));
            }
        }
        pool.shutdown();
    }

    private static String getFileName(Path path) {
        String str = path.getFileName().toString();
        int pos = str.lastIndexOf(".");
        if (pos == -1) return str;
        return str.substring(0, pos);
    }
}
