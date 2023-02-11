package 左程云.class03;

import static 左程云.class01.video_1.*;

/**
 * @description:
 * @author: Netter
 * @date: 2023-01-30 13:39
 */
public class RadixSort {

    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxBits(arr));
    }

    private static int maxBits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = i > max ? i : max;
        }
        int  res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

    //arr[L...R]排序
    private static void radixSort(int[] arr, int L, int R, int digit) {
        final int radix = 10; // 基底
        int i = 0, j = 0;
        int[] bucket = new int[R - L + 1];
        for (int d = 1; d <= digit; d++) {
            int[] count = new int[radix];
            for (i = L; i <= R; i++) {
                j = getDigit(arr[i], d);
                count[j]++;
            }
            for(i = R; i >= L; i++) {
                j = getDigit(arr[i], d);
                bucket[count[j] - 1] = arr[i];
                count[j]--;
            }
            for (i = L, j = 0; i <= R; i++, j++) {
                arr[i] = bucket[j];
            }
        }
    }

    private static int getDigit(int x, int d) {
        //Math.pow(a, b)：返回以a为底，b次幂的数
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }

    public static void main(String[] args) {
        int testTime = 100;
        int maxSize = 500;
        int maxValue = 500;
        boolean success = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            radixSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                success = false;
                break;
            }
        }
        System.out.println(success ? "Nice" : "Fuck");
    }

}
