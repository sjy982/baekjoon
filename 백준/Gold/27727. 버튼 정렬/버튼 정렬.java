import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int ind; 
    long num;
    Node(int ind, long num) {
        this.ind = ind;
        this.num = num;
    }
    
    @Override
    public int compareTo(Node o) {
        if(this.num < o.num) {
            return -1;
        } else if(this.num > o.num) {
            return 1;
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
    static int N;
    static long K;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      
      Node[] A = new Node[N];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int i=0; i<N; i++) {
          A[i] = new Node(i, Long.parseLong(st.nextToken()));
      }
      Arrays.sort(A);
      K = Long.parseLong(br.readLine());
      if(N == 1) {
          System.out.println(K);
          return;
      }
      long curK = 0;
      long asc = 0;
      int startInd = 1;
      for(int i=N-1; i>=2; i--) {
          if(A[i].ind != i) {
              startInd = i;
              break;
          }
      }
      
      for(int i=0; i<startInd; i++) {
          curK += A[startInd].num - A[i].num;
          if(curK > K) {
              System.out.println(asc);
              return;
          }
      }
      if(curK > 0) {
          asc += 1;
      }
      int nextInd = startInd + 1;
      while(nextInd < N) {
          if(A[nextInd - 1].num != A[nextInd].num) {
              long cnt = nextInd * (A[nextInd].num - A[nextInd - 1].num);
              if(curK + cnt > K) {
                  break;
              }
              curK += cnt;
              asc += A[nextInd].num - A[nextInd - 1].num;
          }
          nextInd += 1;
      }
      
      asc +=  (K - curK) / (long) nextInd;
      System.out.println(asc);
  }
}