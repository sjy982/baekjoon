import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long X;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      X = Long.parseLong(st.nextToken());
      
      long max = ((long) (N - 2) * (N - 1)) / 2;
      if(max < X) {
          System.out.println(-1);
          return;
      }
      
      //max >= X라면 무조건 만들 수 있다.
      
      boolean[] unpooled = new boolean[N - 1];
      int pooledMax = N - 2; //가능한 풀에서 max 값을 의미함.
      while(max != X) {
          if(max - pooledMax < X) {
              //X보다 더 작아진다면.
              //1 ~ pooledMax에서 하나를 지워주면 끝임.
              long unpooledV = (N - 1) - (max - X);
              unpooled[(int) unpooledV] = true;
              max -= (max - X);
              break;
          }
          
          //max - pooledMax >= X
          unpooled[(N - 1) - pooledMax] = true;
          max -= pooledMax;
          pooledMax -= 1;
      }
      
      StringBuilder sb = new StringBuilder();
      sb.append(N).append(" ");
      ArrayList<Integer> unpooledList = new ArrayList<>();
      for(int i=1; i<=N - 2; i++) {
          if(unpooled[i]) {
              unpooledList.add(i);
          } else {
              sb.append(i).append(" ");
          }
      }
      sb.append(N - 1).append(" ");
      
      for(int i=unpooledList.size() - 1; i>=0; i--) {
          sb.append(unpooledList.get(i)).append(" ");
      }
      
      System.out.println(sb.toString().trim());
  }
}