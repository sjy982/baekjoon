import java.io.*;
import java.util.*;

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      
      int[] arr = new int[N]; // 수열
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      for(int i=0; i<N; i++) {
          arr[i] = Integer.parseInt(st.nextToken());
      }
      
      HashMap<Integer, Boolean> vDp = new HashMap<>(); //2개의 조합을 체크할 용도로 HashMap 사용
      int cnt = 0;
      for(int i=1; i<N; i++) {
          for(int j=0; j<=i - 1; j++) {
              vDp.put(arr[j] + arr[i - 1], true); //2개의 조합을 넣어준다.
          }
          
          for(int j=0; j<=i - 1; j++) {
              //이제 가능한지 체크
              if((arr[j] * 3 == arr[i])) {
                  //같은 수 3번 더하는 경우
                  cnt += 1;
                  break;
              }
              //현재 수 한 번 2개 조합 중 한 번
              int left = arr[i] - arr[j];
              if(vDp.get(left) != null) {
                  cnt += 1;
                  break;
              }
          }
      }
      
      System.out.println(cnt);
  }
}