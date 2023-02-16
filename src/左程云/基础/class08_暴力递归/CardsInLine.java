package 左程云.基础.class08_暴力递归;

/**
 * @description:
 * @author: Netter
 * @date: 2023-02-11 11:49
 */
public class CardsInLine {

    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(f(arr, 0, arr.length-1), s(arr, 0, arr.length-1));
    }

    public static int f(int[] arr, int i, int j) {
        if (i == j) {
            return arr[i];
        }
        return Math.max(arr[i] + s(arr, i+1, j), arr[j] + s(arr, i, j-1));
    }

    public static int s(int[] arr, int i, int j) {
        if (i == j) {
            return 0;
        }
        return Math.min(f(arr, i+1, j), f(arr, i, j-1));
    }

}