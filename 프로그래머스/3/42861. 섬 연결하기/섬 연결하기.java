import java.io.*;
import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i=0; i<n; i++) {
            graph.add(new ArrayList<>());
        }
        int answer = 0;
        for(int i=0; i<costs.length; i++) {
            int a = costs[i][0];
            int b = costs[i][1];
            graph.get(a).add(b);
            graph.get(b).add(a);
            answer += costs[i][2];
        }
        
        Arrays.sort(costs, new Comparator<int[]>() {
           @Override
            public int compare(int[] a, int[] b) {
                if(a[2] < b[2]) {
                    return 1;
                } else if(a[2] > b[2]) {
                    return -1;
                }
                return 0;
            }
        });
        
        for(int i=0; i<costs.length; i++) {
            int a = costs[i][0];
            int b = costs[i][1];
            graph.get(a).remove(Integer.valueOf(b)); //값을 삭제하는 것임.
            graph.get(b).remove(Integer.valueOf(a));
            
            boolean[] visited = new boolean[n];
            dfs(0, graph, visited);
            
            boolean isPosible = true;
            for(int j=0; j<n; j++) {
                if(!visited[j]) {
                    isPosible = false;
                    break;
                }
            }
            
            if(isPosible) {
                answer -= costs[i][2];
            } else {
                //불가능하면 다시 넣어준다. 유일한 경로이기 때문에.
                graph.get(a).add(b);
                graph.get(b).add(a);
            }
        }
        
        return answer;
    }
    
    public void dfs(int a, ArrayList<ArrayList<Integer>> graph, boolean[] visited) {
        visited[a] = true;
        for(int i=0; i<graph.get(a).size(); i++) {
            int b = graph.get(a).get(i);
            if(!visited[b]) {
                //방문하지 않았다면 다음 방문.
                dfs(b, graph, visited);
            }
        }
    }
}