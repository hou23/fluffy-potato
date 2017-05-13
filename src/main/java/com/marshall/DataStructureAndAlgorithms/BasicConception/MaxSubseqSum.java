package com.marshall.DataStructureAndAlgorithms.BasicConception;

/**
 * Created by marshall.houz on 2017/4/19.
 *
 * 求最大子列和
 */
public class MaxSubseqSum {
    public static void main(String[] args) {

    }

    private int maxSubseqSum1(int a[], int n) {
        int thisSum = 0;
        int maxSum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                thisSum = 0;
                for (int k = i; k < j; k++) {
                    thisSum += a[k];
                }
                if(thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }

    private int maxSubseqSum2(int a[], int n) {
        int thisSum = 0;
        int maxSum = 0;
        for (int i = 0; i < n; i++) {
            thisSum = 0;
            for (int j = i; j < n; j++) {
                thisSum += a[j];
            }
            if (thisSum > maxSum) {
                maxSum = thisSum;
            }
        }
        return maxSum;
    }

    //分而治之
    private int maxSubseqSum3(int a[], int n) {
        return 0;
    }

    //在线处理
    private int maxSubseqSum4(int a[], int n) {
        int thisSum = 0;
        int maxSum = 0;
        for (int i = 0; i < n; i++) {
            thisSum += a[i];
            if (thisSum > maxSum) {
                maxSum = thisSum;
            } else if (thisSum < 0) {
                thisSum = 0;
            }
        }
        return maxSum;
    }
}
