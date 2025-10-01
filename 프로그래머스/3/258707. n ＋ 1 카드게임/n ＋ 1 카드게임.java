import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        int target = n + 1;

        // 위치 맵
        int[] idx = new int[n + 1];
        for (int i = 0; i < n; i++) idx[cards[i]] = i;

        int first = n / 3;                 // 시작에 받는 개수
        int totalRounds = (n - first) / 2; // 진행 가능한 최대 라운드 수

        // 쌍들을 라운드별로 분류
        int zero = 0; // avail=0, cost=0 쌍 개수
        List<List<Integer>> appear = new ArrayList<>();
        for (int t = 0; t <= totalRounds; t++) appear.add(new ArrayList<>());

        // a<b로 한 번씩만 만들기
        for (int a = 1; a <= n; a++) {
            int b = target - a;
            if (a >= b) break; // 중복 방지

            int ia = idx[a], ib = idx[b];

            boolean aStart = ia < first;
            boolean bStart = ib < first;

            if (aStart && bStart) {
                // 시작 손패로 바로 낼 수 있는 0코인 쌍
                zero++;
            } else if (aStart ^ bStart) {
                // 한쪽만 시작 구간: 다른 한 장의 등장 라운드에 cost=1로 사용 가능
                int laterIdx = aStart ? ib : ia;
                int round = (laterIdx - first) / 2 + 1;
                appear.get(round).add(1); // cost=1
            } else {
                // 둘 다 이후: 더 늦게 등장하는 라운드부터 cost=2로 사용 가능
                int laterIdx = Math.max(ia, ib);
                int round = (laterIdx - first) / 2 + 1;
                appear.get(round).add(2); // cost=2
            }
        }

        // 라운드 진행: 현재까지 사용 가능한 쌍들 중 가장 싼 cost를 택하기
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 비용 최소 힙
        int cleared = 0;

        for (int t = 1; t <= totalRounds; t++) {
            // 이 라운드에 새로 사용 가능해진 쌍 추가
            for (int c : appear.get(t)) pq.offer(c);

            // 이번 라운드에 낼 쌍 선택
            if (zero > 0) {
                zero--;
                cleared++;
            } else if (!pq.isEmpty()) {
                int cost = pq.peek();
                if (coin >= cost) {
                    coin -= cost;
                    pq.poll();
                    cleared++;
                } else {
                    break; // 더 이상 진행 불가
                }
            } else {
                break;
            }
        }
        // 도달 가능한 최대 라운드 번호
        return cleared + 1;
    }
}
