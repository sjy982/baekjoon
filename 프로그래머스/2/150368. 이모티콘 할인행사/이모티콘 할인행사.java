import java.util.*;

class Solution {
    static ArrayList<Integer> result = new ArrayList<>();
    static int[] answer = new int[2];
    public int[] solution(int[][] users, int[] emoticons) {
        int[] discount = {10, 20, 30, 40};
        dfs(users, emoticons, discount);
        return answer;
    }
    
    static void dfs(int[][] users, int[] emoticons, int[] discount) {
        if(result.size() == emoticons.length) {
            int[] cal = new int[2];
            for(int i=0; i<users.length; i++) {
                int price = 0;
                for(int j=0; j<emoticons.length; j++) {
                    if(users[i][0] <= discount[result.get(j)]) {
                        //할인율이 이상인 경우
                        price += (emoticons[j] - ((emoticons[j] * discount[result.get(j)]) / 100));
                    }
                }
                if(users[i][1] <= price) {
                    cal[0] += 1;
                } else {
                    cal[1] += price;
                }
            }
            if(answer[0] < cal[0]) {
                answer = cal;
            } else if(answer[0] == cal[0]) {
                if(answer[1] < cal[1]) {
                    answer = cal;
                }
            }
            return;
        }
        for(int i=0; i<=3; i++) {
            result.add(i);
            dfs(users, emoticons, discount);
            result.remove(result.size() - 1);
        }
    }
}