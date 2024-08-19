import java.io.*;
import java.util.*;

class Char implements Comparable<Char> {
    int ind;
    char c;
    Char(int ind, char c) {
        this.ind = ind;
        this.c = c;
    }
    
    @Override
    public int compareTo(Char o) {
        return Character.compare(this.c, o.c);
    }
}

class Info {
    int ind, cnt;
    Info(int ind, int cnt) {
        this.ind = ind;
        this.cnt = cnt;
    }
}

public class Main {
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String S = br.readLine();
      boolean[] visited = new boolean[S.length()]; 
      if(!posible(S, visited)) {
          System.out.println(-1);
          return;
      } else {
          Char[] chars = new Char[S.length()];
          for(int i=0; i<S.length(); i++) {
              chars[i] = new Char(i, S.charAt(i));
          }
          Arrays.sort(chars);
          char[] answer = new char[S.length()];
          if(S.length() % 2 == 1) {
              Info result = findMaxChar(S);
              if(S.length()/2 + 1 == result.cnt) {
                  answer[S.length()/2] = S.charAt(result.ind);
                  visited[result.ind] = true;
              } 
          }
          for(int i=0; i<S.length()/2; i++) {
                  int fCursor = 0;
                  while(visited[chars[fCursor].ind]) {
                      fCursor += 1;
                  }
                  answer[i] = chars[fCursor].c;
                  visited[chars[fCursor].ind] = true;
                  
                  int bCursor = S.length() - 1;
                  while(true) {
                      if(!visited[chars[bCursor].ind]) {
                          visited[chars[bCursor].ind] = true;
                          if(posible(S, visited)) {
                              answer[S.length() - 1 - i] = chars[bCursor].c;
                              break;
                          } else {
                              visited[chars[bCursor].ind] = false;
                          }
                      }
                      bCursor -= 1;
                  }
              
          }
          if(answer[S.length()/2] == '\u0000') {
              int cursor = 0;
              while(visited[chars[cursor].ind]) {
                  cursor += 1;
              }
              answer[S.length()/2] = chars[cursor].c;
          }
          
          System.out.println(String.valueOf(answer));
      }
  }
  
  static boolean posible(String S, boolean[] visited) {
      HashMap<Character, Integer> map = new HashMap<>();
      int leftSize = 0;
      for(int i=0; i<S.length(); i++) {
          if(!visited[i]) {
              if(map.get(S.charAt(i)) == null) {
                  map.put(S.charAt(i), 0);
              }
              map.put(S.charAt(i), map.get(S.charAt(i)) + 1);
              leftSize += 1;
          }
      }
      int max = -1;
      for(Map.Entry<Character, Integer> entry : map.entrySet()) {
          if(max < entry.getValue()) {
              max = entry.getValue();
          }
      }
      if(leftSize % 2 == 0 && leftSize/2 < max) {
          return false;
      } else if(leftSize % 2 == 1 && leftSize/2 + 1 < max) {
          return false;
      }
      return true;
  }
  
  static Info findMaxChar(String S) {
      Info result = new Info(-1, -1);
      HashMap<Character, Info> map = new HashMap<>();
      for(int i=0; i<S.length(); i++) {
        if(map.get(S.charAt(i)) == null) {
             map.put(S.charAt(i), new Info(i, 0));
         }
        map.put(S.charAt(i), new Info(map.get(S.charAt(i)).ind, map.get(S.charAt(i)).cnt + 1));
      }
      int max = -1;
      for(Map.Entry<Character, Info> entry : map.entrySet()) {
          if(result.cnt < entry.getValue().cnt) {
              result = new Info(entry.getValue().ind, entry.getValue().cnt);
          }
      }
      return result;
  }
}