import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DayTest {
    public static void main(String[] args) {
        //设置日期为传入的业务日期
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat("yyyyMMdd").parse(String.valueOf("20240419")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 将日期设置为上一个月的第一天
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        // 获取上一个月的总天数
        int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        // 设置日期为上个月的最后一天
        calendar.set(Calendar.DAY_OF_MONTH, lastDayOfMonth);
        // 格式化上个月的最后一天为字符串
        Integer lastDayOfPreviousMonth = Integer.valueOf(new SimpleDateFormat("yyyyMMdd").format(calendar.getTime()));
        System.out.println(lastDayOfPreviousMonth);
    }
}
