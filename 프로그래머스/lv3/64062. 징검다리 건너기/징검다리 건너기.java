import java.util.*;
class Solution {
    public int solution(int[] stones, int k) {
        return binary_search(stones, k);
    }
    
    static int binary_search(int[] stones, int k) {
        int min = 1;
        int max = 200000000;
        while(min <= max) {
            int mid  = (min + max) / 2;
            if(check(mid, stones, k)) min = mid + 1;
            else max = mid - 1;
        }
        return max;
    }
    
    static boolean check(int v, int[] stones, int k) {
        int zero = 0;
        for(int i=0; i<stones.length; i++) {
            if(stones[i] - (v-1) <= 0) zero += 1;
            else zero = 0;
            //반드시 점프해야하는 횟수 (zero + 1)가 k보다 커진다면 false;
            if(zero + 1 > k) return false;
        }
        return true;
    }
}