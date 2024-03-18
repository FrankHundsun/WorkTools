import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class DeleteBussinessAndInit {

    public static void main(String[] args) throws IOException {
        String directoryPath = "D:\\Projects\\Scripts\\spsql"; // 替换为实际的路径
        Files.walkFileTree(Paths.get(directoryPath), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                String fileName = file.getFileName().toString();
                if (fileName.contains("initfunction") || fileName.contains("bussiness")) {
                    System.out.println(file);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
