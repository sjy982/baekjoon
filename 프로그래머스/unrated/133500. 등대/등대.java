import java.util.*;
class Solution {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited, lighthouse_state;
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        visited = new boolean[n+1];
        lighthouse_state = new boolean[n+1];
        for(int i=0; i<=n; i++) graph.add(new ArrayList<>());
        for(int i=0; i<lighthouse.length; i++) {
            int a = lighthouse[i][0];
            int b = lighthouse[i][1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        visited[1] = true;
        DFS(1);
        for(int i=1; i<=n; i++) if(lighthouse_state[i]) answer += 1;
        return answer;
    }
    static boolean DFS(int a) {
        boolean isLeaf = true;
        for(int i=0; i<graph.get(a).size(); i++) {
            int b = graph.get(a).get(i);
            if(!visited[b]) {
                isLeaf = false;
                visited[b] = true;
                if(!DFS(b)) lighthouse_state[a] = true;
            }
        }
        if(isLeaf) return false;
        else return lighthouse_state[a];
    }
}