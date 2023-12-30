import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int N, M;
    static int answer = -1;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        
        for(int i = 1; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            graph[a].add(b);
            graph[b].add(a);
        }
        dfs(1);
        System.out.println(answer);
    }
    
    static void dfs(int node) {
        visited[node] = true;
        for(int i=0; i<graph[node].size(); i++) {
            int next = graph[node].get(i);
            if(!visited[next]) {
                dfs(next);
            }
        }
        answer += 1;
    }
}