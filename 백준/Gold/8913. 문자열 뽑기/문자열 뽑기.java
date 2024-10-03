import java.io.*;
import java.util.*;

public class Main {
    static HashMap<String, Boolean> visitedMap;
    static int T;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      T = Integer.parseInt(br.readLine());
      StringBuilder sb = new StringBuilder();
      for(int t=0; t<T; t++) {
          String input = br.readLine();
          visitedMap = new HashMap<>();
          
          if(dfs(input)) {
              sb.append(1);
          } else {
              sb.append(0);
          }
          
          sb.append("\n");
      }
      
      System.out.println(sb.toString().trim());
  }
  
  static boolean dfs(String str) {
      visitedMap.put(str, true);
      if(str.length() == 0) {
          return true;
      }
      
      //연속된 문자를 찾는다.
      int fInd = 0; //문자열의 첫 번째 인덱스
      int cnt = 1; //개수
      for(int i=1; i<str.length(); i++) {
          if(str.charAt(fInd) == str.charAt(i)) {
              cnt += 1;
          }
          
          if((str.charAt(fInd) != str.charAt(i)) || (i == str.length() - 1)) {
              //다른 경우 or 마지막인 경우
              if(cnt > 1) {
                  //연속된 문자를 찾음
                  String nextStr = str.substring(0, fInd) + str.substring(fInd + cnt, str.length()); 
                  if(visitedMap.get(nextStr) == null) {
                      if(dfs(nextStr)) {
                          return true;
                      }
                  }
              }
              fInd = i;
              cnt = 1;
          }
      }
      return false;
  }
}