import java.util.*;
class Solution {
    static int card1_order = 0, card2_order = 0;
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "Yes";
        for(int i=0; i<goal.length; i++) {
            if(cards1[card1_order].equals(goal[i])) {
                if(card1_order != cards1.length-1) card1_order += 1;
            } else if(cards2[card2_order].equals(goal[i])) {
                if(card2_order != cards2.length-1) card2_order += 1;
            } else {
                answer = "No";
                break;
            }
        }
        return answer;
    }
}