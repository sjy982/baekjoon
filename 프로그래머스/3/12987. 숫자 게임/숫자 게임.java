import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        
        int cursor = 0;
        int answer = 0;
        for(int i=0; i<A.length; i++) {
            while(cursor < B.length) {
                if(A[i] < B[cursor]) {
                    answer += 1;
                    cursor += 1;
                    break;
                }
                cursor += 1;
            }
        }
        return answer;
    }
}