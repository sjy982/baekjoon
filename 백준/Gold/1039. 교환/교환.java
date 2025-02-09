import java.io.*;
import java.util.*;

public class Main {
    static int answer = -1;
    static int N, K, maxDigit;
    static boolean[][] visited;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      maxDigit = getIntLength(N);
      visited = new boolean[1000001][K + 1];
      dfs(N, 0);
      
      System.out.println(answer);
  }
  
  static void dfs(int number, int depth) {
      if(visited[number][depth]) {
          return;
      }
      
      if(getIntLength(number) != maxDigit) {
          //첫 번째가 0이 됐음을 의미
          return;
      }
      
      if(depth == K) {
          answer = Math.max(answer, number);
          return;
      }
      
      visited[number][depth] = true;
      
      for(int i=1; i<=maxDigit - 1; i++) {
          for(int j=i + 1; j<=maxDigit; j++) {
              //i와 j를 스왑
              int iD = getDigitByMath(number, i);
              int jD = getDigitByMath(number, j);
              
              int subValue = (int) Math.pow(10, i - 1) * iD + (int) Math.pow(10, j - 1) * jD;
              int addValue = (int) Math.pow(10, j - 1) * iD + (int) Math.pow(10, i - 1) * jD;
              
              int newNumber = (number - subValue) + addValue;
              
              dfs(newNumber, depth + 1);
          }
      }
  }
  
  static int getDigitByMath(int number, int position) {
      //포지션은 오른쪽부터 1임.
      if(getIntLength(number) < position) {
          return -1;
      }
      return (number / (int) Math.pow(10, position - 1)) % 10;
  }
  
  static int getIntLength(int number) {
      return (int) (Math.log10(number) + 1);
  }
}