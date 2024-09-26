import java.io.*;
import java.util.*;

public class Main {
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(br.readLine());
      
      long[] arr = new long[N];
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int i=0; i<N; i++) {
          arr[i] = Long.parseLong(st.nextToken());
      }
      
      int lp = 0;
      int rp = N - 1;
      int cnt = 0;
      while(lp < rp) {
          if(arr[lp] == arr[rp]) {
              lp += 1;
              rp -= 1;
          } else if(arr[lp] > arr[rp]) {
              arr[rp - 1] += arr[rp];
              rp -= 1;
              cnt += 1;
          } else if(arr[lp] < arr[rp]) {
              arr[lp + 1] += arr[lp];
              lp += 1;
              cnt += 1;
          }
      }
      
      System.out.println(cnt);
  }
}