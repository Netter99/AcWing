package 左程云.class08;

/**
 * @description:
 * @author: Netter
 * @date: 2023-02-10 15:14
 */
public class Hanoi {

    public static void hanoi(int n) {
        if (n > 0) {
            func(n, "左", "右", "中");
        }
    }

    // 1~i 圆盘 目标是from -> to，other是另一个
    private static void func(int i, String start, String end, String other) {
        if (i == 1) { // base
            System.out.println("Move 1 from" + start + " to " + end);
        }else {
            func(i-1, start, other, end);
            System.out.println("Move " + i + " from " + start + " to " + end);
            func(i-1, other, end, start);
        }
    }

    public static void main(String[] args) {
        int n = 3;
        hanoi(3);
    }

}
