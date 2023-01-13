import java.io.*;
import java.util.*;

public class Main {
    static int A,B,C,X,Y;
    static int ans = 0;
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      A = Integer.parseInt(st.nextToken());
      B = Integer.parseInt(st.nextToken());
      C = Integer.parseInt(st.nextToken());
      X = Integer.parseInt(st.nextToken());
      Y = Integer.parseInt(st.nextToken());
      if((A+B)>=2*C) {
          ans += Math.min(X,Y) * 2*C;
      } else {
          ans += Math.min(X,Y) * (A+B);
      }
      //나머지
      if(X>Y) {
          if(A>=2*C) {
              ans += (X-Y) * 2*C;
          } else {
              ans += (X-Y) * A;
          }
      } else if(X<Y) {
          if(B>=2*C) {
              ans += (Y-X) * 2*C;
          } else {
              ans += (Y-X) * B;
          }
      }
      System.out.println(ans);
    }
}