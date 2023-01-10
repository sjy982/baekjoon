import java.io.*;
import java.util.*;

public class Main {
    static long N,K;
    static long ans;
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Long.parseLong(br.readLine());
      K = Long.parseLong(br.readLine());
      long min = 1;
      long max = N * N;
      while(true) {
          long mid = (min + max)/2;
          long cout = 1;
          long same_cout = 0;
          for(long i=1; i<=N; i++) {
              long n_cout = mid/i;
              if(n_cout > N) cout += N;
              else {
                  if(mid%i != 0) {
                      cout += n_cout;
                  } else {
                      same_cout += 1;
                      cout += n_cout - 1;
                  }
              }
          }
          if(cout == K) {
              ans = mid;
              break;
          } else if(cout <= K && K <= cout + same_cout - 1) {
              ans = mid;
              break;
          }
          if(cout < K) {
              min = mid + 1;
          } else if(cout > K) {
              max = mid - 1;
          }
      }
      long weight = N * N;
      for(int i=1; i<=N; i++) {
          long quo = ans/i;
          if((quo == N) && (ans%i == 0)) {
              weight = 0;
              break;
          } else if(quo < N) {
              if(ans%i == 0) {
                  weight = 0;
                  break;
              } else {
                  weight = Math.min(weight, (i-ans%i));
              }
          }
      }
      System.out.println(ans + weight);
    }
}