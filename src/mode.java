import java.io.*;

public class Main {
    static int[] tmp = new int[100000];
    public static void main(String []args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] arr = new int[n];
        String[] data = reader.readLine().split(" ");
        for (int i=0;i<data.length;i++)
            arr[i] = Integer.parseInt(data[i]);

        System.out.println(merge_sort_use(arr,0,n-1));
    }

    public static long merge_sort_use(int[] arr, int l, int r){
        if (l>=r) return 0;
        int mid = l+r >> 1;
        long res = merge_sort_use(arr, l, mid) + merge_sort_use(arr, mid+1, r);
        int i=l, j=mid+1,k=0;
        while (i<=mid && j<=r){
            if (arr[i]<arr[j])
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

}
