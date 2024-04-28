import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test2 {

    public static void main(String[] args) {
        String fileName = "C:\\Users\\hucong51611\\Desktop"; // 替换为你的 Excel 文件路径
        Map<String, Object> firstRowMap = new HashMap<>();

        EasyExcel.read(fileName, null, new AnalysisEventListener<Object>() {
            private int rowNum = 0;
            private List<String> head;

            @Override
            public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
                this.head = new ArrayList<>();
                for (Map.Entry<Integer, String> entry : headMap.entrySet()) {
                    this.head.add(entry.getValue());
                }
            }

            @Override
            public void invoke(Object object, AnalysisContext context) {
//                if (rowNum == 0) { // 只读取第一行数据
//                    for (int i = 0; i < head.size(); i++) {
//                        firstRowMap.put(head.get(i), context.readCellValue(i));
//                    }
//                    // 读取完第一行后停止读取
//                    context.stopReading();
//                }
                rowNum++;
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                // 所有数据解析完成后会调用此方法，但在这里我们不需要它做什么  
            }
        }).sheet().doRead();

        // 输出第一行的数据和列名  
        firstRowMap.forEach((key, value) -> System.out.println("列名: " + key + ", 值: " + value));
    }
}