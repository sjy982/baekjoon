import java.io.*;
import java.util.*;

public class Main {
    static HashMap<Character, Integer> map = new HashMap<>();
    static boolean answer = false;
    static ArrayList<Character> alpList = new ArrayList<>();
    static ArrayList<Integer> result = new ArrayList<>();
    static boolean[] visited = new boolean[10];
    static boolean[] alpVisited = new boolean[26];
    static String fst, sec, ans;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      fst = st.nextToken();
      sec = st.nextToken();
      ans = st.nextToken();
      
      makeAlpList(fst);
      makeAlpList(sec);
      makeAlpList(ans);
      
      if(alpList.size() > 10) {
          System.out.println("NO");
          return;
      }
      
      dfs(0, alpList.size());
      
      if(answer) {
          System.out.println("YES");
      } else {
          System.out.println("NO");
      }
  }
  
  static void dfs(int depth, int maxDepth) {
      if(answer) {
          return;
      }
      
      if(depth == maxDepth) {
          //여기서 계산
          
          long iFst = trans(fst);
          long iSec = trans(sec);
          long iAns = trans(ans);
          
          if(iFst + iSec == iAns) {
              answer = true;
          }
          return;
      }
      
      for(int i=0; i<=9 && !answer; i++) {
          if(!visited[i]) {
              visited[i] = true;
              map.put(alpList.get(depth), i);
              dfs(depth + 1, maxDepth);
              visited[i] = false;
          }
      }
  }
  
  static long trans(String str) {
      long result = 0L;
      for(int i=0; i<str.length(); i++) {
          result *= 10L;
          result += map.get(str.charAt(i));
        //   result += map.get(str.charAt(i)) * Math.pow(10, (str.length() - 1) - i);
      }
      return result;
  }
  
  static void makeAlpList(String str) {
      for(int i=0; i<str.length(); i++) {
          int ask = (int) str.charAt(i);
          if(!alpVisited[ask - 65]) {
              alpVisited[ask - 65] = true;
              alpList.add(str.charAt(i));
          }
      }
  }
}