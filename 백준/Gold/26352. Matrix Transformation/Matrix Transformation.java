import java.io.*;
import java.util.*;

public class Main {
    static int n;
  public static void main(String args[]) throws IOException {
      BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
      n = Integer.parseInt(br.readLine());
      StringBuilder sb = new StringBuilder();
      for(int t=1; t<=n; t++) {
          sb.append("Case #" + t + ": ");
          StringTokenizer st = new StringTokenizer(br.readLine());
          int r = Integer.parseInt(st.nextToken());
          int c = Integer.parseInt(st.nextToken());
          
          int[][] matrix = new int[r][c];
          
          for(int i=0; i<r; i++) {
              StringTokenizer st2 = new StringTokenizer(br.readLine());
              for(int j=0; j<c; j++) {
                  matrix[i][j] = Integer.parseInt(st2.nextToken());
              }
          }
          
          sb.append(answer(r, c, matrix)).append("\n").append("\n");
      }
      System.out.println(sb.toString().trim());
  }
  
  static String answer(int r, int c, int[][] matrix) {
      String result = "YES";
      
      for(int i=0; i<r; i++) {
          for(int j=0; j<c; j++) {
              if(i == r - 1 && j == c - 1) {
                  break;
              }
              int v = -1 * matrix[i][j];
              if(j == c - 1) {
                  matrix[i + 1][j] += v;
              } else {
                  matrix[i][j + 1] += v;
              }
          }
      }
      
      if(matrix[r - 1][c - 1] != 0) {
          result = "NO";
      }
      return result;
  }
}