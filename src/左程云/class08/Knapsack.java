package 左程云.class08;

/**
 * @description:
 * @author: Netter
 * @date: 2023-02-11 15:03
 */
public class Knapsack {

    public static int maxValue1(int[] weights, int[] values, int bag) {
        return process1(weights, values, 0, 0, bag);
    }

    // i... 的货物自由选择，形成最大价值返回
    private static int process1(int[] weights, int[] values,
                    int i, int alreadyweight, int bag) {
        if (alreadyweight > bag) {
            return 0;
        }
        if (i == weights.length) {
            return 0;
        }
        return Math.max(
                process1(weights, values, i + 1, alreadyweight, bag),
                values[i] + process1(weights, values, i + 1, alreadyweight + weights[i], bag)
        );
    }

}