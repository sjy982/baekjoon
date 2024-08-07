import java.io.*;
import java.util.*;

class Info implements Comparable<Info>{
    int ind, pri;
    Info(int ind, int pri) {
        this.ind = ind;
        this.pri = pri;
    }
    
    @Override
    public int compareTo(Info o) {
        if(this.pri < o.pri) {
            return -1;
        } else if(this.pri > o.pri) {
            return 1;
        } else {
            if(this.ind < o.ind) {
                return 1;
            } else if(this.ind > o.ind) {
                return -1;
            }
        }
        return 0;
    }
}

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      String str = br.readLine();
      Info[] sang = new Info[N];
      Info[] hee = new Info[N];
      for(int i=0; i<N; i++) {
          sang[i] = new Info(i, (int) str.charAt(i));
          hee[i] = new Info(i, (int) str.charAt(i));
      }
      Arrays.sort(hee);
      
      boolean[] visited = new boolean[N];
      int heeCursor = 0;
      int sangCursor = N - 1;
      int cout = 0;
      boolean sangTurn = true;
      StringBuilder sangSb = new StringBuilder();
      StringBuilder heeSb = new StringBuilder();
      while(cout != N) {
          if(sangTurn) {
              while(visited[sangCursor]) {
                  sangCursor -= 1;
              }
              visited[sangCursor] = true;
              sangSb.append((char) sang[sangCursor].pri);
          } else {
              while(visited[hee[heeCursor].ind]) {
                  heeCursor += 1;
              }
              visited[hee[heeCursor].ind] = true;
              heeSb.append((char) hee[heeCursor].pri);
          }
          sangTurn = !sangTurn;
          cout += 1;
      }
      int result = heeSb.toString().compareTo(sangSb.toString());
      if(result < 0) {
          System.out.println("DA");
      } else {
          System.out.println("NE");
      }
      System.out.println(heeSb.toString());
  }
}