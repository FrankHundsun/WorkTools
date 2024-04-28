import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ExcleTest {
    @ExcelProperty(value="A",index = 0)
    private String a;
    @ExcelProperty(value="B",index = 1)
    private String b;
    @ExcelProperty(value="C",index = 2)
    private String c;
    @ExcelProperty(value="D",index = 3)
    private String d;
    @ExcelProperty(value="E",index = 4)
    private String e;
    @ExcelProperty(value="F",index = 5)
    private String f;
    @ExcelProperty(value="G",index = 6)
    private String g;
    @ExcelProperty(value="H",index = 7)
    private String h;
    @ExcelProperty(value="I",index = 8)
    private String i;
    @ExcelProperty(value="J",index = 9)
    private String j;
    @ExcelProperty(value="K",index = 10)
    private String k;
    @ExcelProperty(value="L",index = 11)
    private String l;
    @ExcelProperty(value="M",index = 12)
    private String m;
}
