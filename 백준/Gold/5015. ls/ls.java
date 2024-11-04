import java.io.*;
import java.util.*;

public class Main {
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String pt = br.readLine();
      int N = Integer.parseInt(br.readLine());
      String[] file = new String[N];
      for(int i=0; i<N; i++) {
          file[i] = br.readLine();
      }
      
      StringBuilder sb = new StringBuilder();
      for(int i=0; i<N; i++) {
          int[][] dp = new int[pt.length() + 1][file[i].length() + 1]; //0은 방문 x, 1은 가능, 2는 불가
          if(answer(pt, file[i], dp, 0, 0) == 1) {
              sb.append(file[i]).append("\n");
          }
      }
      
      System.out.println(sb.toString().trim());
  }
  
  static int answer(String pt, String fileName, int[][] dp, int ptCursor, int fnCursor) {
      if(ptCursor == pt.length()) {
          //마지막 커서는 알파벳 또는 와일드임
          if(pt.charAt(ptCursor - 1) != '*' && fnCursor != fileName.length()) {
              //와일드가 아니라면 fnCursor 또한 fileName.length()를 가르키고 있어야됨 그렇지 않으면 false;
              return 2;
          }
          return 1;
      }
      
      if(dp[ptCursor][fnCursor] != 0) {
          //이미 방문했다면
          return dp[ptCursor][fnCursor];
      }
      
      if(pt.charAt(ptCursor) == '*') {
          dp[ptCursor][fnCursor] = answer(pt, fileName, dp, ptCursor + 1, fnCursor);
          return dp[ptCursor][fnCursor];
      }
      
      if(fnCursor == fileName.length()) {
          //패턴이 일치되지 않은 상태에서 cursor가 넘어가면 일치 x
          return 2;
      }
      
      boolean wild = true;
      if(ptCursor == 0 || pt.charAt(ptCursor - 1) != '*') {
          wild = false;
      }
      
      if(!wild) {
          //앞이 와일드가 아닌경우
          if(pt.charAt(ptCursor) == fileName.charAt(fnCursor)) {
              dp[ptCursor][fnCursor] = answer(pt, fileName, dp, ptCursor + 1, fnCursor + 1);
              return dp[ptCursor][fnCursor];
          }
      } else {
          //앞이 와일드인 경우
          for(int i=fnCursor; i<fileName.length(); i++) {
              if(pt.charAt(ptCursor) == fileName.charAt(i)) {
                  if(answer(pt, fileName, dp, ptCursor + 1, i + 1) == 1) {
                      dp[ptCursor][fnCursor] = 1;
                      return dp[ptCursor][fnCursor];
                  }
              }
          }
      }
      dp[ptCursor][fnCursor] = 2;
      return dp[ptCursor][fnCursor];
  }
}