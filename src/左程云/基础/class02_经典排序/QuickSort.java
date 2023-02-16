package 左程云.基础.class02_经典排序;

import static 左程云.基础.class01.video_1.generateRandomArray;
import static 左程云.基础.class01.video_1.swap;

/**
 * @description:
 * @author: Netter
 * @date: 2023-01-28 16:57
 */
public class QuickSort {

    public static void quick_sort(int[] nums) {
        if (nums == null || nums.length < 2)
            return;
        quick_sort(nums, 0, nums.length-1);
    }

    private static void quick_sort(int[] nums, int l, int r) {
        if(l < r) {
            swap(nums, l + (int) (Math.random() * (r - l + 1)), r);
            int[] p = partition(nums, l, r);
            quick_sort(nums, l, p[0]-1);
            quick_sort(nums, p[1]+1, r);
        }
    }

    private static int[] partition(int[] nums, int l, int r) {
        int less = l-1; //左边界
        int more = r; //右边界，这里为r意味着固定最右边的值为标杆，即默认这个位置不可访问
        while (l < more) {
            if(nums[l] < nums[r]) {
                swap(nums, ++less, l++);
            }else if (nums[l] > nums[r]) {
                swap(nums, --more, l);
            }else {
                l++;
            }
        }
        swap(nums, more, r);
        return new int[]{less+1, more};
    }

    public static void main(String[] args) {
        int[] arr = generateRandomArray(20, 5);
        quick_sort(arr);
        int a = 1;
    }
}
