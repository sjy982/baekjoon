import java.io.*;
import java.util.*;

public class Main {
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int m = Integer.parseInt(st.nextToken());
      int n = Integer.parseInt(st.nextToken());
      
      String input = br.readLine();
      int cnt = 0;
      ArrayList<Integer> list = new ArrayList<>();
      for(int i=0; i<input.length(); i++) {
          if(list.size() == 0) {
              cnt += 1;
              list.add(input.charAt(i) - '0');
          } else if(list.size() == 1) {
              if(list.get(0) != (input.charAt(i) - '0')) {
                  cnt += 1;
                  list = new ArrayList<>();
              }
          }
      }
      int answer = n * 2 - (cnt);
      System.out.println(answer > 0 ? answer : 0);
  }
}