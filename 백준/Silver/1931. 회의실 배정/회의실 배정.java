import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    long s,e;
    public Node(long s, long e) {
        this.s = s;
        this.e = e;
    }
    
    @Override
    public int compareTo(Node n) {
        long dif = this.e - n.e;
        if (dif > 0) return 1;
        if (dif < 0) return -1;
        if(dif == 0) {
            long dif_s = this.s - n.s;
            if(dif_s > 0) return 1;
            if(dif_s < 0) return -1;
        }
        return 0;
    }
}

public class Main {
    static int N,cout;
    static long before;
    static Node meeting[];
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      cout = 0;
      before = 0;
      meeting = new Node[N];
      for(int i=0; i<N; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          long s = Integer.parseInt(st.nextToken());
          long e = Integer.parseInt(st.nextToken());
          meeting[i] = new Node(s,e);
      }
      Arrays.sort(meeting);
      for(int i=0; i<N; i++) {
          if(before <= meeting[i].s) {
              before = meeting[i].e;
              cout += 1;
          }
      }
      System.out.println(cout);
    }
}