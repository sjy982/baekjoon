import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      int[] slot = new int[M];
      for(int i=0; i<M; i++) {
          slot[i] = Integer.parseInt(br.readLine());
      }
      
      int answer = binarySearch(slot);
      System.out.println(answer);
  }
  
  static int binarySearch(int[] slot) {
      int min = 1;
      int max = 100000;
      while(min <= max) {
          int mid = (min + max) / 2;
          
          if(check(slot, mid)) {
              min = mid + 1;
          } else {
              max = mid - 1;
          }
      }
      return max;
  }
  
  static boolean check(int[] slot, int len) {
      for(int i=0; i<M; i++) {
          //i는 start slot
          int sv = slot[i];
          int cnt = 1;
          int cursor = i + 1 == M ? 0 : i + 1;
          int left = N;
          while(true) {
              if(cnt == K) {
                  if(left >= len) {
                      return true;
                  }
                  break;
              }
              if(cursor == i) {
                  break;
              }
              int v = findLength(sv, slot[cursor]);
              if(v >= len) {
                  sv = slot[cursor];
                  cnt += 1;
                  left -= v;
              }
              cursor = cursor + 1 == M ? 0: cursor + 1;
          }
      }
      return false;
  }
  
  static int findLength(int sv, int v) {
      if(sv < v) {
          return v - sv;
      }
      return N + v - sv;
  }
}