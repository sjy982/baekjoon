import java.io.*;
import java.util.*;

public class Main {
    static long lan_line[];
    static int N,K;
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      lan_line = new long[N];
      long min_length = 1;
      long max_length = -1;
      for(int i=0; i<N; i++) {
          lan_line[i] = Long.parseLong(br.readLine());
          if(max_length < lan_line[i]) max_length = lan_line[i];
      }
      // 이분 탐색
      while(min_length != max_length) {
          long mid_length;
          if((min_length + max_length)%2 == 0) mid_length = (min_length + max_length)/2;
          else mid_length = (min_length + max_length)/2 + 1;
          //LanLine count
          int cout = 0;
          for(int i=0; i<N; i++) {
              cout += lan_line[i]/mid_length;
          }
          //최소 길이, 최대 길이 업데이트
          if(cout >= K) {
              min_length = mid_length;
          } else {
              max_length = mid_length - 1;
          }
      }
      System.out.println(min_length);
    }
}