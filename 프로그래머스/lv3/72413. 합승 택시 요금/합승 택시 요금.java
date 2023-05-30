import java.util.*;
class Node implements Comparable<Node>{
    int p, w;
    Node(int p, int w) {
        this.p = p;
        this.w = w;
    }
    public int compareTo(Node o) {
        return this.w - o.w;
    }
}
class Solution {
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static int N;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        N = n;
        for(int i=0; i<=n; i++) graph.add(new ArrayList<>());
        for(int i=0; i<fares.length; i++) {
            int p1 = fares[i][0];
            int p2 = fares[i][1];
            int w = fares[i][2];
            graph.get(p1).add(new Node(p2, w));
            graph.get(p2).add(new Node(p1, w));
        }
        int[] c_dp = dijkstra(s);
        for(int i=1; i<=n; i++) {
            if(c_dp[i] != Integer.MAX_VALUE) {
                int[] ab_dp = dijkstra(i);
                answer = Math.min(answer, c_dp[i] + ab_dp[a] + ab_dp[b]);
            }
        }
        return answer;
    }
    
    static int[] dijkstra(int start) {
        int[] dp = new int[N+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        boolean[] visited = new boolean[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dp[start] = 0; //시작점
        pq.offer(new Node(start, 0));
        while(!pq.isEmpty()) {
            Node n = pq.poll();
            if(visited[n.p]) continue; //이미 방문했다면
            visited[n.p] = true;
            for(int i=0; i<graph.get(n.p).size(); i++) {
                int next_p = graph.get(n.p).get(i).p;
                int next_w = n.w + graph.get(n.p).get(i).w;
                if(dp[next_p] > next_w) {
                    dp[next_p] = next_w;
                    pq.offer(new Node(next_p, next_w));
                }
            }
        }
        return dp;
    }
}