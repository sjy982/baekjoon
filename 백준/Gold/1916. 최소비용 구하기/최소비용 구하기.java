import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int v, w;
    Node(int v, int w) {
        this.v = v;
        this.w = w;
    }
    
    @Override
    public int compareTo(Node o) {
        if(this.w < o.w) {
            return -1;
        } else if(this.w > o.w) {
            return 1;
        }
        return 0;
    }
}

public class Main {
    static int N, M;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      M = Integer.parseInt(br.readLine());
      int[] dp = new int[N + 1]; //비용
      ArrayList<ArrayList<Node>> graph = new ArrayList<>();
      for(int i=0; i<=N; i++) {
          graph.add(new ArrayList<>());
          dp[i] = Integer.MAX_VALUE;
      }
      
      for(int i=0; i<M; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          int a = Integer.parseInt(st.nextToken());
          int b = Integer.parseInt(st.nextToken());
          int w = Integer.parseInt(st.nextToken());
          
          graph.get(a).add(new Node(b, w));
      }
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());
      
      dijkstra(s, graph, dp);
      
      System.out.println(dp[e]);
  }
  
  static void dijkstra(int s, ArrayList<ArrayList<Node>> graph, int[] dp) {
      PriorityQueue<Node> pq = new PriorityQueue<>();
      pq.add(new Node(s, 0));
      dp[s] = 0;
      
      while(!pq.isEmpty()) {
          Node n = pq.poll();
          if(n.w > dp[n.v]) continue; //최단 거리가 아닌 경우 컨티뉴 (방문 처리가 같은 느낌)
          //최단 거리라면
          for(int i=0; i<graph.get(n.v).size(); i++) {
              Node nextNode = graph.get(n.v).get(i);
              int nextDistance = dp[n.v] + nextNode.w;
              
              if(nextDistance < dp[nextNode.v]) {
                  dp[nextNode.v] = nextDistance;
                  pq.add(new Node(nextNode.v, nextDistance));
              }
          }
      }
  }
}