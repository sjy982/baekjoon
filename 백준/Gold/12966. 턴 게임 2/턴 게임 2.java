import java.io.*;
import java.util.*;

public class Main {
    static long x, y;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      x = Long.parseLong(st.nextToken());
      y = Long.parseLong(st.nextToken());
      
      double sqrt = Math.sqrt(x + y);
      
      if(sqrt != Math.floor(sqrt)) {
          System.out.println(-1);
          return;
      }
      
      int maxI = (int) sqrt;
      
      int cnt = 0;
      for(int i=maxI; i>=1; i--) {
          if(x == 0) {
              break;
          }
          if(x >= 2 * i - 1 && x - (2 * i - 1) != 2) {
              x -= (2 * i - 1);
              cnt += 1;
          }
      }
      
      if(x != 0) {
          System.out.println(-1);
      } else {
          System.out.println(cnt);
      }
  }
}