package 左程云.class08;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 打印所有的组合
 * @author: Netter
 * @date: 2023-02-11 10:15
 */
public class PrintAllPermutations {

    public static List<String> printAllPermutations(String str) {
        char[] chs = str.toCharArray();
        List<String> res = new ArrayList<>();
        process(chs, 0, res);
        return res;
    }

//    str[i...]范围上，所有的字符，都可以在i位置上，后续都去尝试
//    str[0..i-1]范围上，是之前做的选择
    private static void process(char[] str, int i, List<String> res) {
        if (i == str.length) {
            res.add(String.valueOf(str));
            return;
        }
        boolean[] visit = new boolean[23];
        for (int j = i; j < str.length; j++) {
            if (!visit[str[j] - 'a']) {
                visit[str[j] - 'a'] = true;
                swap(str, i, j);
                process(str, i+1, res);
                swap(str, i, j);
            }
        }
    }

    private static void swap(char[] str, int i, int j) {
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }

    public static void main(String[] args) {
        String a = "abbc";
        for (String printAllPermutation : printAllPermutations(a)) {
            System.out.println(printAllPermutation);
        }
    }


}