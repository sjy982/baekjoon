import java.io.*;
import java.util.*;

class Solution {
    static int startNode;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static int maxNode;
    static int graphCase = 2;
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        maxNode = findMaxNode(edges);
        visited = new boolean[maxNode + 1];
        for(int i=0; i<=maxNode; i++) {
            graph.add(new ArrayList<>());
        }
        answer[0] = startNode;
        makeGraph(edges);
        dfs(startNode, answer);
        return answer;
    }
    
    static void dfs(int a, int[] answer) {
        if(a != startNode && graph.get(a).size() == 2) {
            graphCase = 3;
            return;
        }
        for(int i=0; i<graph.get(a).size(); i++) {
            int b = graph.get(a).get(i);
            if(visited[b]) {
                if(graph.get(b).size() == 1) {
                    graphCase = 1;
                } else {
                    graphCase = 3;
                }
                return;
            }
            visited[b] = true;
            dfs(b, answer);
            if(a == startNode) {
                answer[graphCase] += 1;
                graphCase = 2;
            }
        }
    }
    
    static void makeGraph(int[][] edges) {
        for(int i=0; i<edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            graph.get(a).add(b);
        }
    }
    
    static int findMaxNode(int[][] edges) {
        int max = -1;
        int[] bVisited = new int[1000001];
        int[] aVisited = new int[1000001];
        for(int i=0; i<edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            max = Math.max(max, Math.max(a, b));
            aVisited[a] += 1;
            bVisited[b] += 1;
        }
        
        for(int i=1; i<=max; i++) {
            if(bVisited[i] == 0 && aVisited[i] >= 2) {
                startNode = i;
                break;
            }
        }
        return max;
    }
}