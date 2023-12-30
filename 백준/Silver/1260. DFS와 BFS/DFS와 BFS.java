import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    static int N, M, V;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer setSt = new StringTokenizer(br.readLine());
        N = Integer.parseInt(setSt.nextToken());
        M = Integer.parseInt(setSt.nextToken());
        V = Integer.parseInt(setSt.nextToken());
        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        
        for(int i=1; i<=N; i++) {
            Collections.sort(graph[i]);
        }
        dfs(V);
        sb.append("\n");
        visited = new boolean[N + 1];
        bfs();
        System.out.println(sb.toString());
    }
    
    static void dfs(int n) {
        sb.append(n).append(" ");
        visited[n] = true;
        for(int i=0; i<graph[n].size(); i++) {
            int next = graph[n].get(i);
            if(!visited[next]) {
                dfs(next);
            }
        }
    }
    
    static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(V);
        visited[V] = true;
        while(queue.size() != 0) {
            int n = queue.poll();
            sb.append(n).append(" ");
            for(int i=0; i<graph[n].size(); i++) {
                int next = graph[n].get(i);
                if(!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }
    }
}