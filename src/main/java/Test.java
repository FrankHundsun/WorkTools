import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class Test {
    public static void main(String[] args)
    {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        FileWriter writer = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 连接到数据库
            conn = DriverManager.getConnection("jdbc:mysql://10.20.44.213:33061/bfam6_ceshi?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf-8&tinyInt1isBit=false", "root", "root");

            // 创建statement对象
            stmt = conn.createStatement();

            // 查询某张表的某个字段的所有值
            String query = "SELECT check_sql FROM ts_regulatoryreportcheck_rule";
            rs = stmt.executeQuery(query);


            // 获取桌面路径
            String desktopPath = System.getProperty("user.home") + "\\Desktop\\";
            System.out.println(desktopPath);
            // 打开一个文件用于记录报错信息
            String filePath = desktopPath + "testmysql2.txt";
            writer = new FileWriter(filePath, true);

            while (rs.next()) {
                String sqlToExecute = rs.getString("check_sql");
                try {
                    // 执行SQL语句
                    Statement execStmt = conn.createStatement();
                    execStmt.execute(sqlToExecute);
                    execStmt.close();

                    // 如果执行成功，记录日志
                    //writer.write("SQL执行成功: " + sqlToExecute + "\n");
                } catch (SQLException e) {
                    // 如果报错，记录错误信息到文件
                    writer.write("SQL执行错误: " + sqlToExecute + "\n");
                    writer.write("错误信息: " + e.getMessage() + "\n");
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                if (writer != null) writer.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
