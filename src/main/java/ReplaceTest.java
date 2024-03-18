public class ReplaceTest {
    public static void main(String[] args) {
        String s1 = "a.`a`";
        s1=s1.replace("`","");
        System.out.println(s1);
    }
}
