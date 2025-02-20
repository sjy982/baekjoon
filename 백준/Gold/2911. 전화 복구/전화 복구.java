import java.io.*;
import java.util.*;

class Sensor implements Comparable<Sensor> {
    long ind;
    long c;
    Sensor(long ind, long c) {
        this.ind = ind;
        this.c = c;
    }
    
    @Override
    public int compareTo(Sensor o) {
        if(this.ind < o.ind) {
            return -1;
        } else if(this.ind > o.ind) {
            return 1;
        }
        return 0;
    }
}

public class Main {
    static int N, M;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      Sensor[] sensors = new Sensor[N];
      for(int i=0; i<N; i++) {
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          long ind = Long.parseLong(st2.nextToken());
          long c = Long.parseLong(st2.nextToken());
          sensors[i] = new Sensor(ind, c);
      }
      Arrays.sort(sensors);
      
      long answer = 0;
      long same = sensors[0].c;
      for(int i=0; i<N - 1; i++) {
          if(sensors[i].c <= sensors[i + 1].c) {
              // 2 -> 4인 경우
              answer += sensors[i + 1].c - sensors[i].c;
          } else {
              // 4 -> 2인 경우
              if(same > sensors[i + 1].c) {
                  answer += Math.abs(same - sensors[i + 1].c);
              }
              same = Math.min(same, sensors[i + 1].c);
          }
      }
      System.out.println(answer + same);
  }
}