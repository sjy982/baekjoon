import java.io.*;
import java.util.*;

class Jinju implements Comparable<Jinju> {
    int c, ind;
    Jinju(int c, int ind) {
        this.c = c;
        this.ind = ind;
    }
    
    @Override
    public int compareTo(Jinju o) {
        if(this.c < o.c) {
            return 1;
        } else if(this.c > o.c) {
            return -1;
        }
        return 0;
    }
}

public class Main {
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int C = Integer.parseInt(st.nextToken());
      
      PriorityQueue<Jinju> pQue = new PriorityQueue<>();
      
      for(int i=1; i<=C; i++) {
          pQue.add(new Jinju(Integer.parseInt(br.readLine()), i));
      }
      
      StringBuilder sb = new StringBuilder();
      while(!pQue.isEmpty()) {
          //비어있지 않다면 무조건 두 종류 이상의 진주가 남아있음
          Jinju fst = pQue.poll();
          Jinju sec = pQue.poll();
          
          sb.append(fst.ind).append(" ").append(sec.ind).append("\n");
          
          fst.c -= 1;
          sec.c -= 1;
          
          if(fst.c > 0) {
              pQue.add(fst);
          }
          
          if(sec.c > 0) {
              pQue.add(sec);
          }
      }
      
      System.out.println(sb.toString().trim());
  }
}