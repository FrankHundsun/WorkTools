import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test20240401 {
    public static void main(String[] args) {
        //设计方案，先读取文本，放入map了，key的AA这种，自己根据第多少个生成
        //easyexcle读取excle，把关键信息拿出来，再和sql拼接在一起
        //打印这些sql，手动去除ass这样的
        Map<String,String> hs = new HashMap<>();
        // 指定要读取的文件路径 报表文件的表头
        String filePath = "C:\\Users\\hucong51611\\Desktop\\zg05column.txt";
        //规则文件
        String filePaath="C:\\Users\\hucong51611\\Desktop\\regulatory.xlsx";
        // 创建一个ArrayList来存储单词
        ArrayList<String> words = new ArrayList<>();

        try {
            // 创建一个BufferedReader对象来读取文件
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            // 读取文件中的一行
            String line = reader.readLine();

            // 如果文件不为空
            if (line != null) {
                // 使用空格分割单词并存入数组
                String[] wordArray = line.split("\\s+");

                // 将单词添加到ArrayList中
                for (String word : wordArray) {
                    words.add(word);
                }
            }

            // 关闭文件读取器
            reader.close();
        } catch (IOException e) {
            // 捕获可能出现的异常
            e.printStackTrace();
        }

        int len = words.size();
        String s;
        char a='A';
        for(int i=0;i<len;i++){
            if(i<26){
                a=(char)('A'+i);
                s=String.valueOf(a);
            }else{
                int j=i%26;
                char b =(char)('A'+j);
                int k=i/26;
                char c =(char)('A'+k-1);
                s=String.valueOf(c)+String.valueOf(b);
            }
            hs.put(words.get(i),s);
        }



        // 读取 Excel 文件
        EasyExcel.read(new File(filePaath), ExcleTest.class, new AnalysisEventListener<ExcleTest>() {

            @Override
            public void invoke(ExcleTest excleTest, AnalysisContext context) {
                // 这里可以处理每一行的数据，例如输出到控制台
                String code = excleTest.getA();
                String column=excleTest.getC();
                String ruleType=excleTest.getD();
                String detailRuleType=excleTest.getE();
                String desc=excleTest.getG();
                String cc="";
                // cc=hs.getOrDefault(excleTest.getC()," ");
                for (Map.Entry <String, String > entry: hs.entrySet()) {
                    if(entry.getKey().contains(column.substring(0,5))){
                         cc= entry.getValue();
                    }
                }
                String J = excleTest.getJ();
                String J1="";
                int j2=0;
                for(int i=0;i<J.length()-6;i++){
                    if(J.substring(i,i+6).equals("负债项目=\"")){
                        for(int j=i;j<J.length();j++){
                            if(J.charAt(j)=='-'){
                            J1=J.substring(i+6,j);
                            break;
                            }
                        }
                    }
                }
                for(int i=0;i<J.length()-6;i++){
                    if(J.substring(i,i+6).equals("产品种类=\"")){
                        j2=Integer.valueOf(J.substring(i+6,i+7));
                    }
                }
                String sql ="delete from ts_regulatoryreportcheck_rule where RULE_CODE = '"+code+"';\n" +
                        "insert into ts_regulatoryreportcheck_rule (RULE_CODE, REPORT_DETAIL_TYPE, COLUMN_NAME, RULE_TYPE, RULE_DETAIL_TYPE,RELATED_REPORT, RULE_FLAG, RULE_DESC, CHECK_SQL, CHECK_FUNC)\n" +
                        "values ('"+code+"', 'qgpbcjzg05', '"+column+"', '业务逻辑类', '数值比较校验', 'ZG05资管产品资产负债信息', 2,'"+desc+"','select t1.A,t1.B,t1."+cc+" from ts_pbcj_zgprdzcfzinfo_v1 t1 left join ts_pbcj_zgprdspvdetail_v1 t2 on t1.A = t2.A and t1.B = t2.F where t1.C = ''1'' and t2.B = "+J1+ "'' and t2.C = "+j2+" group by t1.A,t1.B,t1."+cc+" having coalesce(t1."+cc+",0) <> sum(coalesce(t2.G,0)) and t1.SERIAL_NO = ''@zg05_serial_no'' and t2.SERIAL_NO = ''@zg08_serial_no''',' ');";
                System.out.println(sql);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
            }
        }).sheet().doRead();

    }
}
