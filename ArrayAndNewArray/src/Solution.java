import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;



class Result {

	public static int findMaximumGreatness(List<Integer> arr) {

        Map<Integer, Integer> indexMap = new HashMap<>();
        List<Integer> sortedArr = arr.stream()
            .sorted()
            .collect(Collectors.toList());

        for (int i = 0; i < sortedArr.size(); i++) {
            indexMap.put(sortedArr.get(i), i);
        }

        List<Integer> newArr = arr.stream()
            .map(indexMap::get)
            .collect(Collectors.toList());

        return Collections.max(newArr);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = IntStream.range(0, arrCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.findMaximumGreatness(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
