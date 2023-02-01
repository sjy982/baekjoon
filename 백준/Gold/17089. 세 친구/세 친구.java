import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static ArrayList<ArrayList<Integer>> graph_list = new ArrayList<>();
    static Stack<Integer> result = new Stack<>();
    static ArrayList<Integer> ans = new ArrayList<>();
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      for(int i=0; i<=N; i++) graph_list.add(new ArrayList<>());
      for(int i=0; i<M; i++) {
          StringTokenizer n_st = new StringTokenizer(br.readLine());
          int a = Integer.parseInt(n_st.nextToken());
          int b = Integer.parseInt(n_st.nextToken());
          graph_list.get(a).add(b);
          graph_list.get(b).add(a);
      }
      DFS();
      if(ans.size()==0) System.out.println(-1);
      else {
          System.out.println(Collections.min(ans));
      }
    }
    
    static void DFS() {
        if(result.size()==3) {
            if(graph_list.get(result.get(0)).contains(result.get(2))) {
                // A,C가 친구라면 A, B, C 모두 친구
                ans.add(graph_list.get(result.get(0)).size() + graph_list.get(result.get(1)).size() + graph_list.get(result.get(2)).size()-6);
            }
            return;
        }
        if(result.size()==0) {
            for(int i=1; i<=N; i++) {
                result.push(i);
                DFS();
                result.pop();
            }
        } else {
            int top = result.peek();
            for(int i=0; i<graph_list.get(top).size(); i++) {
                int n = graph_list.get(top).get(i);
                if(top<n) {
                    result.push(n);
                    DFS();
                    result.pop();
                }
            }
        }
    }
}