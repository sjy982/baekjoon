import java.util.*;
class Node {
    int b,w;
    Node(int b, int w) {
        this.b = b;
        this.w = w;
    }
}
class Solution {
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static int[] w_visited;
    static boolean[] mountain_peak;
    static int min_intensity = Integer.MAX_VALUE;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        w_visited = new int[n+1];
        Arrays.fill(w_visited, Integer.MAX_VALUE);
        mountain_peak = new boolean[n+1];
        for(int i=0; i<=n; i++) graph.add(new ArrayList<>());
        for(int i=0; i<paths.length; i++) {
            int a = paths[i][0];
            int b = paths[i][1];
            graph.get(a).add(new Node(b, paths[i][2]));
            graph.get(b).add(new Node(a, paths[i][2]));
        }
        for(int i=0; i<summits.length; i++) mountain_peak[summits[i]] = true;
        for(int i=0; i<gates.length; i++) w_visited[gates[i]] = 0;
        for(int i=0; i<gates.length; i++) DFS(gates[i]);
        //답 체크
        for(int i=0; i<summits.length; i++) {
            if(answer[1] > w_visited[summits[i]]) {
                answer[1] = w_visited[summits[i]];
                answer[0] = summits[i];
            } else if(answer[1] == w_visited[summits[i]]) {
                if(answer[0] > summits[i]) answer[0] = summits[i];
            }
        }
        return answer;
    }
    static void DFS(int a) {
        if(mountain_peak[a] == true) {
            min_intensity = Math.min(min_intensity, w_visited[a]);
            return;
        };
        for(int i=0; i<graph.get(a).size(); i++) {
            Node next_node = graph.get(a).get(i);
            int max_intensity = w_visited[a] >= next_node.w ? w_visited[a] : next_node.w;
            if(w_visited[next_node.b] > max_intensity && min_intensity >= max_intensity) {
                w_visited[next_node.b] = max_intensity;
                DFS(next_node.b);
            }
        }
        
    }
}