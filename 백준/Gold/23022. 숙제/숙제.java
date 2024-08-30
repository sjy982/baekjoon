import java.io.*;
import java.util.*;

class Homework implements Comparable<Homework> {
    int t, v;
    Homework(int t, int v) {
        this.t = t;
        this.v = v;
    }
    
    @Override
    public int compareTo(Homework o) {
        if(this.v < o.v) {
            return 1;
        } else if(this.v > o.v) {
            return -1;
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
          StringTokenizer st = new StringTokenizer(br.readLine());
          int n = Integer.parseInt(st.nextToken());
          int S = Integer.parseInt(st.nextToken());
          Homework[] hm = new Homework[n];
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          StringTokenizer st3 = new StringTokenizer(br.readLine());
          for(int i=0; i<n; i++) {
              hm[i] = new Homework(Integer.parseInt(st2.nextToken()), Integer.parseInt(st3.nextToken()));
          }
          
          Arrays.sort(hm, new Comparator<Homework>() {
             @Override
             public int compare(Homework h1, Homework h2) {
                 return Integer.compare(h1.t, h2.t);
             }
          });
          
          PriorityQueue<Homework> pque = new PriorityQueue<>();
          long answer = 0;
          int cursor = 0;
          for(int i=0; i<n; i++) {
              if(hm[i].t > S) {
                  break;
              }
              cursor += 1;
              pque.add(hm[i]);
          }
          while(true) {
              if(pque.isEmpty()) {
                  if(cursor == n) {
                      break;
                  }
                  S = hm[cursor].t;
                  for(int i=cursor; i<n; i++) {
                      if(hm[i].t > S) {
                          break;
                      }
                      cursor += 1;
                      pque.add(hm[i]);
                  }
              }
              Homework h = pque.poll();
              answer += (long) (S - h.t) * (long) h.v;
              S += 1;
              
              if(!pque.isEmpty()) {
                  for(int i=cursor; i<n; i++) {
                      if(hm[i].t > S) {
                          break;
                      }
                      cursor += 1;
                      pque.add(hm[i]);
                  }
              }
              
          }
          
          sb.append(answer).append("\n");
      }
      System.out.println(sb.toString().trim());
  }
}