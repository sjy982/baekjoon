import java.util.*;
class Range {
    int s,e;
    Range(int s, int e) {
        this.s = s;
        this.e = e;
    }
}

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        Arrays.sort(targets, (t1, t2) -> {
            return t1[0] - t2[0];
        });
        Range range = new Range(0, targets[targets.length-1][1]); //최대 범위
        for(int i=0; i<targets.length; i++) {
            if((range.s <= targets[i][0]) && (targets[i][0] < range.e)) {
                range = new Range(targets[i][0], Math.min(range.e, targets[i][1]));
            } else {
                answer += 1;
                range = new Range(targets[i][0], targets[i][1]);
            }
        }
        return answer + 1;
    }
}