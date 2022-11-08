import java.io.*;
import java.util.*;
public class Main {
    static int N,K,ans;
    static int coin[];
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      ans = 0;
      coin = new int[N];
      for(int i=0; i<N; i++) {
          coin[i] = Integer.parseInt(br.readLine());
      }
      while(K!=0) {
          for(int i=0; i<N; i++) {
              if(coin[i] == K) {
                  ans += K/coin[i];
                  K = K%coin[i];
              } else if(coin[i] > K) {
                  ans += K/coin[i-1];
                  K = K%coin[i-1];
              } else if(i == N-1) {
                  //마지막
                  ans += K/coin[i];
                  K = K%coin[i];
              }
          }
      }
      System.out.println(ans);
    }
}