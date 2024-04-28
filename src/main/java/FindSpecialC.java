import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FindSpecialC {

    public static void main(String[] args) {
        String directoryPath = "D:\\Projects\\IDRS6.0-obrain"; // 修改为你想要遍历的目录路径
        File directory = new File(directoryPath);
        processDirectory(directory);
    }

    public static void processDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    processDirectory(file);
                } else {
                    if (file.getName().toLowerCase().endsWith(".sql")) {
                        processSQLFile(file);
                    }
                }
            }
        }
    }

    public static void processSQLFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.contains("&")) {
                    System.out.println("File: " + file.getAbsolutePath());
                    System.out.println("Line: " + lineNumber + ": " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
