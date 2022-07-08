import org.junit.Test;

import java.util.*;
import java.io.*;

public class part_1 {
    static int[] tmp = new int[100000];
    static int[][] temp = new int[1010][1010];

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
        /** 复习：犯了个错误  --- sl = j-i+1;
         *  此处应该是j-l+1，因为退出的时候i=j，如果按照上式，则sl===0
         */
        int sl = j-l+1;
        if (k<=sl) return quick_sork_k(arr,l,j,k);
        return quick_sork_k(arr,j+1,r,k-sl);
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
    @Test
    public void answer4() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] arr = new int[n];
        String[] data = reader.readLine().split(" ");
        for (int i=0;i<data.length;i++)
            arr[i] = Integer.parseInt(data[i]);

        System.out.println(merge_sort_use(arr,0,n-1));
    }

    /**
     * 二分查找的本质：根据条件，将数组分成两部分 --- 满足条件和不满足条件的边界区间
     *      其核心在于寻找两者的边界
     *
     *  写法分两种情况：①区间[l,r]分为 [l, mid-1]、[mid, r]②区间[l,r]分为 [l, mid]、[mid+1, r]
     *     情况①：
     *      1.更新区间的情况为l=mid (r=mid-1)， 则 mid = l+r+1 >> 1
     *      2.更新区间的情况为r=mid (l=mid+1)， 则 mid = l+r >> 1
     */
    /**二分查找
     * 思想：
     *  1、确定分界点X=（l+r）/2
     *  2、定义check函数，将区间一分为二, 再根据区间的变化情况，决定是否改变mid的取值方式
     *  3、归并：将左右两段合二为一（需要一个额外的存储空间来保存单词的比较结果）   => 两个指针进行移动比较
     */
    static int[] arr = new int[100010];
    public static void binary_search() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] data = reader.readLine().split(" ");
        int n = Integer.parseInt(data[0]), m = Integer.parseInt(data[1]);
        data = reader.readLine().split(" ");
        for (int i=0;i<n;i++)
            arr[i] = Integer.parseInt(data[i]);
        while (m-->0){
            int l=0,r=n-1;
            int x = Integer.parseInt(reader.readLine());

            //先找左边界
            while (l<r){
                int mid = l+r>>1;
                //满足条件的情况
                if (arr[mid]>=x) r = mid;
                else l = mid+1;
            }
            if (arr[l]!=x){
                System.out.println("-1 -1");
            }else {
                System.out.print(l + " ");
                l = 0;
                r = n-1;
                while (l<r){
                    int mid = l+r+1>>1;
                    if (arr[mid]<=x) l = mid;
                    else r = mid-1;
                }
                System.out.println(l);
            }
        }
    }
    @Test
    public void answer5() throws IOException {
        binary_search();
    }

    /**
     * 数的三次方根
     * 因为最终目的是找到一个数，所以判断的结束条件为 r≈l
     */
    public static void squared_3() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        double n = Double.parseDouble(reader.readLine());
        double l = -10000, r = 10000;
        while (r-l > 1e-8){
            double mid = (l+r)/2;
            if (mid*mid*mid<=n) l = mid;
            else r = mid;
        }
        System.out.printf("%.6f",l);
    }
    @Test
    public void answer6() throws IOException{
        Scanner sc = new Scanner(System.in);
        String d1 = sc.nextLine();
        String d2 = sc.nextLine();
        List<Integer> l1 = new ArrayList<>(d1.length());
        List<Integer> l2 = new ArrayList<>(d2.length());

        for (int i=d1.length()-1;i>=0;i--) l1.add(d1.charAt(i) - '0');
        for (int i=d2.length()-1;i>=0;i--) l2.add(d2.charAt(i) - '0');

        List<Integer> result = add(l1, l2);

        for (int i = result.size()-1;i>=0;i--)
            System.out.print(result.get(i));
    }

    /**
     * 高精度加法：
     *      两个参数都以倒序存放数据
     */
    public static List<Integer> add(List<Integer> l1, List<Integer> l2){
        int t = 0;
        List<Integer> result = new ArrayList<>();

        for (int i=0;i<l1.size()||i<l2.size();i++){
            if (i<l1.size()) t += l1.get(i);
            if (i<l2.size()) t += l2.get(i);
            result.add(t%10);
            t /= 10;
        }
        if (t!=0)
            result.add(1);
        return result;
    }
    @Test
    public void answer7(){
        Scanner sc = new Scanner(System.in);
        String d1 = sc.nextLine();
        String d2 = sc.nextLine();

        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        for (int i=d1.length()-1; i>=0;i--) l1.add(d1.charAt(i) - '0');
        for (int i=d2.length()-1; i>=0;i--) l2.add(d2.charAt(i) - '0');

        if (compare(d1, d2)){
            List<Integer> sub = sub(l1, l2);
            for (int i = sub.size()-1;i>=0;i--)
                System.out.print(sub.get(i));
        } else {
            List<Integer> sub = sub(l2, l1);
            System.out.print("-");
            for (int i = sub.size()-1;i>=0;i--)
                System.out.print(sub.get(i));
        }
    }

    /**
     * 高精度减法：
     *      ①判断A、B的值大小，保证A>B
     *      ②A-B
     */
    public static boolean compare(String a, String b){
        if (a.length()!=b.length()) return a.length()>b.length();
        for (int i=0;i<a.length();i++){
            if (a.charAt(i)!=b.charAt(i)) return a.charAt(i) > b.charAt(i);
        }
        return true;
    }
    public static List<Integer> sub(List<Integer> l1, List<Integer> l2){
        List<Integer> res = new ArrayList<>();
        //t：进位
        for (int i =0, t=0;i<l1.size();i++){
            t = l1.get(i) - t;
            if (i<l2.size()) t -= l2.get(i);
            res.add(t);
            if (t<0) t = 1;
            else t = 0;
        }
        //去前置0
        int i =res.size()-1;
        while (res.size()>1 && res.get(i)==0) res.remove(i--);
        return res;
    }
    @Test
    public void answer8(){
        Scanner sc = new Scanner(System.in);
        String temp = sc.nextLine();
        int d2 = Integer.parseInt(sc.nextLine());
        List<Integer> d1 = new ArrayList<>();
        for (int i = temp.length()-1;i>=0;i--)  d1.add(temp.charAt(i) - '0');

        List<Integer> res = mul(d1, d2);
        for (int i = res.size()-1; i >= 0; i--) System.out.print(res.get(i));
    }

    /**
     * 高精度乘法
     */
    public static List<Integer> mul(List<Integer> d1, int d2){

        int t = 0;
        List<Integer> res = new ArrayList<>();
        for (int i=0;i<d1.size() || t>0;i++){
            if (i<d1.size()) t += d1.get(i)*d2;
            res.add(t%10);
            t /= 10;
        }

        /**注意while和for的区别：
         * while：不满足条件则结束循环
         * for：条件判断到结束
         */
        int i=res.size()-1;
        while (res.size()>1 && res.get(i) == 0)
            res.remove(i--);

        return res;
    }
    @Test
    public void answer9(){
        Scanner sc = new Scanner(System.in);
        String temp = sc.nextLine();
        int d2 = Integer.parseInt(sc.nextLine());
        List<Integer> d1 = new ArrayList<>();
        for (int i = temp.length()-1;i>=0;i--)  d1.add(temp.charAt(i) - '0');

        List<Integer> res = mul(d1, d2);
        for (int i = res.size()-1; i >= 0; i--) System.out.print(res.get(i));
    }

    /**
     * 高精度除法
     * 注意：为了让加减乘除相统一，依旧按照逆序存储数据（正常思维是从高到低算除法）
     */
    public static List<Integer> div(List<Integer> a, int b){
        int r = 0;//余数
        List<Integer> res = new ArrayList<>();
        for (int i=a.size()-1;i>=0;i--){
            r = a.get(i) + r*10;
            res.add(r/b);
            r %= b;
        }
        res.add(r);
        return res;
    }
    @Test
    public void ansewer10(){
        Scanner sc = new Scanner(System.in);
        String temp = sc.nextLine();
        int d2 = Integer.parseInt(sc.nextLine());
        List<Integer> d1 = new ArrayList<>();
        for (int i = temp.length()-1;i>=0;i--)  d1.add(temp.charAt(i) - '0');

        List<Integer> res = div(d1, d2);
        for (int i = 0; i < res.size() ; i++){
            if (i == res.size()-1){
                System.out.println();
                System.out.println(res.get(i));
            }
            else
                System.out.print(res.get(i));
        }
    }

    /**前缀和：
     * 主要思想：假设输入有N个数，用一个N+1的数组来保存从0~N的累加合   ==> Si = a1 + a2 + ... + ai
     *      则具体计算[l, r]范围的数字时 = S[r]-S[l-1];
     * 为什么是l-1呢？因为S[0]固定为0，而且计算l~r是包括l的！！！
     */
    /**一维前缀和
     */
    public static void prefix_add(){
        Scanner sc = new Scanner(System.in);

        int n=sc.nextInt(), m= sc.nextInt();
        int[] arr = new int[n+1];

        for (int i = 1; i <=n; i++)
            arr[i] = sc.nextInt();

        for (int i = 1; i <=n; i++)
            tmp[i] = tmp[i-1]+arr[i];

        while (m-->0){
            int l=sc.nextInt(), r=sc.nextInt();
            System.out.println(tmp[r]-tmp[l-1]);
        }
        sc.close();
    }


    /**二维前缀和
     */
    public static void prefix_add_2D(){
        Scanner sc = new Scanner(System.in);

        int n=sc.nextInt(), m=sc.nextInt(), q=sc.nextInt();
        int[][] arr = new int[n+1][m+1];

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                arr[i][j] = sc.nextInt();

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                temp[i][j] = temp[i-1][j] + temp[i][j-1] + arr[i][j] - temp[i-1][j-1];

        while (q-->0){
            int x1=sc.nextInt(),y1=sc.nextInt(),x2=sc.nextInt(),y2=sc.nextInt();
            System.out.println(temp[x2][y2] - temp[x1-1][y2] - temp[x2][y1-1] + temp[x1-1][y1-1]);
        }
        sc.close();
    }

    /**差分 --- 原数组的名称，即
     * 存在a1、a2、...，构造b1、b2、...
     * 使得ai = b1 + b2 + ... + bi
     * 其中b1 = a1、b2 = a2 - a1、...、bn = an - an-1
     * b为a的差分、a为b的前缀和
     */
    static int[] a = new int[100010];
    static int[] b = new int[100010];

    public static void difference(){
        Scanner sc = new Scanner(System.in);

        int n=sc.nextInt(), q=sc.nextInt();

        for (int i = 1; i <= n; i++)
            a[i] = sc.nextInt();
        /**错误：a.length  -->  整个数组的大小，而不是实际存储大小
         */
        //求出原始b数组的值
        for (int i = 1; i <= n; i++)
            insert(a[i], i, i);

        while (q-->0){
            int l=sc.nextInt(), r=sc.nextInt(), c=sc.nextInt();
            insert(c, l, r);
        }

        //求前缀和
        for (int i = 1; i <= n; i++)
            b[i] += b[i-1];

        for (int i = 1; i <= n; i++)
            System.out.print(b[i] + " ");
        sc.close();
    }
    public static void insert(int c, int l, int r){
        b[l] += c;
        b[r+1] -= c;
    }

    static int[][] c = new int[1010][1010];
    static int[][] d = new int[1010][1010];
    public static void difference_2d(){
        Scanner sc = new Scanner(System.in);

        int n=sc.nextInt(), m=sc.nextInt(), q=sc.nextInt();

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                c[i][j] = sc.nextInt();

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                insert_2d(i,j,i,j,c[i][j]);

        while (q-->0){
            int x1=sc.nextInt(),y1=sc.nextInt(),x2=sc.nextInt(),y2=sc.nextInt(),c=sc.nextInt();
            insert_2d(x1,y1,x2,y2,c);
        }

        //求前缀和
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                d[i][j] += d[i-1][j] + d[i][j-1] - d[i-1][j-1];

        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= m; j++)
                System.out.print(d[i][j] + " ");
            System.out.println();
        }
    }

    public static void insert_2d(int x1, int y1, int x2, int y2, int c){
        d[x1][y1] += c;
        d[x2+1][y1] -= c;
        d[x1][y2+1] -= c;
        d[x2+1][y2+1] += c;
    }


}
