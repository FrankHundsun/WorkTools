import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwoFilesRegx {

    public static void main(String[] args) {


        String file1Path = "D:\\Projects\\Scripts\\bank\\mtyh\\mtyh_init.sql";
        String file2Path = "D:\\Projects\\IDRS6.0-obrain\\idrs-basicdatamanage\\idrs-basicdatamanage-service\\deploy\\sqls\\oracle\\idr_db\\201901.00.000\\ts_taskinfoin_infomation.sql";
        String regex = "('C1', '[^']*').*from dual";

        try {
            // 读取文件1
            String contentFile1 = readFile(file1Path);

            // 使用正则表达式匹配字段
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(contentFile1);
            String contentFile2 = readFile(file2Path);
            while (matcher.find()) {
                System.out.println("在文件1中找到匹配的字段: " + matcher.group(1));

                // 读取文件2


                // 搜索文件2中是否存在匹配字段
                if (contentFile2.contains(matcher.group())) {
                    System.out.println("找到了匹配的字段.");
                } else {
                    System.out.println("没有找到匹配的字段.");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}
