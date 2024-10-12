import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int v, t;
    Node(int v, int t) {
        this.v = v;
        this.t = t;
    }
    
    @Override
    public int compareTo(Node o) {
        if(this.t < o.t) {
            return -1;
        } else if(this.t > o.t) {
            return 1;
        }
        return 0;
    }
}

public class Main {
    static int N, M, X;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      X = Integer.parseInt(st.nextToken());
      
      ArrayList<ArrayList<Node>> graph = new ArrayList<>();
      for(int i=0; i<=N; i++) {
          graph.add(new ArrayList<>());
      }
      
      for(int i=0; i<M; i++) {
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          int a = Integer.parseInt(st2.nextToken());
          int b = Integer.parseInt(st2.nextToken());
          int t = Integer.parseInt(st2.nextToken());
          graph.get(a).add(new Node(b, t));
      }
      
      int answer = -1;
      for(int i=1; i<=N; i++) {
          answer = Math.max(answer, dijkstra(i, X, graph) + dijkstra(X, i, graph));
      }
      
      System.out.println(answer);
  }
  
  static int dijkstra(int s, int e, ArrayList<ArrayList<Node>> graph) {
      PriorityQueue<Node> pque = new PriorityQueue<>();
      int[] dp = new int[N + 1];
      for(int i=1; i<=N; i++) {
          dp[i] = Integer.MAX_VALUE;
      }
      dp[s] = 0;
      pque.add(new Node(s, 0));
      while(!pque.isEmpty()) {
          Node n = pque.poll();
          
          if(dp[n.v] < n.t) {
              continue;
          }
          
          if(n.v == e) {
              return dp[n.v];
          }
          
          for(int i=0; i<graph.get(n.v).size(); i++) {
              Node b = graph.get(n.v).get(i);
              int nextDistance = dp[n.v] + b.t;
              if(dp[b.v] > nextDistance) {
                  dp[b.v] = nextDistance;
                  pque.add(new Node(b.v, nextDistance));
              }
          }
      }
      
      return dp[e];
      
  } 
}