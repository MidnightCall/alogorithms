import org.junit.Test;

/**
 * @ClassName KMP
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2023/1/2 20:44
 * @Version
 */

public class KMP {
    public static int getIndexOf(String str1, String str2){
        int n1 = str1.length();
        int n2 = str2.length();
        if(str1 == null || str2 == null || n2 < 1 || n1 < n2){
            return -1;
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[] next = getNextArray(chs2);
        int i1 = 0;
        int i2 = 0;

        while(i2 < n2 && i1 < n1){
            if(chs2[i2] == chs1[i1]){
                ++i1;
                ++i2;
            }else if(next[i2] == -1){
                ++i1;
            }else{
                i2 = next[i2];
            }
        }

        return i2 == n2 ? i1 - i2 : -1;
    }

    public static int[] getNextArray(char[] ms){
        if(ms == null){
            return new int[]{-1};
        }

        int n = ms.length;
        int[] next = new int[n];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while(i < n){
            if(ms[i - 1] == ms[cn]){
                next[i++] = ++cn;
            }else if(cn > 0){
                cn = next[cn];
            }else{
                next[i++] = 0;
            }
        }

        return next;
    }

    @Test
    public void test(){
        String str1 = "ahaschsnaldld";
        String str2 = "chs";
        System.out.println(getIndexOf(str1, str2));
    }

}
