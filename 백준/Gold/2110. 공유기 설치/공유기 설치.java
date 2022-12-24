import java.io.*;
import java.util.*;

public class Main {
    static int N,C;
    static long home[];
    static long max_dist, min_dist;
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      C = Integer.parseInt(st.nextToken());
      home = new long[N];
      for(int i=0; i<N; i++) {
          home[i] = Long.parseLong(br.readLine());
      }
      Arrays.sort(home);
      min_dist = 1;
      max_dist = home[home.length-1] - home[0];
      while(min_dist != max_dist) {
          long mid_dist;
          //mid_dist 구하기
          if((min_dist + max_dist)%2 == 0) mid_dist = (min_dist + max_dist)/2;
          else mid_dist = (min_dist + max_dist)/2 + 1;
          //공유기 설치
          int cout = 1;
          long last_router = home[0];
          for(int i=1; i<N; i++) {
              if(last_router + mid_dist <= home[i]) {
                  last_router = home[i];
                  cout += 1;
              }
          }
          if(cout >= C) {
              //min_dist 업데이트
              min_dist = mid_dist;
          } else {
              //max_dist 업데이트
              max_dist = mid_dist - 1;
          }
      }
      System.out.println(min_dist);
    }
}