import java.util.*;
class Solution {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;
    static int N;
    public int solution(int n, int[][] wires) {
        N = n;
        visited = new boolean[n + 1];
        for(int i=0; i<=n; i++) graph.add(new ArrayList<>());
        for(int i=0; i<wires.length; i++) {
            graph.get(wires[i][0]).add(wires[i][1]);
            graph.get(wires[i][1]).add(wires[i][0]);
        }
        DFS(1);
        return answer;
    }
    
    static int DFS(int a) {
        int cnt = 1;
        visited[a] = true;
        for(int i=0; i<graph.get(a).size(); i++) {
            int b = graph.get(a).get(i);
            if(!visited[b]) cnt += DFS(b);
        }
        answer = Math.min(answer, Math.abs((N - cnt) - cnt));
        return cnt;
    }
}