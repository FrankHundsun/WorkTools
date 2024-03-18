import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindFile {
    static Connection conn =null;
    static Statement statement = null;
    static ResultSet rs = null;
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn =null;
        Statement statement = null;
        ResultSet rs = null;
        Class.forName("oracle.jdbc.OracleDriver");
        conn = DriverManager.getConnection("jdbc:oracle:thin:@10.20.25.220:1521:orcl", "test", "test");
        statement = conn.createStatement();
        String basePath = "D:\\Projects\\Scripts\\spsql";
        findFile(basePath,statement);
        return ;
    }
    public static void findFile(String path,Statement statement) throws ClassNotFoundException, SQLException {


        File file = new File(path);
        File[] files = file.listFiles();
        if(files!=null)
        {
            for(File f: files)
            {
                if(f.isDirectory())
                {
                    findFile(f.getPath(),statement);
                }else
                {
                    if(f.getName().contains("ddl")&&f.getParentFile().getName().contains("BAM"))
                    {
                        System.out.println(f.getPath());
                        f.delete();
//                        try (BufferedReader reader = new BufferedReader(new FileReader(f.getPath()))) {
//                            StringBuilder content = new StringBuilder();
//                            String line;
//                            while ((line = reader.readLine()) != null) {
//                                content.append(line).append("\n");
//                            }
//                            String regex = "";
//                            Pattern pattern=null;
//                            Matcher matcher=null;
//
//                            regex="alter table ([^ ]*) modify ([^ ]*).*;";
//                            pattern = Pattern.compile(regex);
//                            matcher = pattern.matcher(content);
//                            while(matcher.find())
//                            {
//                                String s1=matcher.group(1);
//                                String s2=matcher.group(2);
//
//                                rs = statement.executeQuery("SELECT data_type,data_precision,data_scale,data_default   FROM USER_TAB_COLUMNS WHERE TABLE_NAME = UPPER('"+s1+"') AND COLUMN_NAME = UPPER('"+s2+"')");
//                                while(rs.next())
//                                {
//                                    if(matcher.group(0).toLowerCase().contains("number")) {
//
//                                        System.out.println("-----" + matcher.group(0));
//                                        System.out.println("类型:" + rs.getString(1) + "位数：" + rs.getString(2) + "精度:" + rs.getString(3) + "默认值:" + rs.getString(4));
//                                    }
//                                }
//                            }
//
//                            regex="alter table ([^ ]*) add ([^ ]*).*;";
//                            pattern = Pattern.compile(regex);
//                            matcher = pattern.matcher(content);
//                            while(matcher.find())
//                            {
//                                String s1=matcher.group(1);
//                                String s2=matcher.group(2);
//
//                                rs = statement.executeQuery("SELECT data_type,data_precision,data_scale,data_default   FROM USER_TAB_COLUMNS WHERE TABLE_NAME = UPPER('"+s1+"') AND COLUMN_NAME = UPPER('"+s2+"')");
//                                while(rs.next())
//                                {
//                                    if(matcher.group(0).toLowerCase().contains("number")) {
//
//                                        System.out.println("-----" + matcher.group(0));
//                                        System.out.println("类型:" + rs.getString(1) + "位数：" + rs.getString(2) + "精度:" + rs.getString(3) + "默认值:" + rs.getString(4));
//                                    }                                }
//                            }
//
//
//
//
//
//
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
                    }
                }
            }
        }
    }
}
