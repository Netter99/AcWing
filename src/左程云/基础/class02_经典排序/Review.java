package 左程云.基础.class02_经典排序;


import static 左程云.基础.class01.video_1.generateRandomArray;
import static 左程云.基础.class01.video_1.swap;

/**
 * @description:
 * @author: Netter
 * @date: 2023-02-04 10:51
 */
public class Review {

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length-1);
    }

    public static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            swap(arr, l + (int) ((r - l + 1) * Math.random()), r);
            int[] p = process(arr, l, r);
            quickSort(arr, l, p[0]-1);
            quickSort(arr, p[1]+1, r);
        }
    }

    // 返回值arr[r]的区间【左下标，右下标】
    private static int[] process(int[] arr, int l, int r) {
        int less = l-1;
        int more = r;
        while (l < more) {
            if (arr[l] < arr[r]) {
                swap(arr, ++less, l++);
            }else if (arr[l] > arr[r]) {
                swap(arr, --more, l);
            }else {
                l++;
            }
        }
        swap(arr, l, r);
        return new int[]{less+1, more};
    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort2(arr, 0, arr.length-1);
    }

    private static void quickSort2(int[] arr, int l, int r) {
        if (l < r) {
            swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
            int[] p = process2(arr, l, r);
            quickSort2(arr, l, p[0]-1);
            quickSort2(arr, p[1]+1, r);
        }
    }

    private static int[] process2(int[] arr, int l, int r) {
        int less = l - 1;
        int more = r;
        while (l < more) {
            if (arr[l] < arr[r]) {
                swap(arr, l++, ++less);
            }else if (arr[l] > arr[r]){
                swap(arr, l, --more);
            }else {
                l++;
            }
        }
        swap(arr, more, r);
        return new int[]{less+1, more};
    }

    public static void main(String[] args) {
        int[] arr = generateRandomArray(12, 8);
        int[] copy = arr.clone();
        quickSort(arr);
        quickSort2(copy);
        int a = 1;
    }

}
