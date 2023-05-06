class Solution {
    static long answer = -100001;
    public long solution(int[] sequence) {
        int[] per_seq = new int[sequence.length];
        int p = 1;
        for(int i=0; i<sequence.length; i++) {
            per_seq[i] = sequence[i] * p;
            p *= -1;
        }
        update_max(per_seq);
        for(int i=0; i<per_seq.length; i++) {
            per_seq[i] = per_seq[i] * -1;
        }
        update_max(per_seq);
        return answer;
    }
    static void update_max(int[] seq) {
        long sum = 0;
        long min = 0;
        for(int i=0; i<seq.length; i++) {
            sum += seq[i];
            answer = Math.max(answer, sum - min);
            if(min > sum) min = sum;
        }
    }
}