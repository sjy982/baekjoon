class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        long cm_p = 1;
        for(int i=0; i<d; i+=k) {
            long y = (long) Math.sqrt(Math.pow(d, 2) - Math.pow(i, 2));
            if(i==0) cm_p += (y / k) * 2;
            else answer += (y / k);
        }
        return answer + cm_p;
    }
}