import java.io.*;
import java.util.*;

class Stick implements Comparable<Stick> {
    int l, w;
    Stick(int l, int w) {
        this.l = l;
        this.w = w;
    }
    
    @Override
    public int compareTo(Stick o) {
        if(this.l < o.l) {
            return -1;
        } else if(this.l > o.l) {
            return 1;
        }
        return 0;
    }
}

public class Main {
    static int T;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      T = Integer.parseInt(br.readLine());
      StringBuilder sb = new StringBuilder();
      for(int k=0; k<T; k++) {
          int N = Integer.parseInt(br.readLine());
          Stick[] sticks = new Stick[N];
          boolean[] visited = new boolean[N];
          StringTokenizer st = new StringTokenizer(br.readLine());
          for(int i=0; i<N; i++) {
              sticks[i] = new Stick(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
          }
          Arrays.sort(sticks);
          int time = 0;
          for(int i=0; i<N; i++) {
              if(!visited[i]) {
                  visited[i] = true;
                  time += 1;
                  int bInd = i;
                  for(int j=i + 1; j<N; j++) {
                      if(!visited[j] && sticks[bInd].w <= sticks[j].w) {
                          visited[j] = true;
                          bInd = j;
                      }
                  }
              }
          }
          sb.append(time).append("\n");
      }
      System.out.println(sb.toString().trim());
  }
}