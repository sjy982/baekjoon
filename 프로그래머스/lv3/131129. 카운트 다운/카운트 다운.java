import java.util.*;
class Node {
    int cout, bs_cout;
    Node(int cout, int bs_cout) {
        this.cout = cout;
        this.bs_cout = bs_cout;
    }
}
class Solution {
    static Node[] dp;
    public int[] solution(int target) {
        int[] answer = new int[2];
        dp = new Node[100001];
        dp[50] = new Node(1, 1);
        for(int i=1; i<=20; i++) {
            for(int j=1; j<=3; j++) {
                int single = j == 1 ? 1 : 0;
                if(dp[i * j] == null) dp[i * j] = new Node(1, single);
                else if(dp[i * j].bs_cout < single) dp[i * j] = new Node(1, single);
            }
        }
        for(int i=1; i<=60; i++) if(dp[i] == null) dp[i] = find_best(i, 1, i-1);
        for(int i=61; i<=target; i++) dp[i] = find_best(i, i - 60, i - 1);
        answer[0] = dp[target].cout;
        answer[1] = dp[target].bs_cout;
        return answer;
    }
    
    static Node find_best(int score, int start, int end) {
        Node n = new Node(Integer.MAX_VALUE, -1);
        for(int i=start; i<=end; i++) {
            if(n.cout > dp[score - i].cout + dp[i].cout) {
                n = new Node(dp[score - i].cout + dp[i].cout, dp[score - i].bs_cout + dp[i].bs_cout);
            } else if(n.cout == dp[score - i].cout + dp[i].cout) {
                if(n.bs_cout < dp[score - i].bs_cout + dp[i].bs_cout) {
                    n = new Node(n.cout, dp[score - i].bs_cout + dp[i].bs_cout);
                }
            }
        }
        return n;
    }
}