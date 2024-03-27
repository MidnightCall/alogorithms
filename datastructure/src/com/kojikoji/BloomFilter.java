package com.kojikoji;

import org.junit.Test;

import java.util.BitSet;

/**
 * @ClassName BloomFilter
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2023/3/7 10:36
 * @Version
 */

public class BloomFilter {
    private static final int default_size = 2 << 24;
    private static final int[] SEEDS = new int[]{3, 13, 46, 71, 91, 134};
    private BitSet bits = new BitSet(default_size);
    private SimpleHash[] func = new SimpleHash[SEEDS.length];

    public BloomFilter(){
        for(int i = 0; i < func.length; ++i){
            func[i] = new SimpleHash(default_size, SEEDS[i]);
        }
    }

    public void add(String value){
        for(SimpleHash f : func){
            bits.set(f.hash(value), true);
        }
    }

    public boolean contains(String value){
        for(SimpleHash f : func){
            if(!bits.get(f.hash(value))){
                return false;
            }
        }
        return true;
    }

    public static class SimpleHash {

        private int cap;
        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        /**
         * 计算 hash 值
         */
        public int hash(Object value) {
            int h;
            return (value == null) ? 0 : Math.abs(seed * (cap - 1) & ((h = value.hashCode()) ^ (h >>> 16)));
        }

    }

    @Test
    public void test(){
        String value1 = "https://javaguide.cn/";
        String value2 = "https://github.com/Snailclimb";
        BloomFilter filter = new BloomFilter();
        System.out.println(filter.contains(value1));
        System.out.println(filter.contains(value2));
        filter.add(value1);
        filter.add(value2);
        System.out.println(filter.contains(value1));
        System.out.println(filter.contains(value2));
    }
}
