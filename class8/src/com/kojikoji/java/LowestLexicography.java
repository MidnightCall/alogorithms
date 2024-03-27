package com.kojikoji.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @ClassName LowestLexicography
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2022/12/29 15:51
 * @Version
 */

public class LowestLexicography {

    public static class MyComparator implements Comparator<String>{

        @Override
        public int compare(String a, String b) {
            return (a + b).compareTo(b + a);
        }
    }

    public static String lowestString(String[] strs){
        if(strs == null || strs.length == 0){
            return "";
        }

        StringBuilder builder = new StringBuilder();
        Arrays.sort(strs, new MyComparator());
        for(String word : strs){
            builder.append(word);
        }

        return builder.toString();
    }

    public static void main(String[] args){
        String[] strs1 = {"jibw", "ji", "jp", "bw", "jibw"};
        System.out.println(lowestString(strs1));

        String[] str2 = {"ba", "b"};
        System.out.println(lowestString(str2));
    }
}
