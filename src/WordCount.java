import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class WordCount {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите минимальную длину слова");
        int minLength = Integer.parseInt(input.readLine());
        System.out.println("Введите максимальную длину слова");
        int maxLength = Integer.parseInt(input.readLine());
        Files.lines(Paths.get("tolstoy.txt"))
                .flatMap(x -> Arrays.stream(x.replaceAll("[^A-Za-zА-Яа-я]", " ").split("\\s+")))
                .map(String::toLowerCase)
                .filter(word -> word.length() >= minLength)
                .filter(word -> word.length() <= maxLength)
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()))
                .entrySet().stream()
                .sorted(Comparator.comparingLong(x -> -x.getValue()))
                .forEach(x -> System.out.println(x.getKey() + ": " + x.getValue()));
    }
}
