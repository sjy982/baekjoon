import java.io.*;
import java.util.*;

public class Main {
    static int N,K;
    static char S[];
    static String ans;
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      S = new char[N];
      Arrays.fill(S, 'B');
      int ca= N/2;
      int cb = N - ca;
      if(ca * cb < K) System.out.println(-1);
      else {
          int front_ac = K/cb - 1;
          int loc_a = -1;
          int back_ac = ca;
          if(K%cb != 0) {
              loc_a = front_ac + 1 + cb - K%cb;
              if(front_ac == -1) back_ac -= 1;
              else back_ac -= front_ac + 2;
          } else {
              if(front_ac != -1) back_ac -= front_ac + 1;
          }
          for(int i=0; i<N; i++) {
              if(front_ac >= i) {
                  S[i] = 'A';
              } else if(loc_a == i) {
                  S[i] = 'A';
              } else if(N-back_ac<= i) {
                  S[i] = 'A';
              }
          }
        ans = new String(S);
        System.out.println(ans);
      }
    }
}