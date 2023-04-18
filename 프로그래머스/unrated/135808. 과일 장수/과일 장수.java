import java.util.*;
class Solution {
    static int[] arr_apple;
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        arr_apple = new int[k+1];
        for(int i=0; i<score.length; i++) arr_apple[score[i]] += 1;
        for(int i=k; i>=1; i--) {
            int sum = sum_apple(i, k);
            answer += i * m * (sum / m);
            arr_apple[i] = sum % m;
        }
        return answer;
    }
    
    static int sum_apple(int s, int k) {
        int cout = 0;
        for(int i=s; i<=k; i++) {
            cout += arr_apple[i];
            arr_apple[i] = 0;
        }
        return cout;
    }
}