import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long M;
    static long tree[];
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Long.parseLong(st.nextToken());
      tree = new long[N];
      long min_len = 0;
      long max_len = -1;
      StringTokenizer sti = new StringTokenizer(br.readLine());
      for(int i=0; i<N; i++) {
          tree[i] = Long.parseLong(sti.nextToken());
          if(max_len < tree[i]) max_len = tree[i];
      }
      max_len -= 1;
      //이분 탐색
      while(min_len != max_len) {
          long mid_len;
          if((min_len + max_len)%2 == 0) mid_len = (min_len + max_len)/2;
          else mid_len = (min_len + max_len)/2 + 1;
          // 나무 자르기
          long len_cout = 0;
          for(int i=0; i<N; i++) {
              if(mid_len < tree[i]) len_cout += tree[i] - mid_len;
          }
          //최소 길이, 최대 길이 업데이트
          if(len_cout>=M) {
              min_len = mid_len;
          } else {
              max_len = mid_len - 1;
          }
      }
      System.out.println(min_len);
    }
}