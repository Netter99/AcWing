package 左程云.基础.class07_贪心;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @description: 分金块最少代价  --- 哈夫曼编码
 * @author: Netter
 * @date: 2023-02-07 17:04
 */
public class LessMoneySplitGold {

    public static class MinheapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2; // 负数：o1 < o2
        }
    }

    public static int lessMoney(int[] arr) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i : arr) {
            queue.add(i);
        }
        int cur = 0;
        int sum = 0;
        while (queue.size() > 1) {
            cur = queue.poll() + queue.poll();
            sum += cur;
            queue.add(cur);
        }
        return sum;
    }

}
