package 左程云.class03;

import static 左程云.class01.video_1.*;

/**
 * @description:
 * @author: Netter
 * @date: 2023-02-04 11:39
 */
public class Review {

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        // 生成堆结构
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left] < arr[left + 1]
                            ? left + 1 : left;
            largest = arr[index] > arr[largest] ? index : largest;

            if (largest == index) {
                break;
            }

            swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index-1) / 2]) {
            swap(arr, index, (index-1) / 2);
            index = (index - 1) / 2;
        }
    }


    public static void main(String[] args) {
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


        success = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            //radixSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                success = false;
                break;
            }
        }
        System.out.println(success ? "Nice" : "Fuck");
    }
}
