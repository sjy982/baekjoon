import java.io.*;
import java.util.*;

class Job implements Comparable<Job>{
    int ind;
    double std;
    Job(int ind, double std) {
        this.ind = ind;
        this.std = std;
    }
    
    @Override
    public int compareTo(Job o) {
        if(this.std < o.std) {
            return -1;
        } else if(this.std > o.std) {
            return 1;
        } else {
            //같은 경우는 ind가 빠른 순
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
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      Job[] jobs = new Job[N];
      for(int i=0; i<N; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          double t = Double.parseDouble(st.nextToken());
          double s = Double.parseDouble(st.nextToken());
          jobs[i] = new Job(i + 1, t / s);
      }
      Arrays.sort(jobs);
      StringBuilder sb = new StringBuilder();
      for(int i=0; i<N; i++) {
          sb.append(jobs[i].ind).append(" ");
      }
      System.out.println(sb.toString().trim());
  }
}