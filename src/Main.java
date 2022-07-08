import java.util.*;
import java.io.*;

public class Main {

    static int[][] a = new int[1010][1010];
    static int[][] b = new int[1010][1010];
    //static int[] a = new int[100010];
    //static int[] b = new int[100010];

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n=sc.nextInt(), m=sc.nextInt(), q=sc.nextInt();

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                a[i][j] = sc.nextInt();

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                insert(i,j,i,j,a[i][j]);

        while (q-->0){
            int x1=sc.nextInt(),y1=sc.nextInt(),x2=sc.nextInt(),y2=sc.nextInt(),c=sc.nextInt();
            insert(x1,y1,x2,y2,c);
        }

        //求前缀和
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                b[i][j] += b[i-1][j] + b[i][j-1] - b[i-1][j-1];

        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= m; j++)
                System.out.print(b[i][j] + " ");
            System.out.println();
        }
    }

    public static void insert(int x1, int y1, int x2, int y2, int c){
        b[x1][y1] += c;
        b[x2+1][y1] -= c;
        b[x1][y2+1] -= c;
        b[x2+1][y2+1] += c;
    }

}