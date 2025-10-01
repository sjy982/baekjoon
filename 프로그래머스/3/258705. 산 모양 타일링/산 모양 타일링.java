class Solution {
    static final int MOD = 10007;

    public int solution(int n, int[] tops) {
        // A: 경계까지 딱 맞게 채움, B: 경계에 이어져야 완성되는 모양이 하나 남아 있음
        int A = 1; // 아무것도 놓지 않은 상태 = 깔끔
        int B = 1; // 왼쪽 사선 변 때문에 시작부터 '이어질 수 있는' 상태도 1가지로 본다

        for (int i = 0; i < n; i++) {
            int t = tops[i];
            int nA, nB;
            if (t == 0) {
                nA = ( (2 * A) % MOD + B ) % MOD;     // A' = 2A + B
                nB = ( A + B ) % MOD;                 // B' = A + B
            } else {
                nA = ( (3 * A) % MOD + B ) % MOD;     // A' = 3A + B
                nB = ( (2 * A) % MOD + B ) % MOD;     // B' = 2A + B
            }
            A = nA;
            B = nB;
        }
        return A % MOD;
    }
}
