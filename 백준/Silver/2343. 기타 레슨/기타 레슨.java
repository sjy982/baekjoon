import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static int lecture[];
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      lecture = new int[N];
      StringTokenizer n_st = new StringTokenizer(br.readLine());
      int min = 1;
      int max = 0;
      for(int i=0; i<N; i++) {
          lecture[i] = Integer.parseInt(n_st.nextToken());
          max += lecture[i];
      }
      //이분 탐색
      while(max != min) {
          int mid = (min + max)/2;
          //최소, 최대 업뎃
          int cout = 0;
          int lec_cout = 0;
          for(int i=0; i<N; i++) {
              if(lecture[i] > mid) {
                  cout = M+1;
                  break;
              } else {
                  if((lec_cout + lecture[i]) > mid) {
                      cout += 1;
                      lec_cout = lecture[i];
                  } else lec_cout += lecture[i];
              }
          }
          cout += 1;
          if(cout <= M) {
              max = mid;
          } else {
              min = mid+1;
          } 
      }
      System.out.println(max);
    }
}