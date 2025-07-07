import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        
        for(int i=0; i<stations.length; i++) {
            if(i == 0) {
                answer += cal(1, stations[i] - w - 1, w);
            } else {
                answer += cal(stations[i - 1] + w + 1, stations[i] - w - 1, w);
            }
            
            if(i == stations.length - 1) {
                answer += cal(stations[i] + w + 1, n, w);
            }
        }
        return answer;
    }
    
    static int cal(int start, int end, int w) {
        //빈칸 start 지점, 빈칸 end 지점
        if(start > end) {
            return 0;
        }
        int std = 2 * w + 1;
        int blank = end - start + 1;
        
        int result = blank / std;
        if(blank % std != 0) {
            result += 1;
        }
        return result;
    }
}