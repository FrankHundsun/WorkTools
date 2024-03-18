import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TableChecker {

    public static void main(String[] args) throws ClassNotFoundException {
        // 文件路径
        String filePath = "D:\\Projects\\Scripts\\spsql\\BAM6.0V202006.00.000.20200618\\01_ora_ddl.sql";

        // 读取文件内容
        String fileContent = readFileContent(filePath);

        // 正则表达式匹配
        String regex = "\\bcreate\\s+table\\s+(\\w+)\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fileContent);

        // JDBC连接Oracle数据库
        String jdbcUrl = "jdbc:oracle:thin:@10.20.25.220:1521:orcl";
        String username = "test";
        String password = "test";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            while (matcher.find()) {
                String tableName = matcher.group(1);
                boolean tableExists = checkTableExists(connection, tableName);

                System.out.println("Table Name: " + tableName);
                System.out.println("Table Exists: " + tableExists);
                System.out.println("--------------");
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String readFileContent(String filePath) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return content.toString();
    }

    private static boolean checkTableExists(Connection connection, String tableName) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM USER_TABLES WHERE TABLE_NAME = '" + tableName.toUpperCase() + "'");
        resultSet.next();
        int count = resultSet.getInt(1);
        statement.close();

        return count > 0;
    }
}

