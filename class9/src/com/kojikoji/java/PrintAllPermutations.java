package com.kojikoji.java;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;

/**
 * @ClassName PrintAllPermutations
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2023/1/1 14:42
 * @Version
 */

public class PrintAllPermutations {
    public static String[] Permutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        if(str == null || str.length() == 0){
            return null;
        }
        process(str.toCharArray(), 0, res);
        String[] ret = (String[]) res.toArray(new String[0]);
        return ret;
    }

    public static void process(char[] chs, int i, List<String> res){
        if(i == chs.length){
            res.add(String.valueOf(chs));
            return;
        }
        boolean[] visted = new boolean[26];
        for(int j = i; j < chs.length; ++j){
            int index = chs[j] - 'a';
            if(!visted[index]){
                visted[index] = true;
                swap(chs, i, j);
                process(chs, i+1, res);
                swap(chs, i, j);
            }
        }
    }

    public static void swap(char[] chs, int i, int j){
        char temp = chs[i];
        chs[i] = chs[j];
        chs[j] = temp;
    }

    public static void main(String[] args) {
        String[] ret = Permutation("aba");
        for(String s : ret){
            System.out.println(ret);
        }
    }
}
