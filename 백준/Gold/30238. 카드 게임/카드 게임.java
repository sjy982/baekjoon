import java.io.*;
import java.util.*;

public class Main {
    static int t;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      t = Integer.parseInt(br.readLine());
      StringBuilder sb = new StringBuilder();
      for(int k=0; k<t; k++) {
          int n = Integer.parseInt(br.readLine());
          long answer = 0;
          if(n == 1) {
              int a = Integer.parseInt(br.readLine());
              if(a >= 0) {
                  answer += a;
              }
          } else {
              int[] card = new int[n];
              StringTokenizer st = new StringTokenizer(br.readLine());
              for(int i=0; i<n; i++) {
                  card[i] = Integer.parseInt(st.nextToken());
              }
              if(card[0] >= 0) {
                  for(int i=0; i<n; i++) {
                      if(card[i] > 0) {
                          answer += card[i];
                      }
                  }
              } else {
                  for(int i=2; i<n; i++) {
                      if(card[i] > 0) {
                          answer += card[i];
                      }
                  }
                  answer += card[0] + card[1] > 0 ? card[0] + card[1] : 0;
              }
          }
          sb.append(answer).append("\n");
      }
      System.out.println(sb.toString().trim());
  }
}