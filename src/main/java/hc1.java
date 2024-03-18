import java.io.File;

public class hc1 {
    private static final String SPECIAL_BANK_NAME = "${special_bank_name}";
    private static final String VOLATILE_PARENT = "volatile";

    public static void main(String[] args) {
        // 指定要遍历的路径
        String basePath = "D:\\Projects\\IDRS6.0-obrain\\idrs-basicdatamanage\\idrs-basicdatamanage-service\\deploy\\sqls";

        // 调用遍历方法
        traverseDirectory(basePath);
    }

    private static void traverseDirectory(String path) {
        File directory = new File(path);

        // 检查目录是否存在
        if (directory.exists() && directory.isDirectory()) {
            // 获取目录下的所有子文件和目录
            File[] files = directory.listFiles();

            if (files != null) {
                // 遍历所有子文件和目录
                for (File file : files) {

                    if(file.getName().contains(".vm"))
                    {
                        System.out.println(file.getPath());
                    }
                    // 如果是目录，则进行处理
                    if (file.isDirectory()) {
                        // 检查是否为特殊银行目录
//                        if (file.getName().equals(SPECIAL_BANK_NAME)) {
//                            // 获取父目录
//                            File parent = file.getParentFile();
//
//                            // 检查父目录是否为volatile
//                            if (parent != null && !parent.getName().equals(VOLATILE_PARENT)) {
//                                // 输出符合条件的路径
//                                System.out.println("符合条件的路径：" + file.getAbsolutePath());
//                            }
//                        }

                        // 递归调用，继续遍历子目录
                        traverseDirectory(file.getAbsolutePath());
                    }
                }
            }
        }
    }
}
