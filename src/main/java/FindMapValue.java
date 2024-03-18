import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.util.List;

public class FindMapValue {

    public static void main(String[] args) {
        // 指定文件路径
        String filePath = "D:\\Projects\\IDRS6.0-obrain\\idrs-basicdatamanage\\idrs-basicdatamanage-service\\src\\main\\java\\com\\hundsun\\obrain\\productmanage\\service\\impl\\PrdInfoAchieveServiceImpl.java";
        HashSet hs= new HashSet<String>();
        // 读取文件内容
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            // 定义正则表达式
            String regex = "msgMap.get\\(\"([^\"]*)\"";

            // 编译正则表达式
            Pattern pattern = Pattern.compile(regex);

            // 遍历文件内容，匹配正则表达式并打印结果
            for (String line : lines) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    if(line.contains("format"))
                    hs.add(matcher.group(1));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Iterator<String> iterator = hs.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
