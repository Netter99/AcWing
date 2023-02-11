package 左程云.class08;

/**
 * @description:
 * @author: Netter
 * @date: 2023-02-11 14:52
 */
public class ConvertToLetterString {

    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

//    i之前的位置，如何转化已经做过决定
//    i... 有多少种转化的结果
    private static int process(char[] str, int i) {
        if (i == str.length) {
            return 1;
        }
        if (str[i] == '0') {
            return 0;
        }
        if (str[i] == '1') {
            int res = process(str, i + 1); // i自己作为单独的部分，后续有多少种方法
            if (i + 1 < str.length) {
                res += process(str, i + 2);
            }
            return res;
        }
        if (str[i] == '2') {
            int res = process(str, i + 1);
            if (i + 1 < str.length && (str[i+1] >= '0' && str[i+1] <= '6')) {
                res += process(str, i + 2);
            }
            return res;
        }
        // str[i] = 3 ~ 9
        return process(str, i + 1);
    }

    public static void main(String[] args) {
        System.out.println(number("11253"));
        /*
        1 1 2 5 3
        1 12 5 3
        11 2 5 3
        1 1 25 3
        11 25 3
        * */
    }
}