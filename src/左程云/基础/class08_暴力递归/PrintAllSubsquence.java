package 左程云.基础.class08_暴力递归;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 打印子串
 * @author: Netter
 * @date: 2023-02-11 9:57
 */
public class PrintAllSubsquence {

    public static void printAllSubsquence(String str) {
        char[] chs = str.toCharArray();
        process(chs, 0);
    }

    public static void process(char[] str, int i) {
        if (i == str.length) {
            System.out.println(String.valueOf(str));
            return;
        }
        process(str, i+1);
        char tmp = str[i];
        str[i] = 0;
        process(str, i+1);
        str[i] = tmp;
    }

    public static void function(String str) {
        char[] chs = str.toCharArray();
        process(chs, 0, new ArrayList<Character>());
    }

    public static void process(char[] str, int i, List<Character> res) {
        if (i == str.length) {
            System.out.println(String.valueOf(str));
            return;
        }
        List<Character> resKeep = copyList(res);
        resKeep.add(str[i]);
        process(str, i+1, resKeep);
        List<Character> resNoInclude = copyList(res);
        process(str, i+1, resNoInclude);
    }

    private static List<Character> copyList(List<Character> res) {
        return null;
    }



}