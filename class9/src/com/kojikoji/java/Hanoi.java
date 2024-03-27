package com.kojikoji.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Hanoi
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2023/1/1 12:05
 * @Version
 */

public class Hanoi {
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        if(!A.isEmpty()){
            func(A.size(), A, B, C);
        }
    }

    public void func(int rest, List<Integer> from, List<Integer> to, List<Integer> other){
        if(rest == 1){
            to.add(from.get(from.size() - 1));
            from.remove(from.size() - 1);
        }else{
            func(rest - 1, from, other, to);
            func(1, from, to, other);
            func(rest - 1, other, to, from);
        }
    }

    @Test
    public void test(){
        ArrayList<Integer> from = new ArrayList<>();
        ArrayList<Integer> to = new ArrayList<>();
        ArrayList<Integer> other = new ArrayList<>();
        from.add(0);

        hanota(from, to, other);
    }
}
