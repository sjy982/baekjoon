import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      
      int[][] map = new int[N][N];
      for(int i=0; i<N; i++) {
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          for(int j=0; j<N; j++) {
              map[i][j] = Integer.parseInt(st2.nextToken());
          }
      }
      
      long[][] prefix = new long[N][N];
      
      long[][] answer = new long[N][N];
      for(int i=0; i<N; i++) {
          for(int j=0; j<N; j++) {
              if(j != 0) {
                  prefix[i][j] += prefix[i][j - 1];
              }
              
              int uv = 0; //바로 위쪽 값
              if(i != 0) {
                  uv = map[i - 1][j];
              }
              long need = map[i][j] - (uv + prefix[i][j]);
              if(need < 0) {
                  answer[i + (M/2)][j + (M/2)]  = Math.abs(need);
                  
                  prefix[i][j] += need;
                  
                  if(j + M < N) {
                      prefix[i][j + M] -= need;
                  }
                  
                  if(i + M < N) {
                      prefix[i + M][j] -= need;
                  }
                  
                  if((i + M < N) && (j + M < N)) {
                      prefix[i + M][j + M] += need;
                  }
              }
          }
      }
      
      StringBuilder sb = new StringBuilder();
      for(int i=0; i<N; i++) {
          for(int j=0; j<N; j++) {
              sb.append(answer[i][j]);
              if(j != (N - 1)) {
                  sb.append(" ");
              }
          }
          sb.append("\n");
      }
      System.out.println(sb.toString().trim());
  }
}