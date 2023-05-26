import java.util.*;
class Solution {
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    static boolean[] visited;
    static long answer = 0;
    public long solution(int[] w, int[][] edges) {
        long sum = 0;
        visited = new boolean[w.length];
        for(int i=0; i<w.length; i++) {
            tree.add(new ArrayList<>());
            sum += w[i];
        }
        if(sum == 0) {
            for(int i=0; i<edges.length; i++) {
                int a = edges[i][0];
                int b = edges[i][1];
                tree.get(a).add(b);
                tree.get(b).add(a);
            }
            visited[0] = true;
            DFS(0, w);
        } else return -1;
        
        return answer;
    }
    
    static long DFS(int a, int[] w) {
        long sum_weight = w[a];
        for(int i=0; i<tree.get(a).size(); i++) {
            int b = tree.get(a).get(i);
            if(!visited[b]) {
                visited[b] = true;
                sum_weight += DFS(b, w);
            }
        }
        answer += Math.abs(sum_weight);
        return (long) sum_weight;
    }
}