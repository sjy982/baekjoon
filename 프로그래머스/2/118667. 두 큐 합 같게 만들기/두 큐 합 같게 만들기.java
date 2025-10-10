class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int n = queue1.length;
        int m = queue2.length;
        int L = n + m;

        long sum1 = 0, sum2 = 0;
        long[] arr = new long[L];

        // arr = queue1 + queue2, sum1/sum2 계산
        for (int i = 0; i < n; i++) {
            arr[i] = queue1[i];
            sum1 += queue1[i];
        }
        for (int i = 0; i < m; i++) {
            arr[n + i] = queue2[i];
            sum2 += queue2[i];
        }

        long total = sum1 + sum2;
        if ((total & 1L) == 1L) {   // 총합이 홀수면 불가능
            return -1;
        }
        long target = total / 2;

        // 두 포인터: i = q1의 front, j = q2의 front
        int i = 0;     // q1 front
        int j = n;     // q2 front (arr에서 n부터 시작)
        int ops = 0;
        int limit = (n + m) * 3; // 안전한 상한

        // 그리디 이동 (O(n))
        while (ops <= limit) {
            if (sum1 == target) return ops;

            if (sum1 > target) {
                long x = arr[i % L];
                sum1 -= x;         // q1에서 빼고
                // q2로 간 걸 굳이 arr에 붙일 필요 없이 합만 반영하면 됨
                i++;
            } else {
                long x = arr[j % L];
                sum1 += x;         // q2에서 q1로 옮겨 sum1 증가
                j++;
            }
            ops++;
        }

        return -1; // 상한 초과 → 불가능
    }
}
