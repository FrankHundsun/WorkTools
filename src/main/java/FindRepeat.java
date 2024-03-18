import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FindRepeat {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\hucong51611\\Desktop\\1.txt"; // 替换为你的txt文件路径
        Map<String, Integer> lineMap = new HashMap<>();
        int lineNumber = 1;

        System.out.println("hello world");
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(lineNumber);
                if (lineMap.containsKey(line)) {
                    System.out.println("Duplicate value found at line: " + lineNumber + ". Original line: " + lineMap.get(line));
                } else {
                    lineMap.put(line, lineNumber);
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}