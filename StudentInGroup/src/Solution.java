import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;



class Result {

    public static int groupDivision(List<Integer> levels, int maxSpread) {
        Collections.sort(levels);
        
        int totalGroups = 0;
        int startLevel = levels.get(0);
        
        for (int i = 1; i < levels.size(); i++) {
            if (levels.get(i) - startLevel > maxSpread) {
                totalGroups++;
                startLevel = levels.get(i);
            }
        }
        
        totalGroups++;
        
        return totalGroups;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int levelsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> levels = IntStream.range(0, levelsCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int maxSpread = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.groupDivision(levels, maxSpread);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}