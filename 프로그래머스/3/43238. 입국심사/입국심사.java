import java.io.*;
import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = binarySearch(1, 1000000000L * 1000000000L , n, times);
        return answer;
    }
    
    static long binarySearch(long min, long max, int n, int[] times) {
        while(min <= max) {
            long mid = (min + max) / 2;
            long sum = find(times, mid);
            
            if(sum < n) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return min;
    }
    
    static long find(int[] times, long time) {
        long result = 0;
        for(int i=0; i<times.length; i++) {
            result += (time / times[i]);
        }
        return result;
    }
}