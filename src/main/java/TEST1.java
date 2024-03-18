import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TEST1 {

    public static void main(String[] args) {
        String baseFolder = "C:\\Users\\hucong51611\\Desktop\\nnn";
        int maxLen = 6;
        for (int i = 1; i < maxLen; i++) {
            String sourcePath = baseFolder + "\\namingsql" + (i);
            String targetPath = baseFolder + "\\namingsql" + (i+1);

            File sourceDir = new File(sourcePath);
            File targetDir = new File(targetPath);

            // 创建目标目录
            if (!targetDir.exists()) {
                targetDir.mkdir();
            }

            // 遍历源目录中的文件
            File[] files = sourceDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    String targetFilePath = targetPath + "\\" + file.getName();
                    Path targetFilePathObj = Paths.get(targetFilePath);

                    // 检查目标目录是否存在同名文件，如果不存在则复制
                    if (!Files.exists(targetFilePathObj)) {
                        try {
                            Files.copy(file.toPath(), targetFilePathObj);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        System.out.println("复制完成");
    }
}
