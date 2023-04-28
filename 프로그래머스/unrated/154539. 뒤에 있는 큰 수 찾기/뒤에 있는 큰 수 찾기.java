import java.util.*;
class NumInfo {
    int v, ind;
    NumInfo(int v, int ind) {
        this.v = v;
        this.ind = ind;
    }
}
class Solution {
    static Stack<NumInfo> stack = new Stack<>();
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        for(int i=0; i<numbers.length; i++) {
            if(stack.size() == 0) stack.push(new NumInfo(numbers[i], i));
            else {
                while(stack.size() != 0 && (stack.peek().v < numbers[i])) {
                    answer[stack.pop().ind] = numbers[i];
                }
                stack.push(new NumInfo(numbers[i], i));
            }
        }
        while(stack.size() != 0) answer[stack.pop().ind] = -1;
        return answer;
    }
}