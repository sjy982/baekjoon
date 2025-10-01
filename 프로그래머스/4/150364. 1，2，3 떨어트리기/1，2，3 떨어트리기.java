import java.util.*;

class Solution {
    public int[] solution(int[][] edges, int[] target) {
        int N = target.length;                  // 노드: 1..N
        List<Integer>[] child = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) child[i] = new ArrayList<>();
        for (int[] e : edges) child[e[0]].add(e[1]);
        for (int i = 1; i <= N; i++) Collections.sort(child[i]);

        boolean[] isLeaf = new boolean[N + 1];
        for (int i = 1; i <= N; i++) isLeaf[i] = child[i].isEmpty();

        // 로터 포인터(각 부모가 가리키는 자식 인덱스)
        int[] ptr = new int[N + 1];

        // 각 리프의 필요 최소 방문수 lb = ceil(t/3)
        int[] lb = new int[N + 1];
        for (int i = 1; i <= N; i++) if (isLeaf[i]) lb[i] = (target[i - 1] + 2) / 3;

        // 시뮬레이션: 하한을 모두 만족하는 가장 이른 시점 T까지
        int[] visit = new int[N + 1];
        int need = 0; // 남은 하한 방문 수의 총합
        for (int i = 1; i <= N; i++) need += lb[i];

        List<Integer> order = new ArrayList<>();
        while (need > 0) {
            int leaf = dropOnce(child, ptr);    // 한 번 떨어뜨려 도착한 리프
            order.add(leaf);
            visit[leaf]++;
            // 리프의 목표 초과 -> 불가능
            if (visit[leaf] > target[leaf - 1]) return new int[]{-1};
            // 하한을 갱신
            if (visit[leaf] <= lb[leaf]) need--;
        }

        // 이제 길이 T=order.size()로 가능. 사전순 최소로 값(1/2/3) 배정
        int T = order.size();
        int[] remSum = new int[N + 1];
        int[] leftCnt = new int[N + 1];
        for (int i = 1; i <= N; i++) remSum[i] = target[i - 1];
        for (int v : order) leftCnt[v]++;

        int[] ans = new int[T];
        for (int i = 0; i < T; i++) {
            int leaf = order.get(i);
            // 남은 방문수는 이 칸 포함되어 있으니, 가정 값 선택 후 cnt-1 사용
            for (int val = 1; val <= 3; val++) {
                if (remSum[leaf] - val < 0) continue;
                int cntAfter = leftCnt[leaf] - 1;
                int remAfter = remSum[leaf] - val;
                if (cntAfter <= remAfter && remAfter <= 3 * cntAfter) {
                    ans[i] = val;
                    remSum[leaf] = remAfter;
                    leftCnt[leaf] = cntAfter;
                    break;
                }
            }
        }
        return ans;
    }

    // 로터-루터 한 번: 현재 포인터들을 이용해 리프까지 내려가며,
    // 지나간 각 노드의 포인터를 다음 자식으로 회전시킨다.
    private int dropOnce(List<Integer>[] child, int[] ptr) {
        int u = 1;
        while (!child[u].isEmpty()) {
            int idx = ptr[u];
            int v = child[u].get(idx);
            ptr[u] = (idx + 1) % child[u].size(); // 다음 자식으로 회전
            u = v;
        }
        return u; // 리프
    }
}
