package com.kojikoji.java;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @ClassName BestArrange
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2022/12/29 19:12
 * @Version
 */

public class BestArrange {
    public class Program{
        int start;
        int end;
        public Program(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    public class MyComparator implements Comparator<Program>{
        public int compare(Program a, Program b){
            return a.end - b.end;
        }
    }

    public int bestArrage(Program[] programs, int start){
        Arrays.sort(programs);
        int result = 0;
        int time = start;

        for(int i = 0; i < programs.length; ++i){
            if(time < programs[i].start){
                ++result;
                time = programs[i].end;
            }
        }

        return result;
    }

}
