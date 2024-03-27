package com.kojikoji.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName AllSubsquences
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2023/1/1 14:27
 * @Version
 */

public class AllSubsquences {
    public static void printAllSubsquence(String str) {
        char[] chs = str.toCharArray();
        list(chs, 0);
    }

    public static void list(char[] chs, int i){
        if(i == chs.length){
            System.out.println(String.valueOf(chs));
            return;
        }

        list(chs, i + 1);
        char temp = chs[i];
        chs[i] = 0;
        list(chs, i + 1);
        chs[i] = temp;
    }

    public static void function(String str) {
        char[] chs = str.toCharArray();
        process(chs, 0, new ArrayList<Character>());
    }

    public static void process(char[] chs, int i, List<Character> res) {
        if(i == chs.length) {
            printList(res);
        }
        List<Character> resKeep = copyList(res);
        resKeep.add(chs[i]);
        process(chs, i+1, resKeep);
        List<Character> resNoInclude = copyList(res);
        process(chs, i+1, resNoInclude);
    }

    public static void printList(List<Character> res) {
        // ...;
    }

    public static List<Character> copyList(List<Character> list){
        return null;
    }


    public static void main(String[] args) {
        String test = "aba";
        printAllSubsquence(test);
    }
}
