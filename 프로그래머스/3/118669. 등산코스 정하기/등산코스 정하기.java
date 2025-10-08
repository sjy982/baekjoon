import java.util.*;

class Node implements Comparable<Node> {
    int n, v;
    Node(int n, int v) {
        this.n = n;
        this.v = v;
    }
    
    @Override
    public int compareTo(Node o) {
        if(this.v < o.v) {
            return -1;
        } else if(this.v > o.v) {
            return 1;
        } else {
            if(this.n < o.n) {
                return -1;
            } else if(this.n > o.n) {
                return 1;
            }
        }
        return 0;
    }
}

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<paths.length; i++) {
            graph.get(paths[i][0]).add(new Node(paths[i][1], paths[i][2]));
            graph.get(paths[i][1]).add(new Node(paths[i][0], paths[i][2]));
        }
        
        boolean[] smts = new boolean[n + 1];
        for(int i=0; i<summits.length; i++) {
            smts[summits[i]] = true;
        }
        
        int[] answer = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i=0; i<gates.length; i++) {
            visited[gates[i]] = true;
            pq.add(new Node(gates[i], 0));
        }
        
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            visited[node.n] = true;
            
            if(node.v > answer[1]) {
                continue;
            }
            if(smts[node.n]) {
                if(answer[1] > node.v) {
                    answer[1] = node.v;
                    answer[0] = node.n;
                } else if(answer[1] == node.v) {
                    if(answer[0] > node.n) {
                        answer[0] = node.n;
                    }
                }
                continue;
            }
            
            for(int i=0; i<graph.get(node.n).size(); i++) {
                Node b = graph.get(node.n).get(i);
                if(visited[b.n]) {
                    continue;
                }
                int nextV = node.v;
                if(node.v < b.v) {
                    nextV = b.v;
                }
                if(nextV > answer[1]) {
                    continue;
                }
                pq.add(new Node(b.n, nextV));
            }
        }
        return answer;
    }
}