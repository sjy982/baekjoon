import java.io.*;
import java.util.*;

public class Main {
    static int N, A, B;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      A = Integer.parseInt(st.nextToken());
      B = Integer.parseInt(st.nextToken());
      
      if(A == B) {
          System.out.println(0);
          return;
      }
      
      ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
      for(int i=0; i<=N; i++) {
          graph.add(new ArrayList<>());
      }
      HashMap<String, Integer> wMap = new HashMap<>();
      for(int i=0; i<N-1; i++) {
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          int a = Integer.parseInt(st2.nextToken());
          int b = Integer.parseInt(st2.nextToken());
          int w = Integer.parseInt(st2.nextToken());
          graph.get(a).add(b);
          graph.get(b).add(a);
          
          wMap.put(a + " " + b, w); // a b => w
          wMap.put(b + " " + a, w); // b a => w
      }
      
      int[] aDistance = new int[N + 1];
      int[] bDistance = new int[N + 1];
      for(int i=1; i<=N; i++) {
          aDistance[i] = -1;
          bDistance[i] = -1;
      }
      
      aDistance[A] = 0;
      bDistance[B] = 0;
      dfs(A, aDistance, graph, wMap);
      dfs(B, bDistance, graph, wMap);
      
      int min = Integer.MAX_VALUE;
      for(Map.Entry<String, Integer> entry : wMap.entrySet()) {
          StringTokenizer keyTokens = new StringTokenizer(entry.getKey(), " ");
          int a = Integer.parseInt(keyTokens.nextToken());
          int b = Integer.parseInt(keyTokens.nextToken());
          min = Math.min(min, aDistance[a] + bDistance[b]);
      }
      
      System.out.println(min);
      
  }
  
  static void dfs(int v, int[] distance, ArrayList<ArrayList<Integer>> graph, HashMap<String, Integer> wMap) {
      for(int i=0; i<graph.get(v).size(); i++) {
          int nextV = graph.get(v).get(i);
          if(distance[nextV] == -1) {
              //방문하지 않았다면
              distance[nextV] = distance[v] + wMap.get(v + " " + nextV);
              dfs(nextV, distance, graph, wMap);
          }
      }
  }
}