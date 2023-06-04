import java.util.*;
class Solution {
    static HashMap<String, Boolean> jew_types = new HashMap<>();
    static HashMap<String, Integer> jew = new HashMap<>();
    static Queue<Integer> que = new LinkedList<>();
    public int[] solution(String[] gems) {
        int[] answer = {1, Integer.MAX_VALUE};
        for(int i=0; i<gems.length; i++) if(jew_types.get(gems[i]) == null) jew_types.put(gems[i], true);
        for(int i=0; i<gems.length; i++) {
            if(jew.get(gems[i]) == null) jew.put(gems[i], 1);
            else jew.put(gems[i], jew.get(gems[i]) + 1);
            que.add(i);
            if(jew.size() == jew_types.size()) {
                //모든 보석 종류가 모였다면
                while(jew.get(gems[que.peek()]) > 1) {
                    int ind = que.poll();
                    jew.put(gems[ind], jew.get(gems[ind]) - 1);
                }
                if(answer[1] - answer[0] > i - que.peek()) {
                    answer[0] = que.peek() + 1;
                    answer[1] = i + 1;
                }
            }
        }
        return answer;
    }
}