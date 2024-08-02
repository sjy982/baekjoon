import java.io.*;
import java.util.*;

public class Main {
    static long N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Long.parseLong(br.readLine());
      
      Stack<String> stack = new Stack();
      int cout = 0;
      while(N != 0) {
          if(N == 2) {
              N -= 2;
              stack.push("[+]");
          } else if((N & 2) == 2) {
              //첫 번째 비트가 1인 경우
              N -= 2;
              stack.push("[+]");
          } else if((N & 1) == 1) {
              //0 번째 비트가 1인 경우
              N *= 2;
              stack.push("[/]");
          } else {
              //0, 1번째 비트가 0인 경우는 앞에서 1를 끌어온다.
              N /= 2;
              stack.push("[*]");
          }
          cout += 1;
          
          if(cout > 99) {
              cout = -1;
              break;
          }
      }
      System.out.println(cout);
      if(cout != -1) {
          StringBuilder sb = new StringBuilder();
          while(!stack.isEmpty()) {
              sb.append(stack.pop()).append(" ");
          }
          System.out.println(sb.toString().trim());
      }
  }
}