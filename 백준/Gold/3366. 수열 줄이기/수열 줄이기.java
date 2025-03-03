import java.io.*;
import java.util.*;

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      
      long answer = 0;
      int before = Integer.parseInt(br.readLine());
      for(int i=1; i<N; i++) {
          int num = Integer.parseInt(br.readLine());
          answer += Math.max(before, num);
          before = num;
      }
      
      System.out.println(answer);
  }
}