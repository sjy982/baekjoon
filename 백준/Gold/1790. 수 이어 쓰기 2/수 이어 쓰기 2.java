import java.io.*;
import java.util.*;

public class Main {
    static int w = 1;
    static long K;
    static int N;
    static long boundary = 9;
    static long ans_num = 0;
    static long temp = 9;
    static char ans;
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      K = Long.parseLong(st.nextToken());
      while(true) {
          if(ans_num > 100000000) break;
          
          if(boundary < K) {
              K = K - boundary;
              ans_num = temp;
              w += 1;
              update_boundary();
          } else {
              long Q = K/w; //몫
              int R = (int) K%w; //나머지
              if(R != 0) {
                  ans_num += Q + 1;
                  String str_n = Long.toString(ans_num);
                  ans = str_n.charAt(R-1);
              } else {
                  ans_num += Q;
                  String str_n = Long.toString(ans_num);
                  ans = str_n.charAt(str_n.length() - 1);
              }
              break;
          }
      }
      if(N < ans_num) System.out.println(-1);
      else System.out.println(ans);
    }
    
    static void update_boundary() {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for(int i=0; i<w; i++) {
            sb.append('9');
            if(i==0) sb2.append('1');
            else sb2.append('0');
        }
        temp = Long.parseLong(sb.toString());
        boundary = w * (temp - Long.parseLong(sb2.toString()) + 1);
    }
}