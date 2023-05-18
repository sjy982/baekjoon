import java.util.*;
class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = -1;
        answer = binary_search(a, b, g, s, w, t, (a + b) * 200000L);
        return answer;
    }
    
    static long binary_search(int a, int b, int[] g, int[] s, int[] w, int[] t, long max) {
        long min = 0;
        while(min <= max) {
            long mid = (min + max) / 2;
            if(possible(a, b, g, s, w, t, mid)) {
                //a, b 목표량 채웠으면 시간 줄이기
                max = mid - 1;
            } else {
                //a, b 목표량 못채웠으면 시간 늘리기
                min = mid + 1;
            }
        }
        return min;
    }
    
    static boolean possible(int a, int b, int[] g, int[] s, int[] w, int[] t, long time) {
        //먼저 골드부터 채운다.
        long total_carry_g = 0;
        long total_carry_s = 0;
        long total_left_s = 0;
        for(int i=0; i<g.length; i++) {
            long carry_weight = time % (t[i] * 2) >= t[i] ? w[i] * (time / (t[i] * 2) + 1) : w[i] * (time / (t[i] * 2));
            long carry_g = 0; //현재 지역에서 가져가는 금 개수
            //금 먼저 옮기기
            if(carry_weight >= g[i]) {
                total_carry_g += g[i];
                carry_g += g[i];
                carry_weight -= g[i];
            } else {
                total_carry_g += carry_weight;
                carry_g += carry_weight;
                carry_weight = 0;
            }
            //나머지 은 옮기기
            if(carry_weight >= s[i]) {
                total_carry_s += s[i];
            } else {
                total_carry_s += carry_weight;
                total_left_s += s[i] - carry_weight <= carry_g ? s[i] - carry_weight : carry_g; //현재 가져간 금 개수를 은으로 바꿀 수 있는 개수 카운트
            }
        }
        if(total_carry_g < a) return false; //금을 우선으로 옮겼는데 미달이면 무조건 false;
        else if(total_carry_g >=a && total_carry_s < b) {
            //은만 미만일 때 가용할 수 있는 은의 개수를 구한다.
            long available_s = (total_carry_g - a) < total_left_s ? (total_carry_g - a) : total_left_s;
            if((available_s + total_carry_s) < b) return false; //가용 할 수 있는 은 + 토탈 은 < b 일 때 false;
        }
        return true;    
    }
}