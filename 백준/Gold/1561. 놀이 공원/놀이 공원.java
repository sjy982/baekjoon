import java.io.*;
import java.util.*;

public class Main {
    static long N;
    static int M,ans;
    static long rides[];
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Long.parseLong(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      rides = new long[M];
      StringTokenizer n_st = new StringTokenizer(br.readLine());
      for(int i=0; i<M; i++) {
          rides[i] = Long.parseLong(n_st.nextToken());
      }
      if(N<=M) ans = (int) N;
      else {
          //이분 탐색
          long min = 1;
          long max = 30 * 2000000000L;
          while(true) {
              long mid = (min + max)/2;
              long left[] = new long[M];
              long cout = 0;
              for(int i=0; i<M; i++) {
                  cout += mid/rides[i];
                  left[i] = mid%rides[i];
                  if(left[i] != 0) cout += 1;
              }
              if(cout<N) {
                  if(check(cout, left)) break;
                  else {
                      min = mid + 1;
                  }
              } else {
                  max = mid - 1;
              }
          }
      }
      System.out.println(ans);
    }
    
    static boolean check(long cout, long left[]) {
        for(int i=0; i<M; i++) {
            if(left[i] == 0) cout += 1;
            if(cout == N) {
                ans = i + 1;
                return true;
            }
        }
        return false;
    }
}