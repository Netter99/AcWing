import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

public class Main {
    public static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static int binarysearch(int[] a, int target) {
        int l = 0, r = a.length - 1;
        int mid;
        while (l <= r) {
            mid = l + r >>>1;
            if (a[mid] == target) return mid;
            else if (a[mid] < target) l = mid + 1;
            else r = mid - 1;
        }
        return -1;
    }

    //冒泡排序
    public static void bubble_source(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1; j++) {
                System.out.println("比较次数" + (j+1));
                if (a[j]>a[j+1]) {
                    swap(a, j, j+1);
                }
            }
            System.out.println("第" + i + "轮冒泡" + Arrays.toString(a));
        }
    }

    public static void bubble1(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < a.length - 1; j++) {
                System.out.println("比较次数" + (j+1));
                if (a[j]>a[j+1]) {
                    swap(a, j, j+1);
                    swapped = true;
                }
            }
            System.out.println("第" + i + "轮冒泡" + Arrays.toString(a));
            if (!swapped) break;
        }
    }

    public static void bubble2(int[] a) {
        int n = a.length - 1;
        int i = 1;
        while (true){
            int last = 0;
            for (int j = 0; j < n; j++) {
                System.out.println("比较次数" + (j+1));
                if (a[j]>a[j+1]) {
                    swap(a, j, j+1);
                    last = j;
                }
            }
            n = last;
            System.out.println("第" + (i++) + "轮冒泡" + Arrays.toString(a));
            if (n == 0) break;
        }
    }

    //选择排序
    public static void selection(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            //i 代表每轮选择最小元素要交换到的目标索引
            int s = i; //代表最小元素的索引
            for (int j = s + 1; j < a.length; j++) {
                if(a[s] > a[j]) s = j;
            }
            if (s != i) swap(a, s, i);
            System.out.println(Arrays.toString(a));
        }
    }

    //插入排序
    public static void insert(int[] a) {
        //i 代表待插入元素的索引
        for (int i = 1; i < a.length; i++) {
            int t = a[i]; // 代表待插入的元素值
            int j = i - 1; // 代表已排序区域的元素索引
            while (j >= 0) {
                if(t < a[j]) {
                    a[j+1] = a[j];
                } else {
                    break;
                }
                j--;
            }
            a[j+1] = t;
            System.out.println(Arrays.toString(a));
        }
    }

    //快速排序
    public static void quicksort(int[] a, int l, int r) {
        if(l >= r) return;
        int p = partition(a, l, r);
        quicksort(a, l, p-1);
        quicksort(a, p+1, r);
    }

    //单边
    private static int partition(int[] a, int l, int r) {
        int pv = a[r];
        int i = l;
        for (int j = l; j < r; j++) {
            if(a[j] < pv) {
                if (i != j)
                    swap(a, i, j);
                System.out.println(Arrays.toString(a));
                i++;
            }
        }
        if(r != i)
            swap(a, r, i);
        System.out.println();
        System.out.println(Arrays.toString(a));
        //返回值代表了基准点元素所在的正确索引，用它确定下一轮分区的边界
        return i;
    }

    //双边
    private static int partition2(int[] a, int l, int r) {
        int pv = a[l];
        int i = l, j = r;
        while(i < j) {
            //j 从右找小的
            while (i < j && a[j] > pv) {
                j--;
            }
            while (i < j && a[i] <= pv) {
                i++;
            }
            swap(a, i, j);
        }
        swap(a, l, i);
        System.out.println(Arrays.toString(a) + " j=" + j);
        return i;
    }

    public static void main(String[] args) {
        int[] a = {1, 5, 8, 11, 19 ,22, 31, 35, 40, 45, 48, 49, 50};
        int target = 48;
        System.out.println(binarysearch(a, target));

        int[] b = {5, 2, 7, 4, 1, 3, 8, 9};
        //bubble2(b);

        int[] c = {5, 3, 7, 2, 1, 9, 8, 4};
        //selection(c);

        int[] d = {9, 3, 7, 2, 5, 8, 1, 4};
        //insert(d);

        int[] e = {5, 3, 7, 2, 9, 8, 1, 4};
        //quicksort(e, 0, d.length - 1);

        Runnable job = new Runnable() {
            @Override
            public void run() {
                while (true)
                    System.out.println("123");
            }
        };

        Runnable job2 = new Runnable() {
            @Override
            public void run() {
                while (true)
                    System.out.println("456");
            }
        };

        Thread thread = new Thread(job);
        Thread thread2 = new Thread(job2);
        thread.start();
        thread2.start();

    }
}
