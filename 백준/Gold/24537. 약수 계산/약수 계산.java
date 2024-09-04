import java.io.*;
import java.util.*;

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      int[] gcdCount = new int[1000001];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int i=0; i<N; i++) {
          gcdCount[Integer.parseInt(st.nextToken())] += 1;
      }
      
      for(int i=1; i<=1000000; i++) {
          for(int j=i+i; j<=1000000; j+=i) {
              gcdCount[i] += gcdCount[j];
          }
      }
      
      for(int i=1; i<=1000000; i++) {
          if(gcdCount[i] == 0) {
              gcdCount[i] = -1;
              continue;
          }
          for(int j=i+i; j<=1000000; j+=i) {
              if(gcdCount[j] > 0) {
                  if(gcdCount[i] == gcdCount[j]) {
                      //같으면 최대공약수 i를 만들 수 없음
                      gcdCount[i] = -1;
                      break;
                  }
              }
          }
      }
      StringBuilder sb = new StringBuilder();
      int Q = Integer.parseInt(br.readLine());
      for(int i=0; i<Q; i++) {
          sb.append(gcdCount[Integer.parseInt(br.readLine())]).append("\n");
      }
      System.out.println(sb.toString().trim());
  }
}