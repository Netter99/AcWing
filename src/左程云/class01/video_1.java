package 左程云.class01;

import java.util.Arrays;

/**
 * @description:
 * @author: Netter
 * @date: 2023-01-28 10:21
 */
public class video_1 {

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void print_arr(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    //for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    public static int[] copyArray(int[] arr) {
        int len = arr.length;
        int[] temp = new int[len];
        for (int i = 0; i < len; i++) {
            temp[i] = arr[i];
        }
        return temp;
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        boolean success = true;
        for (int i = 0; i < arr1.length; i++) {
            if(arr1[i] != arr2[i]) {
                success = false;
                break;
            }
        }
        return success;
    }

    //对数器：用于生成随机长度的随机数据的数组
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        //Math.random() -> [0,1) 所有的小数，等概率返回一个
        //Math.random() * N -> [0,N) 所有的小数，等概率返回一个
        //(int)(Math.random() * N) -> [0,N-1] 所有的整数，等概率返回一个

        int[] arr = new int[(int) ((maxSize + 1) * Math.random())]; //长度随机
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    /**
     * 位操作
     */
    //不用额外变量交换两个整数的值
    public static void change_num_with_no_variable(int a, int b) {
        System.out.println(a + "---" + b);
        a ^= b;
        b ^= a;
        a ^= b;
        System.out.println(a + "---" + b);
    }

    /*
    注意：位操作必须用括号包起来！
     */
    //在其他数都出现偶数次的数组中找到出现奇数次的数
    public int[] singleNumbers(int[] nums) {
        if(nums.length == 2)
            return nums;
        int[] rst = new int[2];
        int eor = 0;
        for(int i=0; i<nums.length; i++)
            eor ^= nums[i];
        int sign = eor & (~eor + 1);
        int eor2 = 0;
        for(int i=0; i<nums.length; i++) {
            /*
            注意：位操作必须用括号包起来！
             */
            if((nums[i] & sign) != 0)
                eor2 ^= nums[i];
        }
        rst[0] = eor2;
        rst[1] = eor ^ eor2;
        return rst;
    }

    /**
     * 排序
     */
    //选择排序
    public static void select_sort(int[] nums) {
        if(nums == null || nums.length <=1) return;
        for (int i = 0; i < nums.length - 1; i++) {
            int minIdx = i;
            for (int j = i+1; j < nums.length; j++) {
                if(nums[minIdx] > nums[j])
                    minIdx = j;
            }
            swap(nums, i, minIdx);
            print_arr(nums);
        }
    }

    //冒泡排序
    public static void bubble_sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if(nums[j] > nums[j+1]) {
                    swap(nums, j, j+ 1);
                    print_arr(nums);
                }

            }
        }
    }

    //插入排序
    public static void insert_sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j > 0 && nums[j-1] > nums[j]; j--) {
                swap(nums, j-1, j);
                //print_arr(nums);
            }
        }
    }

    //归并排序
    public static int merge_sort(int[] nums, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return merge_sort(nums, l, mid) + merge_sort(nums, mid+1, r) + merge(nums, l, mid, r);
    }

    private static int merge(int[] nums, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l, p2 = mid + 1;
        int res = 0;
        while (p1 <= mid && p2 <= r) {
            res += nums[p1] < nums[p2] ? (r - p2 + 1) * nums[p1] : 0;
            help[i++] = nums[p1] < nums[p2] ? nums[p1++] : nums[p2++];
        }
        while (p1 <= mid) {
            help[i++] = nums[p1++];
        }
        while (p2 <= r) {
            help[i++] = nums[p2++];
        }
        for (i = 0; i < help.length; i++) {
            nums[l + i] = help[i];
        }
        return res;
    }

    //快速排序
    public static void quick_sort(int[] nums) {
        if (nums == null || nums.length < 2)
            return;
        quick_sort(nums, 0, nums.length-1);
    }

    private static void quick_sort(int[] nums, int l, int r) {
        if(l < r) {
            swap(nums, l + (int) (Math.random() * (r - l + 1)), r);

        }
    }

    public static void sort(int[] nums, int target) {
        int l = -1, r = nums.length;
        for (int i = 0; i < r;) {
            if(nums[i] < target) {
                swap(nums, l+1, i++);
                l++;
            }else if (nums[i] > target) {
                swap(nums, r-1, i);
                r--;
            }else {
                i++;
            }
        }
        l = 1;
    }

    /**
     * 查找
     */
    //二分查找
    public static int bianry_search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        int mid = -1;
        while (l < r) {
            mid = l + (r - l>>1);
            if(nums[mid] >= target) {
                r = mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            }
        }
        if (nums[l] == target)
            return l;
        else
            return -1;
    }

    public static int getMax(int[] nums) {
        return getMax_process(nums, 0, nums.length-1);
    }

    private static int getMax_process(int[] nums, int l, int r) {
        if (l == r) return nums[l];
        int mid = l + (r - l >> 1);
        int leftMax = getMax_process(nums, l, mid);
        int rightMax = getMax_process(nums, mid+1, r);
        return Math.max(leftMax, rightMax);
    }




    public static void main(String[] args) {
        //change_num_with_no_variable(2, 5);

        //for (int i : singleNumbers(new int[]{4, 5, 4, 6})) {
        //    System.out.println(i);
        //}

        //select_sort(new int[]{2, 3, 1, 5, 4});
        //bubble_sort(new int[]{2, 3, 1, 5, 4});
        //insert_sort(new int[]{2, 3, 1, 5, 4});
        //System.out.println(bianry_search(new int[]{1, 1, 2, 3, 3, 5}, 1));

        /**
         * 使用对数器进行代码测试
         */
        int testTime = 100;
        int maxSize = 20;
        int maxValue = 500;
        boolean success = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            merge_sort(arr1, 0, arr1.length-1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                success = false;
                break;
            }
        }
        System.out.println(success ? "Nice" : "Fuck");

        //System.out.println(getMax(new int[]{1, 1, 2, 3, 3, 5}));
        int[] arr1 = generateRandomArray(maxSize, maxValue);
        sort(arr1, 5);

    }

}
