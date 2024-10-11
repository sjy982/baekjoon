import java.io.*;
import java.util.*;

public class Main {
    static int T;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      T = Integer.parseInt(br.readLine());
      StringBuilder sb = new StringBuilder();
      for(int t=0; t<T; t++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          int N = Integer.parseInt(st.nextToken());
          int K = Integer.parseInt(st.nextToken());
          int[] time = new int[N + 1];
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          for(int i=1; i<=N; i++) {
              time[i] = Integer.parseInt(st2.nextToken());
          }
          
          int[][] ruleCnt = new int[N + 1][2]; //0 -> 현재 만족하고 있는 규칙 수 , 1 -> 건설하는데 만족해야될 규칙 수
          
          ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
          for(int i=0; i<=N; i++) {
              graph.add(new ArrayList<>());
          }
          
          boolean[] visited = new boolean[N + 1];
          for(int i=0; i<K; i++) {
              StringTokenizer st3 = new StringTokenizer(br.readLine());
              int a = Integer.parseInt(st3.nextToken());
              int b = Integer.parseInt(st3.nextToken());
              ruleCnt[b][1] += 1;
              graph.get(a).add(b);
              if(!visited[b]) {
                  visited[b] = true;
              }
          }
          
          ArrayList<Integer> startList = new ArrayList<>();
          for(int i=1; i<=N; i++) {
              if(!visited[i]) {
                  startList.add(i);
              }
          }
          
          int[] dp = new int[N + 1]; // [x] -> x를 건설하는 데 걸리는 시간.
          
          //탐색 시작
          for(int i=0; i<startList.size(); i++) {
              int a = startList.get(i);
              dp[a] = time[a];
              dfs(a, graph, time, ruleCnt, dp);
          }
          
          int w = Integer.parseInt(br.readLine());
          sb.append(dp[w]).append("\n");
      }
      
      System.out.println(sb.toString().trim());
  }
  
  static void dfs(int a, ArrayList<ArrayList<Integer>> graph, int[] time, int[][] ruleCnt, int[] dp) {
      for(int i=0; i<graph.get(a).size(); i++) {
          int b = graph.get(a).get(i);
          dp[b] = Math.max(dp[b], dp[a] + time[b]);
          ruleCnt[b][0] += 1;
          if(ruleCnt[b][0] == ruleCnt[b][1]) {
              dfs(b, graph, time, ruleCnt, dp);
          }
      }
  }
}