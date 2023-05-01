import java.util.*;
class Solution {
    static int[] storey_arr;
    public int solution(int storey) {
        int answer = 0;
        String str = String.valueOf(storey);
        storey_arr = new int[str.length()];
        for(int i=0; i<str.length(); i++) storey_arr[i] = str.charAt(i) - '0';
        for(int i=storey_arr.length-1; i>=1; i--) {
            if(storey_arr[i] == 10) storey_arr[i-1] += 1;
            else if(storey_arr[i] >= 6) {
                answer += (10 - storey_arr[i]);
                storey_arr[i-1] += 1;
            } else if(storey_arr[i] == 5) {
                answer += 5;
                if(5 <= storey_arr[i-1] && storey_arr[i-1] <= 9) storey_arr[i-1] += 1; 
            } else answer += storey_arr[i];
        }
        if(storey_arr[0] == 10) answer += 1;
        else if(storey_arr[0] >= 6) answer += (10 - storey_arr[0]) + 1;
        else answer += storey_arr[0];
        return answer;
    }
}