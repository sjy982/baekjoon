import java.util.*;
class Node {
    int s1, s2, ind;
    Node(int s1, int s2, int ind) {
        this.s1 = s1;
        this.s2 = s2;
        this.ind = ind;
    }
}
class Solution {
    static ArrayList<Node> scores_list = new ArrayList<>();
    static Node[] scores2;
    public int solution(int[][] scores) {
        int answer = -1;
        scores2 = new Node[scores.length];
        for(int i=0; i<scores.length; i++) scores2[i] = new Node(scores[i][0], scores[i][1], i);
        Arrays.sort(scores2, (a,b) -> {
            if(b.s1 > a.s1) return 1;
            else if(b.s1 < a.s1) return -1;
            else if(b.s1 == a.s1) {
                return a.s2 - b.s2;
            }
            return -1;
        });
        int max = -1;
        for(int i=0; i<scores2.length; i++) {
            if(i==0) scores_list.add(scores2[i]); 
            else {
                if((scores2[i-1].s1 != scores2[i].s1) && (scores2[i-1].s2 > max)) max = scores2[i-1].s2;
                if(max <= scores2[i].s2) scores_list.add(scores2[i]);
            }
        }
        Collections.sort(scores_list, (a, b) -> {
            return (b.s1 + b.s2) - (a.s1 + a.s2);
        });
        int same = 1;
        for(int i=0; i<scores_list.size(); i++) {
            int rank = (i + 1);
            if(i != 0) {
                int before_score = scores_list.get(i-1).s1 + scores_list.get(i-1).s2;
                int cur_score = scores_list.get(i).s1 + scores_list.get(i).s2;
                if(before_score == cur_score) {
                    rank -= same;
                    same += 1;
                } else same = 1;
            }
            if(scores_list.get(i).ind == 0) answer = rank;
        }
        return answer;
    }
}