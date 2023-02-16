package 左程云.基础.class07_贪心;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @description: 贪心2 最小拼接字符
 * @author: Netter
 * @date: 2023-02-07 15:36
 */
public class LowestLexicography {

    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return (a + b).compareTo(b + a);
        }
    }

    public static String lowestString(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, new MyComparator());
        String str = "";
        for (String s : strs) {
            str += s;
        }
        return str;
    }

}
