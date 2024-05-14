import java.sql.*;
import java.util.Random;

public class BatchInsert {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int STRING_LENGTH = 10; // 你可以根据需要修改这个长度
    private static final Random RANDOM = new Random();
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection conn =null;
        Statement statement = null;
        ResultSet rs = null;
        Class.forName("oracle.jdbc.OracleDriver");
        conn = DriverManager.getConnection("jdbc:oracle:thin:@10.20.38.215:1521:orcl", "bfam6", "bfam6");
        statement = conn.createStatement();
        StringBuffer sb = new StringBuffer();
        String s1= new String("insert into ts_transinfo VALUES ('1', '1', '");
        String s2=new String("', '1', '1', '1', '1', '1', '1',  '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1');");
        for(int i=0;i<400;i++){
            sb.append(s1+generateRandomString()+s2);
        }
        System.out.println(sb);
        //System.out.println(statement.execute(sb.toString()));;
    }
    public static String generateRandomString() {
        StringBuilder stringBuilder = new StringBuilder(STRING_LENGTH);
        for (int i = 0; i < STRING_LENGTH; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            stringBuilder.append(CHARACTERS.charAt(index));
        }
        return stringBuilder.toString();
    }
}
