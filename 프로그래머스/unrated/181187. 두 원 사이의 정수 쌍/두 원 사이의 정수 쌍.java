class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        long cm_p = 0;
        for(int i=0; i<r1; i++) {
            double d_y1 = Math.sqrt(Math.pow(r1, 2) - Math.pow(i, 2));
            long y1 = (long) d_y1;
            if(d_y1 % 1 != 0) y1 += 1;
            long y2 = (long) Math.sqrt(Math.pow(r2, 2) - Math.pow(i, 2));   
            if(i == 0) cm_p = (y2 - y1 + 1) * 4;
            else answer += (y2 - y1) + 1;
        }
        for(int i=r1; i<r2; i++) answer += (long) Math.sqrt(Math.pow(r2, 2) - Math.pow(i, 2));
        answer *= 4;
        answer += cm_p;
        return answer;
    }
}