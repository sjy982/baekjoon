import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static ArrayList<ArrayList<Integer>> graph_list = new ArrayList<>();
    static boolean visited[];
    static int cout = 0;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];
        for(int i=0; i<=N; i++) graph_list.add(new ArrayList<Integer>());
        M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph_list.get(a).add(b);
            graph_list.get(b).add(a);
        }
        visited[1] = true;
        DFS(1);
        System.out.println(cout);
    }
    
    static void DFS(int n) {
        for(int i=0; i<graph_list.get(n).size(); i++) {
            int next_node = graph_list.get(n).get(i);
            if(!visited[next_node]) {
                cout += 1;
                visited[next_node] = true;
                DFS(next_node);
            }
        }
    }
}