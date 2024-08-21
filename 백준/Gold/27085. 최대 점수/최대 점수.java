import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int s;
    static long max = 0;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      s = Integer.parseInt(st.nextToken());
      long[] dungeon = new long[N];
      StringTokenizer st2 = new StringTokenizer(br.readLine());
      for(int i=0; i<N; i++) {
          dungeon[i] = Long.parseLong(st2.nextToken());
      }
      
      long lBeforeMax = 0;
      long rBeforeMax = 0;
      int lCursor = s - 1;
      int rCursor = s - 1;
      while(true) {
          rCursor = moveRight(rCursor, rBeforeMax, dungeon);
          rBeforeMax = max;
          lCursor = moveLeft(lCursor, lBeforeMax, dungeon);
          lBeforeMax = max;
          
          if(max == rBeforeMax) {
              break;
          }
      }
      System.out.println(max);
  }
  
  static int moveRight(int cursor, long beforeMax, long[] dungeon) {
      if(cursor == N) {
          return cursor;
      }
      long v = max - beforeMax + dungeon[cursor];
      cursor += 1;
      while(cursor <= N-1) {
          if(v + dungeon[cursor] < 0) {
              return cursor - 1;
          }
          dungeon[cursor] += v;
          v = dungeon[cursor];
          max = Math.max(max, v);
          cursor += 1;
      }
      return cursor;
  }
  
  static int moveLeft(int cursor, long beforeMax, long[] dungeon) {
      if(cursor == -1) {
          return cursor;
      }
      long v = max - beforeMax + dungeon[cursor];
      cursor -= 1;
      while(cursor >= 0) {
          if(v + dungeon[cursor] < 0) {
              return cursor + 1;
          }
          dungeon[cursor] += v;
          v = dungeon[cursor];
          max = Math.max(max, v);
          cursor -= 1;
      }
      return cursor;
  }
}