import java.util.*;
class Score {
    int score;
    boolean sb;
    Score(int score, boolean sb) {
        this.score = score;
        this.sb = sb;
    }
}
class Node {
    int cnt, sb_cnt;
    Node(int cnt, int sb_cnt) {
        this.cnt = cnt;
        this.sb_cnt = sb_cnt;
    }
}
class Solution {
    static ArrayList<Score> score_list = new ArrayList<>();
    static Node[] dp;
    public int[] solution(int target) {
        int[] answer = new int[2];
        //다트판 모든 점수 넣기
        score_list.add(new Score(50, true));
        for(int i=1; i<=20; i++) {
            score_list.add(new Score(i, true));
            score_list.add(new Score(i*2, false));
            score_list.add(new Score(i*3, false));
        }
        dp = new Node[target + 61];
        dp[0] = new Node(0, 0);
        for(int i=0; i<=target; i++) {
            for(int j=0; j<score_list.size(); j++) {
                int next_score = i + score_list.get(j).score;
                int next_sb_cnt = score_list.get(j).sb ? dp[i].sb_cnt + 1 : dp[i].sb_cnt;
                if(dp[next_score] == null) dp[next_score] = new Node(dp[i].cnt + 1, next_sb_cnt);
                else {
                    if((dp[i].cnt + 1) < dp[next_score].cnt) dp[next_score] = new Node(dp[i].cnt + 1, next_sb_cnt);
                    else if((dp[i].cnt + 1) == dp[next_score].cnt) {
                        if(next_sb_cnt > dp[next_score].sb_cnt) dp[next_score].sb_cnt = next_sb_cnt;
                    }
                }
            }
        }
        answer[0] = dp[target].cnt;
        answer[1] = dp[target].sb_cnt;
        return answer;
    }
}