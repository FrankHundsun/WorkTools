import java.io.File;

public class deleteNamingsql {

    public static void main(String[] args) {
        String basePath = "D:\\project\\Scripts\\bank"; // 你要遍历的基础路径
        File baseDir = new File(basePath);

        if (!baseDir.exists() || !baseDir.isDirectory()) {
            System.out.println("指定路径不存在或不是一个目录");
            return;
        }

        // 调用递归方法来处理目录
        deleteNamingsqlDirectories(baseDir);
    }

    public static void deleteNamingsqlDirectories(File directory) {
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    if (file.getName().equals("namingsql")) {
                        // 如果找到名为"namingsql"的目录，则删除它及其所有文件
                        deleteDirectory(file);
                    } else {
                        // 递归处理子目录
                        deleteNamingsqlDirectories(file);
                    }
                }
            }
        }
    }

    public static void deleteDirectory(File directory) {
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }

        directory.delete();
    }
}
