package 左程云.class07;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @description: 数据流实时取中位数
 * @author: Netter
 * @date: 2023-02-10 11:39
 */
public class MedianFinder {

    private PriorityQueue<Integer> min = new PriorityQueue<>();
    private PriorityQueue<Integer> max = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });

    private int count;

    public void insert(int num) {
        count++;
        if (count == 1) {
            max.add(num);
        }
        if (num <= max.peek()) {
            max.add(num);
        }else {
            min.add(num);
        }
        if (max.size() - min.size() > 1) {
            min.add(max.poll());
        }else if (min.size() - max.size() > 1) {
            max.add(min.poll());
        }
    }

    public double getMedian() {
        if (count % 2 == 0) {
            return (min.peek() + max.peek()) / 2;
        }else {
            return min.size() > max.size() ? min.peek() : max.peek();
        }
    }


}
