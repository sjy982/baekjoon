import java.util.*;
class Solution {
    static int[] weights_cout;
    public long solution(int[] weights) {
        long answer = 0;
        weights_cout = new int[1001];
        for(int i=0; i<weights.length; i++) weights_cout[weights[i]] += 1;
        for(int i=0; i<weights.length; i++) {
            double v2 = weights[i] * (2.0 / 3.0);
            double v3 = weights[i] * (2.0 / 4.0);
            double v4 = weights[i] * (3.0 / 4.0);
            if(v2 % 1 == 0) answer += weights_cout[(int)v2];
            if(v3 % 1 == 0) answer += weights_cout[(int)v3];
            if(v4 % 1 == 0) answer += weights_cout[(int)v4];
        }
        for(int i=0; i<weights_cout.length; i++) {
            if(weights_cout[i] > 1) {
                for(int j=weights_cout[i]-1; j>=1; j--) answer += j;
            }
        }
        return answer;
    }
}