import java.io.*;
import java.util.*;

class Art implements Comparable<Art> {
    long A, B;
    Art(long A, long B) {
        this.A = A;
        this.B = B;
    }
    
    @Override
    public int compareTo(Art o) {
        if(this.A < o.A) {
            return -1;
        } else if(this.A > o.A) {
            return 1;
        }
        return 0;
    }
}

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      Art[] arts = new Art[N + 1];
      
      for(int i=1; i<=N; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          arts[i] = new Art(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
      }
      arts[0] = new Art(0, 0);
      Arrays.sort(arts);
      
      long[] prefix = new long[N + 1];
      prefix[1] = arts[1].B;
      for(int i=2; i<=N; i++) {
          prefix[i] = prefix[i - 1] + arts[i].B;
      }
      
      long answer = prefix[1];
      int minIndex = 1;
      for(int i=2; i<=N; i++) {
          for(int j=1; j<=i; j++) {
              long newAnswer = (prefix[i] - prefix[j - 1]) - (arts[i].A - arts[j].A);
              answer = Math.max(answer, newAnswer);
          }
      }
      System.out.println(answer);
  }
}