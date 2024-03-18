import java.sql.*;

public class DMTest{
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url="jdbc:dm://10.19.64.238:5236";
        String username="IDRS6_BUSI";
        String password="IDRS_ADMIN";
        Connection conn =null;
        Class.forName("dm.jdbc.driver.DmDriver");
        conn = DriverManager.getConnection(url,username,password);
        Statement sta = conn.createStatement();

        String sql="select ROW_NO, B, V from TS_PBCJ_ZGPRDINFO_V1 where\n" +
                "SERIAL_NO = '1734039908080517122' and\n" +
                "trim(V) is not null and ROW_NO in\n" +
                "( select t.ROW_NO from\n" +
                " (select ROW_NO, (regexp_substr(V, '[^,]+', 1, LEVEL)) AS V  from\n" +
                "  (select ROW_NO, V from TS_PBCJ_ZGPRDINFO_V1 where trim(V) is not null\n" +
                "  and SERIAL_NO = '1734039908080517122'\n" +
                "  ) CONNECT BY LEVEL <= length(V) - length(regexp_replace(V, ',', '')) + 1\n" +
                "  and row_no = PRIOR row_no\n" +
                "  and PRIOR dbms_random.value is not null\n" +
                " ) t\n" +
                " where t.V not in (select DICT_ITEM_CODE from ts_check_dict_item b where b.dict_entry_code = 'Z0009' and b.assert_flag = '01')\n" +
                ")";
        ResultSet rst = sta.executeQuery(sql);



        while(rst.next()){
            System.out.println(rst);
        }
        rst.close();
        conn.close();
        sta.close();
    }
}