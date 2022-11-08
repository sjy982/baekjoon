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
         for(int i=N-1; i>=0; i--) {
             if(K >= coin[i]) {
                 ans += K/coin[i];
                 K = K%coin[i];
             }
         }
      }
      System.out.println(ans);
    }
}