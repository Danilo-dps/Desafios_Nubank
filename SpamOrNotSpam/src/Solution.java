import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    public static List<String> getSpamEmails(List<String> subjects, List<String> spam_words) {
        List<String> spamStatusList = new ArrayList<>();

        List<String> additionalSpamWords = Arrays.asList(
            "free",
            "promotion",
            "offer",
            "money",
            "urgent", 
            "millions"
        );
        
        List<String> allSpamWords = new ArrayList<>(spam_words);
        allSpamWords.addAll(additionalSpamWords);

        List<String> lowerCaseSpamWords = allSpamWords.stream()
            .map(String::toLowerCase)
            .collect(Collectors.toList());

        for (String subject : subjects) {
            String lowerCaseSubject = subject.toLowerCase();
            boolean isSpam = false;

            for (String spamWord : lowerCaseSpamWords) {
                if (lowerCaseSubject.contains(spamWord)) {
                    isSpam = true;
                    break;
                }
            }

            spamStatusList.add(isSpam ? "spam" : "not_spam");
        }

        return spamStatusList;
    }
}



public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int subjectsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> subjects = IntStream.range(0, subjectsCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        int spam_wordsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> spam_words = IntStream.range(0, spam_wordsCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        List<String> result = Result.getSpamEmails(subjects, spam_words);

        bufferedWriter.write(
            result.stream()
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}