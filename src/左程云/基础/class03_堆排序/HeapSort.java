package 左程云.基础.class03_堆排序;

import java.util.Arrays;

import static 左程云.基础.class01.video_1.*;

/**
 * @description:
 * @author: Netter
 * @date: 2023-01-29 20:37
 */
public class HeapSort {

    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        //1.每次读入一个数
        for (int i = 0; i < arr.length; i++) {  //O(N)
            heapInsert(arr, i); //O(logN)
        }
        //2.一次性读入所有数
        for (int i = arr.length-1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {  //O(N)
            heapify(arr, 0, heapSize);  //O(logN)
            swap(arr, 0, --heapSize);   //O(1)
        }
    }

    private static void heapInsert(int[] arr, int index) {
        /**注意：这里不能用位操作
         * (0 - 1) / 2 = 0
         * (0 - 1) >> 1 = -1
         */

        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) { // 有右孩子的前提是有左孩子
            //两个孩子中，谁的值大，把下标给largest
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left]
                            ? left + 1 : left;

            //父和较大的孩子之间，谁的值大，把下标给largest
            largest = arr[index] > arr[largest] ? index : largest;

            if (index == largest) {
                break;
            }

            swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void main(String[] args) {
        System.out.println((0-1)>>1);
        int testTime = 100;
        int maxSize = 500;
        int maxValue = 500;
        boolean success = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            heapSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                success = false;
                break;
            }
        }
        System.out.println(success ? "Nice" : "Fuck");
    }

}
