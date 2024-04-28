import java.util.ArrayList;
import java.util.List;

class Solution {
    public static void main(String[] args) {
        findAnagrams("cbaebabacd","abc");
    }
    static public List<Integer> findAnagrams(String s, String p) {
        int len1 =s.length();
        int len2 =p.length();
        int[] array1 =new int[len1];
        int flag = 1;
        for(int i=0;i<len2;i++){
            array1[i] = p.charAt(i)-'a'+1;
            flag= flag*array1[i];
        }
        int[] array2=new int[len1];
        for(int i=0;i<len1;i++){
            array2[i]=s.charAt(i)-'a'+1;
            System.out.print(array2[i]+" ");
        }
        System.out.println();
        List<Integer> ans =new ArrayList<Integer>();
        for(int i=0;i<=len1-len2;i++){
            int f=1;
            for(int j=i;j<i+len2;j++){
                f=f*array2[j];
            }
            System.out.print(f+" ");
            if(f==flag){
                ans.add(i);
            }
        }
        return ans;
    }
}