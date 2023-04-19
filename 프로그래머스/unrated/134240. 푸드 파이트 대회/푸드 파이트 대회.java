import java.util.*;
class Solution {
    static Queue<Integer> queue = new LinkedList<>();
    static Stack<Integer> stack = new Stack<>();
    public String solution(int[] food) {
        StringBuilder answer = new StringBuilder();
        for(int i=1; i<food.length; i++) {
            int push_cout = food[i] / 2;
            for(int j=0; j<push_cout; j++) {
                queue.add(i);
                stack.push(i);
            }
        }
        while(queue.size()!=0) {
            answer.append(queue.poll());
        }
        answer.append('0');
        while(stack.size()!=0) {
            answer.append(stack.pop());
        }
        return answer.toString();
    }
}