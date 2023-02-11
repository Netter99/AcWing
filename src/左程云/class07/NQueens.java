package 左程云.class07;

/**
 * @description: N皇后
 * @author: Netter
 * @date: 2023-02-08 15:55
 */
public class NQueens {

    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return process(0, record, n);
    }

    public static int process(int i, int[] record, int n) {
        if (i == n) {
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process(i+1, record, n);
            }
        }
        return res;
    }

    public static boolean isValid(int[] record, int i, int j) {
        // record[0...i-1]需要看
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(i - k) == Math.abs(j - record[k])) {
                return false;
            }
        }
        return true;
    }

}
