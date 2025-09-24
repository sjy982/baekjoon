import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int answer = 0;
    static ArrayList<Integer> result;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      
      //a, n, t, i, c
      if(K < 5) {
          //반드시 배워야 하는 글자 수를 충족시키지 못함
          System.out.println(0);
          return;
      }
      K -= 5;
      result = new ArrayList<>();
      String[] words = new String[N];
      for(int i=0; i<N; i++) {
          String str = br.readLine();
          words[i] = str.substring(4, str.length() - 4);
      }
      
      boolean[] visited = new boolean[26];
      visited[findInd('a')] = true;
      visited[findInd('n')] = true;
      visited[findInd('t')] = true;
      visited[findInd('i')] = true;
      visited[findInd('c')] = true;
      
      dfs(0, 0, visited, words);
      
      System.out.println(answer);
  }
  
  static void dfs(int ind, int length, boolean[] visited, String[] words) {
      if(length == K) {
          //글자 다 배웠을 때
          int cnt = 0;
          for(int i=0; i<words.length; i++) {
              if(words[i] == "") {
                  cnt += 1;
                  continue;
              }
              
              boolean isPosible = true;
              for(int j=0; j<words[i].length(); j++) {
                  int fInd = findInd(words[i].charAt(j));
                  if(!visited[fInd]) {
                      isPosible = false;
                      break;
                  }
              }
              if(isPosible) {
                  cnt += 1;
              }
          }
          answer = Math.max(answer, cnt);
          return;
      }
      
      if(ind == 26) {
          return;
      }
      
      int leftK = K - length;
      int leftInd = 25 - ind + 1;
      if(leftK > leftInd) {
          return;
      }
      
      for(int i=ind; i<=25; i++) {
          if(visited[i]) {
             continue; 
          }
          visited[i] = true;
          dfs(i + 1, length + 1, visited, words);
          visited[i] = false;
      }
  }
  
  static public int findInd(char c) {
      return (c - '0') - 49;
  }
}