package 左程云.基础.class08_暴力递归;

import java.util.Stack;

/**
 * @description:
 * @author: Netter
 * @date: 2023-02-11 13:05
 */
public class ReverseStackUsingRecursive {

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = f(stack);
        reverse(stack);
        stack.push(i);
    }

    public static int f(Stack<Integer> stack) {
        Integer result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        }else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }

}