import java.io.*;
import java.util.*;

public class Main {
    static int nA, nB;
    static int M;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      nA = Integer.parseInt(st.nextToken());
      nB = Integer.parseInt(st.nextToken());
      
      M = Integer.parseInt(br.readLine());
      int finalA = nA;
      int finalB = nB;
      int answer = 0;
      if(nA % 2 == 1 && nB % 2 == 1) {
          for(int i=0; i<M; i++) {
              StringTokenizer st2 = new StringTokenizer(br.readLine());
              int a = Integer.parseInt(st2.nextToken());
              int b = Integer.parseInt(st2.nextToken());
              
              if(divisionEven(a, nA) && divisionEven(b, nB)) {
                  answer += 1;
                  break;
              }
          }
      }
      answer += finalA/2 + finalB/2;
      System.out.println(answer);
  }
  
  static boolean divisionEven(int v, int end) {
      if((v - 1) % 2 == 0 && (end - v) % 2 == 0) {
          return true;
      }
      return false;
  }
}