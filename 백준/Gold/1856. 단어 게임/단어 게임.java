import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = Integer.MAX_VALUE;
    static int w, l;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      w = Integer.parseInt(st.nextToken());
      l = Integer.parseInt(st.nextToken());
      String S = " " + br.readLine(); // 1 ~ l까지`
      int[][] dp = new int[l + 1][2]; // 1은 이미 진행했음을 의미
      init(dp);
      dp[0][0] = 0;
      
      String[] dictionary = new String[w];
      for(int i=0; i<w; i++) {
          dictionary[i] = br.readLine();
      }
      
      boolean end = false;
      while(!end){
          if(!start(S, dictionary, dp)) {
              end = true;
          }
      }
      
      int answer = MAX;
      for(int i=1; i<=l; i++) {
          if(dp[i][0] != MAX) {
              answer = Math.min(answer, dp[i][0] + (l - i));
          }
      }
      System.out.println(answer);
  }
  
  static boolean start(String S, String[] dictionary, int[][] dp) {
      boolean isNext = false;
      for(int i=0; i<l; i++) {
          if(dp[i][0] != MAX && dp[i][1] != 1) {
              for(int j=0; j<w; j++) {
                  int cnt = 0;
                  //사전의 단어를 하나씩 매칭시켜 본다.
                  int stdCursor = 0;
                  String std = dictionary[j];
                  for(int k=i + 1; k<=l; k++) {
                      if(std.charAt(stdCursor) == S.charAt(k)) {
                          stdCursor += 1;
                          if(stdCursor == std.length()) {
                              //매칭 완료
                              if(dp[i][0] + cnt < dp[k][0]) {
                                  dp[k][0] =  dp[i][0] + cnt;
                                  dp[k][1] = 0;
                                  isNext = true;
                              }
                              break;
                          }
                      } else {
                          cnt += 1; //제거
                      }
                  }
              }
              dp[i][1] = 1;
          }
      }
      return isNext;
  }
  
  static void init(int[][] dp) {
      for(int i=0; i<dp.length; i++) {
          dp[i][0] = MAX;
      }
  }
}