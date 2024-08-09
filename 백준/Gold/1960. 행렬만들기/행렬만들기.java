import java.io.*;
import java.util.*;

class Info implements Comparable<Info> {
    int j, c;
    Info(int j, int c) {
        this.j = j; //열 번호
        this.c = c; //개수
    }
    
    @Override
    public int compareTo(Info o) {
        if(this.c < o.c) {
            return 1;
        } else if(this.c > o.c) {
            return -1;
        }
        return 0;
    }
}

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      int[][] answer = new int[N][N];
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      StringTokenizer st2 = new StringTokenizer(br.readLine());
      
      PriorityQueue<Info> pque = new PriorityQueue<>();
      for(int j=0; j<N; j++) {
          int v = Integer.parseInt(st2.nextToken());
          if(v != 0) {
              pque.add(new Info(j, v));
          }
      }
      boolean isPosible = true;
      for(int i=0; i<N; i++) {
          //i는 행 번호
          ArrayList<Info> list = new ArrayList<>();
          int v = Integer.parseInt(st.nextToken()); //행의 개수
          if(v == 0) {
              continue;
          }
          if(pque.size() < v) {
              isPosible = false;
              break;
          }
          
          for(int k=1; k<=v; k++) {
              Info row = pque.poll();
              answer[i][row.j] = 1;
              row.c -= 1;
              if(row.c != 0) {
                  list.add(row);
              }
          }
          
          for(int k=0; k<list.size(); k++) {
              pque.add(list.get(k));
          }
      }
      
      if(pque.size() != 0) {
          isPosible = false;
      }
      
      if(isPosible) {
          StringBuilder sb = new StringBuilder();
          sb.append(1).append("\n");
          for(int i=0; i<N; i++) {
              for(int j=0; j<N; j++) {
                  sb.append(answer[i][j]);
              }
              sb.append("\n");
          }
          System.out.println(sb.toString().trim());
      } else {
          System.out.println(-1);
      }
  }
}