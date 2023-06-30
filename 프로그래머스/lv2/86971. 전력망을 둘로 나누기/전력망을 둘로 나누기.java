import java.util.*;
class Solution {
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        for(int i=0; i<wires.length; i++) {
            make_graph(wires, i, n);
            visited = new boolean[n + 1];
            ArrayList<Integer> cnt_list = new ArrayList<>();
            for(int j=1; j<=n; j++) {
                if(!visited[j]) cnt_list.add(DFS(j));
            }
            answer = Math.min(answer, Math.abs(cnt_list.get(1) - cnt_list.get(0)));
        }
        return answer;
    }
    
    static int DFS(int a) {
        int cnt = 1;
        visited[a] = true;
        for(int i=0; i<graph.get(a).size(); i++) {
            int b = graph.get(a).get(i);
            if(!visited[b]) cnt += DFS(b);
        }
        return cnt;
    }
    
    static void make_graph(int[][] wires, int ind, int n) {
        graph = new ArrayList<>();
        for(int i=0; i<=n; i++) graph.add(new ArrayList<>());
        for(int i=0; i<wires.length; i++) {
            if(i != ind) {
                graph.get(wires[i][0]).add(wires[i][1]);
                graph.get(wires[i][1]).add(wires[i][0]);
            }
        }
    }
}