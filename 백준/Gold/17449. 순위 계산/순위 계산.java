import java.io.*;
import java.util.*;

class RankInfo {
    int r,w;
    boolean sc;
    RankInfo(int r, int w, boolean sc) {
        this.r = r;
        this.w = w;
        this.sc = sc;
    }
}

class Range {
    int s,e;
    Range(int s, int e) {
        this.s = s;
        this.e = e;
    }
}

public class Main {
    static int N;
    static RankInfo tam;
    static Range range;
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      tam = new RankInfo(Integer.parseInt(br.readLine()), 0, false);
      N = Integer.parseInt(br.readLine());
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int i=0; i<N; i++) {
          int ar = Integer.parseInt(st.nextToken()); //after rank
          if(tam.r > ar) {
              tam = new RankInfo(tam.r+1, tam.w, tam.sc);
              range = new Range(tam.r+1, tam.r + tam.w);
          } else if(tam.r == ar) {
              tam = new RankInfo(tam.r, tam.w + 1, true);
              range = new Range(tam.r+1, tam.r + tam.w);
          } else {
              if(tam.sc) {
                  if(ar>=range.s && ar<=range.e) {
                      tam = new RankInfo(ar, tam.w - (ar - tam.r) + 1, true);
                      range = new Range(tam.r + 1, tam.r + tam.w);
                  }
              }
          }
      }
      System.out.printf("%d %d", tam.r, tam.r + tam.w);
    }
}