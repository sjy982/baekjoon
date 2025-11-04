import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<ArrayList<Integer>> graph;
    static int[][] dp;
    static int[] town;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      graph = new ArrayList<>();
      dp = new int[N + 1][2]; //0은 우수마을 선정, 1은 우수마을 선정 x
      town = new int[N + 1];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int i=1; i<=N; i++) {
          town[i] = Integer.parseInt(st.nextToken());
      }
      for(int i=0; i<=N; i++) {
          graph.add(new ArrayList<>());
      }
      for(int i=0; i<N - 1; i++) {
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          int a = Integer.parseInt(st2.nextToken());
          int b = Integer.parseInt(st2.nextToken());
          graph.get(a).add(b);
          graph.get(b).add(a);
      }
      
      dfs(0, 1);
      System.out.println(Math.max(dp[1][0], dp[1][1]));
  }
  
  static void dfs(int beforeNode, int curNode) {
      dp[curNode][0] = town[curNode];
      for(int i=0; i<graph.get(curNode).size(); i++) {
          int nextNode = graph.get(curNode).get(i);
          if(beforeNode == nextNode) {
              continue;
          }
          dfs(curNode, nextNode);
          
          dp[curNode][0] += dp[nextNode][1];
          dp[curNode][1] += Math.max(dp[nextNode][0], dp[nextNode][1]);
      }
  }
}