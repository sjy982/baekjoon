import java.util.*;
class Solution {
    static final int[] dc_ratio = {10, 20, 30, 40}; 
    static ArrayList<Integer> result = new ArrayList<>();
    static int[] answer = new int[2];
    public int[] solution(int[][] users, int[] emoticons) {
        DFS(users, emoticons);
        return answer;
    }
    static void DFS(int[][] users, int[] emoticons) {
        if(result.size() == emoticons.length) {
            int[] cal_result = new int[2];
            for(int i=0; i<users.length; i++) {
                int sum = 0;
                for(int j=0; j<result.size(); j++) {
                    if(result.get(j) >= users[i][0]) sum += emoticons[j] - ((emoticons[j] * result.get(j)) / 100);
                    if(sum >= users[i][1]) {
                        cal_result[0] += 1;
                        sum = 0;
                        break;
                    }
                }
                cal_result[1] += sum;
            }
            if(answer[0] < cal_result[0]) answer = cal_result;
            else if((answer[0] == cal_result[0]) && answer[1] < cal_result[1]) answer = cal_result;
            return;
        }
        for(int i=0; i<4; i++) {
            result.add(dc_ratio[i]);
            DFS(users, emoticons);
            result.remove(result.size()-1);
        }
    }
}