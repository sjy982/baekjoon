import java.util.*;
class Solution {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        for(int i=0; i<enemy.length; i++) {
            if(priorityQueue.size() < k) {
                priorityQueue.add(enemy[i]);
            } else if(priorityQueue.size() == k){
                if(priorityQueue.peek() < enemy[i]) {
                    n -= priorityQueue.poll();
                    priorityQueue.add(enemy[i]);
                } else n -= enemy[i];
            }
            if(n < 0) break;
            answer += 1;
        }
        return answer;
    }
}