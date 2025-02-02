import java.io.*;
import java.util.*;

class State {
    int S;
    String time;
    State(int S, String time) {
        this.S = S;
        this.time = time;
    }
}

public class Main {
    static int N,T;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      T = Integer.parseInt(st.nextToken());
      int[][] subjects = new int[N + 1][T + 1];
      for(int i=1; i<=N; i++) {
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          for(int j=0; j<=T; j++) {
              subjects[i][j] = Integer.parseInt(st2.nextToken());
          }
      }
      int[] D = new int[T + 1];
      StringTokenizer st2 = new StringTokenizer(br.readLine());
      for(int i=0; i<=T; i++) {
          D[i] = Integer.parseInt(st2.nextToken());
      }
      State[][] dp = new State[N + 1][T + 1];
      for(int i=0; i<=T; i++) {
          dp[1][i] = new State(subjects[1][i], String.valueOf(i));
      }
      
      for(int i=2; i<=N; i++) {
          for(int j=0; j<=T; j++) {
              for(int k=0; k<=j; k++) {
                  int leftT = j - k; //현재 과목을 공부하고 남은 시간
                  int newS = (dp[i - 1][leftT].S + subjects[i][k]);
                  if(dp[i][j] == null || dp[i][j].S < newS) {
                      //newS가 더 크다면
                      dp[i][j] = new State(newS, dp[i -1][leftT].time + " " + k);
                  }
              }
          }
      }
      
      State answer = new State(0 - D[0], " ");
      for(int i=1; i<=N; i++) {
          for(int j=1; j<=T; j++) {
              int score = dp[i][j].S - D[j];
              if(answer.S < score) {
                  answer = new State(score, dp[i][j].time);
              }
          }
      }
      StringBuilder sb = new StringBuilder();
      sb.append(answer.S).append("\n");
      
      String[] split = answer.time.split(" ");
      for(int i=0; i<N; i++) {
          if(i < split.length) {
              sb.append(split[i]).append(" ");
          } else {
              sb.append(0).append(" ");
          }
      }
      System.out.println(sb.toString().trim());
  }
}