import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckRuleTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection conn =null;
        Statement statement = null;
        ResultSet rs = null;
        Class.forName("oracle.jdbc.OracleDriver");
        conn = DriverManager.getConnection("jdbc:oracle:thin:@10.20.25.220:1521:orcl", "bfam6", "bfam6");
        statement = conn.createStatement();

        // 指定要读取的txt文件路径
        String filePath = "C:\\Users\\hucong51611\\Desktop\\3.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            // 定义正则表达式，包含捕获组
            String regex = "select '[^']*', ('[^']*')"; // 替换为你的正则表达式

            // 使用正则表达式匹配字符串
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(content);
            int i=0;

            List<String> list1 =new ArrayList<String>();
            List<String> list2 =new ArrayList<String>();
            rs = statement.executeQuery("select distinct interface_code,task_name from TS_TASKINFOIN");
            while(rs.next())
            {
                list1.add(rs.getString(1));
                list2.add(rs.getString(2));
            }
            for(int j=0;j< list1.size();j++)
            {
                rs=statement.executeQuery("select count(1) from tsys_dict_item where dict_item_code = "+"\'"+list1.get(j)+"\'");
                while(rs.next())
                {
                    if(rs.getInt(1)==0)
                    {
//                        System.out.println(list1.get(j));
//                        System.out.println(list2.get(j));
                        String s1="'"+list1.get(j)+"'";
                        String s2="'"+list2.get(j)+"'";
                        //values ('TDMDZTXX','BFAM_JKDM','特定目的载体信息',120);
                        String s = "insert into tsys_dict_item (dict_item_code, dict_entry_code, dict_item_name, dict_item_order) values ("+s1+","+"'BFAM_JKDM'"+","+s2+","+"0);";
                        System.out.println(s);
                    }
                }
            }
            // 迭代找到所有匹配项
//            while (matcher.find()) {
//                // 提取第一个捕获组的值
//                String capturedValue = matcher.group(1);
//                //System.out.println("select count(1) from ts_reportcheckrule where RULE_NO = "+matcher.group(1));
//                rs = statement.executeQuery("select count(1) from tsys_dict_item where dict_item_code = "+matcher.group(1).toUpperCase());
//                while(rs.next())
//                {
//                    if(rs.getInt(1)==0)
//                        System.out.println(matcher.group(1));
//                    //System.out.println(rs.getInt(1));
//                }
//                // 输出每个匹配项的捕获组值
//                //System.out.println("Captured Value: " + capturedValue);
//            }
//
//            // 如果没有匹配项
//            if (!matcher.find()) {
//                System.out.println("No match found");
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
