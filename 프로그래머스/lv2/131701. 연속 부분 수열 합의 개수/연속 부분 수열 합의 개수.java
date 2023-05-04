import java.util.*;
class Solution {
    static HashMap<Integer, Boolean> visited = new HashMap<>();
    public int solution(int[] elements) {
        int answer = 0;
        for(int i=0; i<elements.length; i++) {
            int sum = 0;
            for(int j=0; j<elements.length; j++) {
                int n_i = i + j;
                if(n_i < elements.length) sum += elements[n_i];
                else sum += elements[n_i - elements.length];
                if(visited.get(sum) == null) {
                    visited.put(sum, true);
                    answer += 1;
                }
            }
        }
        return answer;
    }
}