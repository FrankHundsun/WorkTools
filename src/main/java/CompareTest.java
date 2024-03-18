import com.sun.org.apache.bcel.internal.classfile.StackMapEntry;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CompareTest {
    public static void main(String[] args) throws IOException {
        HashMap<String,String> hp1 = new HashMap<String,String>();
        HashMap<String,String> hp2 = new HashMap<String,String>();
        int i=0;
        String s1,s2;
        //hp1连' ',''，hp2连''
        Connection conn =null;
        Statement statement = null;
        ResultSet rs = null;
        try {

             Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@10.20.25.220:1521:orcl", "bfam6", "bfam6");
            statement = conn.createStatement();
            rs = statement.executeQuery("select TABLE_NAME,COLUMN_NAME,DATA_DEFAULT from USER_TAB_COLUMNS");

            //第五步：处理结果集
            while (rs.next())
            {
                i++;
                s1=rs.getString("TABLE_NAME")+rs.getString("COLUMN_NAME");
                s2=rs.getString("DATA_DEFAULT");
                hp1.put(s1,s2);
               if(i%1000==0){
                   System.out.println(i);
               }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //第六步：关闭资源
            try {
                if (rs!=null) rs.close();
                if (statement!=null) statement.close();
                if (conn!=null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println("hp1生成完成");

        i=0;
        try {

            Class.forName("dm.jdbc.driver.DmDriver");
            conn = DriverManager.getConnection("jdbc:dm://10.19.64.238:5236", "IDRS6_BUSI", "IDRS_ADMIN");
            statement = conn.createStatement();
            rs = statement.executeQuery("select TABLE_NAME,COLUMN_NAME,DATA_DEFAULT from USER_TAB_COLUMNS ");

            //第五步：处理结果集
            while (rs.next())
            {
               i++;
                s1=rs.getString("TABLE_NAME")+rs.getString("COLUMN_NAME");
                s2=rs.getString("DATA_DEFAULT");
                hp2.put(s1,s2);
                if(i%1000==0){
                    System.out.println(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //第六步：关闭资源
            try {
                if (rs!=null) rs.close();
                if (statement!=null) statement.close();
                if (conn!=null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("hp2生成完成");



        String desktopPath = System.getProperty("user.home") + "\\Desktop\\";
        FileWriter writer = null;

        String filePath = desktopPath + "kk.txt";
        writer = new FileWriter(filePath, true);
        String key="";
        String valueHp1="";
        String valueHp2="";
        for (Map.Entry<String, String> entry : hp1.entrySet()) {
             key = entry.getKey();
             if(key==null) continue;

             valueHp1 = entry.getValue();
            if(valueHp1==null) continue;

            // 根据key在第二个HashMap（hp2）中查找对应的value
             valueHp2 = hp2.get(key);
            if(valueHp2==null) continue;

            // 比较两个value是否相等
            if (valueHp1.equals(valueHp2)) {

            } else {
                writer.write("Key: " + key + " - Values are different - hp1: " + valueHp1 + ", hp2: " + valueHp2);
                System.out.println("Key: " + key + " - Values are different - hp1: " + valueHp1 + ", hp2: " + valueHp2);
            }
        }



    }

}
