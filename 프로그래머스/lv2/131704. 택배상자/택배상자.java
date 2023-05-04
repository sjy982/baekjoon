import java.util.*;
class Solution {
    static Stack<Integer> main_stack = new Stack<>();
    static Stack<Integer> assist_stack = new Stack<>();
    public int solution(int[] order) {
        int answer = 0;
        for(int i=order.length; i>=1; i--) main_stack.push(i);
        for(int i=0; i<order.length; i++) {
            if((main_stack.size() != 0) && order[i] >= main_stack.peek()) {
                int repeat = order[i] - main_stack.peek();
                for(int j=0; j<repeat; j++) assist_stack.push(main_stack.pop());
                main_stack.pop();
                answer += 1;
            } else {
                if(assist_stack.peek() == order[i]) {
                    assist_stack.pop();
                    answer += 1;
                } else break;
            }
        }
        return answer;
    }
}