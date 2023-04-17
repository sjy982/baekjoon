import java.util.*;
class Solution {
    static char x;
    static int x_c = 0, nx_c = 0;
    public int solution(String s) {
        int answer = 0;
        x = s.charAt(0);
        for(int i=0; i<s.length(); i++) {
            if(x == s.charAt(i)) x_c += 1;
            else nx_c += 1;
            
            if((x_c != 0 && nx_c != 0) && (x_c == nx_c)) {
                answer += 1;
                x_c = 0;
                nx_c = 0;
                if(i != s.length()-1) x = s.charAt(i+1);
            } else if(i == s.length()-1) answer += 1;
        }
        return answer;
    }
}