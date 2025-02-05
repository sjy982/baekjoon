import java.io.*;
import java.util.*;

class State {
    int f, v;
    State before;
    State(int f, int v, State before) {
        this.f = f;
        this.v = v;
        this.before = before;
    }
}

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(br.readLine());
      State[][][] dp = new State[141][141][N + 1];
      dp[findIndex(st.nextToken())][findIndex(st.nextToken())][0] = new State(0, 0, null); //f는 1, 2;
      
      int[][] ebCnt = new int[141][141];
      
      for(int i=1; i<=140; i++) {
          int before = 0;
          for(int j=i + 1; j<=140; j++) {
              if(find(j) == 7 || find(j) == 1) {
                  before += 1;
              }
              ebCnt[i][j] = before;
          }
      }
      
      for(int i=140; i>=1; i--) {
          int before = 0;
          for(int j=i - 1; j>=1; j--) {
              if(find(j) == 0 || find(j) == 6) {
                  before += 1;
              }
              ebCnt[i][j] = before;
          }
      }
     
     int[] sheetMusic = new int[N + 1];
     StringTokenizer st2 = new StringTokenizer(br.readLine());
     for(int i=1; i<=N; i++) {
         sheetMusic[i] = findIndex(st2.nextToken());
     }
     
     for(int i=1; i<=N; i++) {
         
         for(int l=1; l<=140; l++) {
             for(int r=1; r<=140; r++) {
                 if(dp[l][r][i - 1] != null) {
                     //존재한다면
                     
                     //왼손에서 -> sheetMusic[i]까지
                     int nextV = Math.abs(l - sheetMusic[i]) - ebCnt[l][sheetMusic[i]] + dp[l][r][i - 1].v;
                     if(dp[sheetMusic[i]][r][i] == null || (nextV < dp[sheetMusic[i]][r][i].v)) {
                         dp[sheetMusic[i]][r][i] = new State(1, nextV, dp[l][r][i - 1]);
                     }
                   
                     //오른손에서 -> sheetMusic[i]까지
                     int nextVr = Math.abs(r - sheetMusic[i]) - ebCnt[r][sheetMusic[i]] + dp[l][r][i - 1].v;
                     if(dp[l][sheetMusic[i]][i] == null || (nextVr < dp[l][sheetMusic[i]][i].v)) {
                         dp[l][sheetMusic[i]][i] = new State(2, nextVr, dp[l][r][i - 1]);
                     }
                 }
             }
         }
     }
     
     State answer = new State(-1, Integer.MAX_VALUE, null);
     for(int i=1; i<=140; i++) {
         for(int j=1; j<=140; j++) {
             if(dp[i][j][N] != null) {
                 if(dp[i][j][N].v < answer.v) {
                     answer = dp[i][j][N];
                 }
             }
         }
     }
     StringBuilder sb = new StringBuilder();
     sb.append(answer.v).append("\n");
     Stack<Integer> stack = new Stack<>();
     while(answer.before != null) {
         stack.push(answer.f);
         answer = answer.before;
     }
     
     while(!stack.isEmpty()) {
         sb.append(stack.pop()).append(" ");
     }
     
     System.out.println(sb.toString().trim());
  }
  
  static int findIndex(String note) {
      int result = 0;
      if(note.charAt(0) == 'C') {
          result += 1;
      } else if(note.charAt(0) == 'D') {
          result += 3;
      } else if(note.charAt(0) == 'E') {
          result += 5;
      } else if(note.charAt(0) == 'F') {
          result += 7;
      } else if(note.charAt(0) == 'G') {
          result += 9;
      } else if(note.charAt(0) == 'A') {
          result += 11;
      } else if(note.charAt(0) == 'B') {
          result += 13;
      }
      
      result += 14 * (note.charAt(1) - '0');
      
      if(note.length() == 3) {
          result += 1;
      }
      
      return result;
  }
  
  static int find(int nInd) {
      int v = nInd % 14;
      return v;
  }
}