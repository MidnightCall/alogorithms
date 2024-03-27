package com.kojikoji.java;

/**
 * @ClassName NQueens
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2022/12/31 20:40
 * @Version
 */

public class NQueens {
    public static int num1(int n){
        if(n < 1){
            return 0;
        }
        int[] record = new int[n];
        return process(0, record, n);
    }

    public static int process(int i, int[] record, int n){
        if(i == n){
            return 1;
        }
        int res = 0;
        for(int j = 0; j < n; ++j){
            if(isValid(record, i, j)){
                record[i] = j;
                res += process(i+1, record, n);
            }
        }
        return res;
    }

    public static boolean isValid(int[] record, int i, int j){
        for(int k = 0; k < i; ++k){
            if(j == record[k] || Math.abs(record[k] - j) == Math.abs(k - i)){
                return false;
            }
        }
        return true;
    }

    public int num2(int n){
        if(n < 1 || n > 32){
            return 0;
        }
        int upperLimit = n == 32 ? -1 : (1 << n) - 1;

        return process2(upperLimit, 0, 0, 0);
    }

    public int process2(int upperLimit, int colLimit, int leftDiaLimit, int rightDiaLimit){
        if(upperLimit == colLimit){
            return 1;
        }
        int pos = upperLimit & (~(colLimit | leftDiaLimit | rightDiaLimit));
        int res = 0;
        int mostRightOne = 0;
        while (pos != 0){
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process2(upperLimit, colLimit & mostRightOne,
                    (leftDiaLimit & mostRightOne) << 1,
                    (rightDiaLimit & mostRightOne) >> 1
                    );
        }
        return res;
    }
}
