import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));
        int answer = 0;
        int last = -30001;
        
        for(int i=0; i<routes.length; i++) {
            if((routes[i][0] <= last) && (last <= routes[i][1])) {
                //들어간다면 pass
                continue;
            }
            //들어가지 않는다면 끝을 기준으로 설치한다.
            answer += 1;
            last = routes[i][1];
        }
        
        return answer;
    }
}