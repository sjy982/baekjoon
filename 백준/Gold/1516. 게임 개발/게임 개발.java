import java.io.*;
import java.util.*;

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      
      int[] bTime = new int[N + 1]; //빌드 타임
      ArrayList<Integer> startList = new ArrayList<>(); //규칙 없이 지을 수 있는 건물
      ArrayList<ArrayList<Integer>> graph = new ArrayList<>(); // 그래프
      for(int i=0; i<=N; i++) {
          graph.add(new ArrayList<>());
      }
      
      int[][] ruleCnt = new int[N + 1][2]; //0은 현재 규칙을 만족하는 수, 1은 만족해야 하는 전체 수
      int[] dp = new int[N + 1];
      
      for(int i=1; i<=N; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          
          bTime[i] = Integer.parseInt(st.nextToken());
          
          int curInput = Integer.parseInt(st.nextToken());
          while(curInput != -1) {
              ruleCnt[i][1] += 1;
              graph.get(curInput).add(i);
              curInput = Integer.parseInt(st.nextToken());
          } 
          
          if(ruleCnt[i][1] == 0) {
              //규칙 없이 지을 수 있음
              startList.add(i);
          }
      }
      
      for(int i=0; i<startList.size(); i++) {
          int a = startList.get(i);
          dp[a] = bTime[a];
          dfs(a, graph, bTime, ruleCnt, dp);
      }
      
      StringBuilder sb = new StringBuilder();
      for(int i=1; i<=N; i++) {
          sb.append(dp[i]).append("\n");
      }
      System.out.println(sb.toString().trim());
  }
  
  static void dfs(int a, ArrayList<ArrayList<Integer>> graph, int[] bTime, int[][] ruleCnt, int[] dp) {
      for(int i=0; i<graph.get(a).size(); i++) {
          int b = graph.get(a).get(i);
          dp[b] = Math.max(dp[b], dp[a] + bTime[b]);
          ruleCnt[b][0] += 1;
          if(ruleCnt[b][0] == ruleCnt[b][1]) {
              //규칙을 만족한다면 탐색
              dfs(b, graph, bTime, ruleCnt, dp);
          }
      }
  }
}