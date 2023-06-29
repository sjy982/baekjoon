import java.util.*;
class Solution {
    static ArrayList<Integer> answer_list = new ArrayList<>();
    public int[] solution(int n, long left, long right) {
        int[] answer = {};
        long i = left / n;
        long j = left % n;
        while(i * n + j <= right) {
            answer_list.add(Math.max((int) i + 1, (int) j + 1));
            j += 1;
            if(j == n) {
                j = 0;
                i += 1;
            }
        }
        answer = new int[answer_list.size()];
        for(int k=0; k<answer_list.size(); k++) answer[k] = answer_list.get(k);
        return answer;
    }
}