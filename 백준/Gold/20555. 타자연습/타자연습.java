import java.io.*;
import java.util.*;

public class Main {
    static final char[] dw = {'A', 'B', 'C', 'D'};
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      String[] words = new String[N];
      for(int i=0; i<N; i++) {
          words[i] = " " + br.readLine();
      }
      
      String[][][][][][] dp = new String[10][10][10][10][10][10]; //[현재 cursor의 위치] cursor는 마지막으로 채워진 위치를 기반
      dp[0][0][0][0][0][0] = "";
      for(int on=0; on<=9; on++) {
          for(int tw=0; tw<=9; tw++) {
              for(int th=0; th<=9; th++) {
                  for(int fo=0; fo<=9; fo++) {
                      for(int fi=0; fi<=9; fi++) {
                          for(int si=0; si<=9; si++) {
                              String str = dp[on][tw][th][fo][fi][si];
                              if(str != null) {
                                  //값이 있다면
                                  int[] indArr = {on, tw, th, fo, fi, si};
                                  fillDp(dp[on][tw][th][fo][fi][si], words, indArr, dp);
                              }
                          }
                      }
                  }
              }
          }
      }
      int[] answerInd = new int[6];
      for(int i=0; i<N; i++) {
          answerInd[i] = words[i].length() - 1;
      }
      
      System.out.println(dp[answerInd[0]][answerInd[1]][answerInd[2]][answerInd[3]][answerInd[4]][answerInd[5]]);
  }
  
  static void fillDp(String str, String[] words, int[] indArr, String[][][][][][] dp) {
      for(int i=0; i<dw.length; i++) {
          int[] newIndArr = {indArr[0], indArr[1], indArr[2], indArr[3], indArr[4], indArr[5]};
          
          //newStr이 어디까지 만족시키는지 검사.
          boolean next = false;
          for(int j=0; j<N; j++) {
              if((indArr[j] < words[j].length() - 1) && (words[j].charAt(indArr[j] + 1) == dw[i])) {
                  //새로 추가된 문자가 다음을 만족한다면
                  if(!next) {
                      next = true;
                  }
                  newIndArr[j] += 1;
              }
          }
          
          if(next) {
              //새로운 경우의 수가 가능하다면
              String newStr = str + dw[i];
              String exiStr = dp[newIndArr[0]][newIndArr[1]][newIndArr[2]][newIndArr[3]][newIndArr[4]][newIndArr[5]];
              if(exiStr == null || (newStr.length() < exiStr.length())) {
                  //null이거나 새로운 문자열의 길이가 더 작다면
                  dp[newIndArr[0]][newIndArr[1]][newIndArr[2]][newIndArr[3]][newIndArr[4]][newIndArr[5]] = newStr;
              } else if(newStr.length() == exiStr.length()) {
                  //길이가 같다면 사전 순으로 앞선 문자열이 들어가야 됨.
                  if(newStr.compareTo(exiStr) < 0) {
                      //newStr이 사전 순으로 앞선다면
                      dp[newIndArr[0]][newIndArr[1]][newIndArr[2]][newIndArr[3]][newIndArr[4]][newIndArr[5]] = newStr;
                  }
              }
          }
      }
  }
}