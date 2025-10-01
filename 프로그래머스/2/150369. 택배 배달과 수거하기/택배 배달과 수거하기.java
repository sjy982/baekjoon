class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0L;
        int di = n - 1; // 배달 남은 곳의 가장 먼 인덱스
        int pi = n - 1; // 수거 남은 곳의 가장 먼 인덱스

        while (true) {
            while (di >= 0 && deliveries[di] == 0) di--;
            while (pi >= 0 && pickups[pi] == 0) pi--;
            if (di < 0 && pi < 0) break; // 모두 끝

            int far = Math.max(di, pi) + 1; // 1-based 거리
            answer += 2L * far;

            // 배달 처리
            int capD = cap;
            while (di >= 0 && capD > 0) {
                if (deliveries[di] == 0) { di--; continue; }
                int use = Math.min(capD, deliveries[di]);
                deliveries[di] -= use;
                capD -= use;
                if (deliveries[di] == 0) di--;
            }

            // 수거 처리
            int capP = cap;
            while (pi >= 0 && capP > 0) {
                if (pickups[pi] == 0) { pi--; continue; }
                int use = Math.min(capP, pickups[pi]);
                pickups[pi] -= use;
                capP -= use;
                if (pickups[pi] == 0) pi--;
            }
        }
        return answer;
    }
}
