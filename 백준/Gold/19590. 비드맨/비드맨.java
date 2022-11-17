import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long max_bead = -1;
    static long sum_bead = 0;
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      for(int i=0; i<N; i++) {
          long cur_bead = Integer.parseInt(br.readLine());
          sum_bead += cur_bead;
          if(max_bead < cur_bead) {
              max_bead = cur_bead;
          }
      }
      sum_bead -= max_bead;
      if(max_bead > sum_bead) {
          System.out.println(max_bead - sum_bead);
      } else {
          if((max_bead + sum_bead) % 2 == 0) {
              System.out.println(0);
          } else {
              System.out.println(1);
          }
      }
    }
}