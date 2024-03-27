import org.junit.Test;

import java.io.CharArrayReader;

/**
 * @ClassName Manacher
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2023/1/5 16:03
 * @Version
 */

public class Manacher {
    public int maxLcpsString(String str){
        if(str == null || str.length() < 1){
            return 0;
        }

        char[] charArr = manacherString(str);
        int[] pArr = new int[charArr.length];
        int max = Integer.MIN_VALUE;
        int R = -1;
        int C = -1;

        for(int i = 0; i < charArr.length; ++i){
            pArr[i] = R <= i ? 1 : Math.min(R - i, pArr[2 * C - i]);

            while(i + pArr[i] < charArr.length && i - pArr[i] > -1){
                if(charArr[i + pArr[i]] == charArr[i - pArr[i]]){
                    ++pArr[i];
                }else{
                    break;
                }
            }
            if(i + pArr[i] > R){
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]);
        }

        return max - 1;
    }

    public char[] manacherString(String str){
        char[] charArr = new char[str.length() * 2 + 1];
        int index = 0;
        for(int i = 0; i < charArr.length; ++i){
            charArr[i] = (i & 1) == 0 ? '#' : str.charAt(index++);
        }
        return charArr;
    }


    @Test
    public void test() {
        String str1 = "abc1234321ab";
        System.out.println(maxLcpsString(str1));
    }
}
