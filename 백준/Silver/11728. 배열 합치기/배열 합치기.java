import java.io.*;
import java.util.*;

public class Main {
    static long arr[];
    static int N,M;
    static StringBuilder ans = new StringBuilder();
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      arr = new long[N+M];
      for(int i=0; i<2; i++) {
          StringTokenizer sti = new StringTokenizer(br.readLine());
          if(i==0) {
              for(int j=0; j<N; j++) {
                  arr[j] = Long.parseLong(sti.nextToken());
              }
          } else {
              for(int j=N; j<N+M; j++) {
                  arr[j] = Long.parseLong(sti.nextToken());
              }
          }
      }
      Arrays.sort(arr);
      for(int i=0; i<arr.length; i++) {
          ans.append(arr[i]);
          if(i != arr.length - 1) ans.append(' ');
      }
      System.out.println(ans.toString());
    }
}