import java.io.*;
import java.util.*;

class Ticket implements Comparable<Ticket> {
    double v; //가치
    int ind; //인덱스
    
    Ticket(int ind, double v) {
        this.ind = ind;
        this.v = v;
    }
    
    @Override
    public int compareTo(Ticket o) {
        if(this.v < o.v) {
            return 1;
        } else if(this.v > o.v) {
            return -1;
        } else {
            if(this.ind < o.ind) {
                return -1;
            } else if(this.ind > o.ind) {
                return 1;
            }
        }
        return 0;
    }
}

public class Main {
    static int n;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      n = Integer.parseInt(br.readLine());
      Ticket[] tickets = new Ticket[n];
      
      for(int i=0; i<n; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          int w = Integer.parseInt(st.nextToken());
          int q = Integer.parseInt(st.nextToken());
          
          tickets[i] = new Ticket(i + 1, w * 10000.0 / (10000.0  - q));
      }
      
      Arrays.sort(tickets);
      
      StringBuilder sb = new StringBuilder();
      for(int i=0; i<n; i++) {
          sb.append(tickets[i].ind).append(" ");
      }
      System.out.println(sb.toString().trim());
  }
}