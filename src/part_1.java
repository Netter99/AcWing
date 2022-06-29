import org.junit.Test;

import java.util.*;
import java.io.*;

public class part_1 {

    public static void main(String []args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] arr = new int[n];
        String[] data = reader.readLine().split(" ");
        for (int i=0;i<data.length;i++)
            arr[i] = Integer.parseInt(data[i]);

        System.out.println(merge_sort_use(arr,0,n-1));
    }

    //////////////////////////////////Function//////////////////////////////////

    /*
    快排和归排都是才用分治思想
    稳定：归排
    不稳定：快排
     */

    /**
     * 快速排序：先排序，后递归
     * 时间复杂度：nlogn
     */
    /**快速选择排序
     * 思想：
     *  1、确定分界点X
     *  2、根据分界点的值，将arr分成两半，左边的数≤X，右边的数≥X
     *      具体做法：
     *          指针移动的时候保证i的指<X，即停下来的时候，当前值≥X
     *          同理，j停下来的时候，当前值≤X
     *          两数互换（这样就保证结论成立）
     *  3、递归处理左右两段
     */
    public static void quick_sort(int[] arr, int l, int r){
        // 退出条件：只有一个数或没有数时，直接退出
        if (l>=r) return;
        // 注意此处边界：因为每次是先移动后比较，所以分别-1，+1
        int x = arr[l], i=l-1, j = r+1;
        while(i<j){
            do{
                i++;
            }while(arr[i]<x);
            do {
                j--;
            }while(x<arr[j]);
            if (i<j){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        /**
         * 边界问题， 当取x=arr[l]时，递归调用不能是
         *                      quick_sort(arr, l, i-1);
         *                      quick_sort(arr, i, r);
         * 举例：1、2，x=0
         *   退出时，i=0 => 第二个递归 为：quick_sort(arr, 0, 1);进入死循环
         */
        quick_sort(arr, l, j);
        quick_sort(arr, j+1, r);
    }

    /**
     * 快速选择排序的使用，注意退出条件的选择；题目：第k个数
     * 思想：进行排序后，根据指针计算左半区间的个数
     *      情况一：左边个数≤K，则答案在左边区间，递归调用左区间
     *      情况二：左边个数>K，则答案在右边区间，递归调用右区间（k-Num_left）
     * 最终的结果是：答案必然在左半区间，所以退出条件值是 “==”
     */
    public static int quick_sork_k(int[] arr, int l, int r, int k){
        if (l==r) return arr[l];
        int x = arr[l], i = l-1, j=r+1;
        while (i<j){
            while (arr[++i]<x);
            while (arr[--j]>x);
            if (i<j){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int sl = j-l+1;
        if (k<=sl) return quick_sork_k(arr,l,j,k);
        return quick_sork_k(arr,j+1,r,k-sl);
    }


    /**
     * 归并排序：先递归，后排序
     * 时间复杂度：nlogn
     */
    /**归并排序
     * 思想：
     *  1、确定分界点X=（l+r）/2，将arr分为 [L,mid]、[mid+1,R]
     *  2、递归排序[L,mid]、[mid+1,R]
     *  3、归并：将左右两段合二为一（需要一个额外的存储空间来保存单词的比较结果）   => 两个指针进行移动比较
     */
    static int[] tmp = new int[100000];
    public static void merge_sort(int[] arr, int l, int r){
        if (l>=r) return;
        int mid = l+r>>1;
        merge_sort(arr, l, mid); merge_sort(arr, mid+1, r);

        int k=0,i=l,j=mid+1;
        while (i<=mid && j<=r){
            if (arr[i]<=arr[j])
                tmp[k++] = arr[i++];
            else
                tmp[k++] = arr[j++];
        }
        while (i<=mid)
            tmp[k++] = arr[i++];
        while (j<=r)
            tmp[k++] = arr[j++];

        for (i=l, j=0;i<=r;i++,j++){
            arr[i] = tmp[j];
        }
    }

    /**
     * 归并排序的使用，注意退出条件的选择；题目：逆序对的数量
     * 逆序对的定义：前面的数比后面的数大
     * 思想：
     *  当左右排序后，arr[i] > arr[j]时，arr[i-mid]的数都大于arr[j]，
     *  所以，逆序对的个数：mid-i+1
     */
    public static long merge_sort_use(int[] arr, int l, int r){
        if (l>=r) return 0;
        int mid = l+r >> 1;
        long res = merge_sort_use(arr, l, mid) + merge_sort_use(arr, mid+1, r);
        int i=l, j=mid+1,k=0;
        while (i<=mid && j<=r){
            if (arr[i]<=arr[j])
                tmp[k++] = arr[i++];
            else {
                tmp[k++] = arr[j++];
                res += mid-i+1;
            }
        }
        while (i<=mid)  tmp[k++] = arr[i++];
        while (j<=r)  tmp[k++] = arr[j++];

        for (i=l,j=0;i<=r;i++,j++)
            arr[i] = tmp[j];
        return res;
    }

    //////////////////////////////////Test//////////////////////////////////
    @Test
    public void answer1() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] arr = new int[n];
        String[] strs = reader.readLine().split(" ");
        for(int i=0;i<strs.length;i++){
            arr[i] = Integer.parseInt(strs[i]);
        }

        quick_sort(arr, 0, n-1);

        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i] + " ");
        }
        reader.close();
    }

    @Test
    public void answer2() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = reader.readLine().split(" ");
        int n = Integer.parseInt(strs[0]);
        int k = Integer.parseInt(strs[1]);

        int[] arr = new int[n];
        strs = reader.readLine().split(" ");
        for(int i=0;i<strs.length;i++){
            arr[i] = Integer.parseInt(strs[i]);
        }

        int result = quick_sork_k(arr, 0, n - 1, k);

        System.out.println(result);
        reader.close();
    }

    @Test
    public void answer3() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        int[] arr = new int[n];
        String[] strs = reader.readLine().split(" ");
        for(int i=0;i<strs.length;i++){
            arr[i] = Integer.parseInt(strs[i]);
        }

        merge_sort(arr, 0, n - 1);
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i] + " ");
        }
        reader.close();
    }

}
