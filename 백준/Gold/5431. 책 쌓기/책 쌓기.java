import java.io.*;
import java.util.*;

public class Main {
    static int T;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      T = Integer.parseInt(br.readLine());
      StringBuilder sb = new StringBuilder();
      for(int t=0; t<T; t++) {
          int N = Integer.parseInt(br.readLine());
          int[] arr = new int[N];
          StringTokenizer st = new StringTokenizer(br.readLine());
          for(int i=0; i<N; i++) {
              arr[i] = Integer.parseInt(st.nextToken());
          }
          
          long[] dp = new long[N];
          dp[0] = 1;
          ArrayList<Integer> list = new ArrayList<>();
          list.add(arr[0]);
          
          long answer = 0;
          
          int cntSame = 1;
          for(int i=1; i<N; i++) {
              int bottom = list.get(list.size() - 1);
              if(bottom == arr[i]) {
                  list.add(arr[i]);
                  cntSame += 1;
              } else if(bottom < arr[i]) {
                  list.add(arr[i]);
                  dp[i] = (cntSame + 1) * dp[i - cntSame];
                  cntSame = 1;
              } else {
                  //bottom > arr[i] 올려야 될 값임. 1 2 3 4 5 -> 다음 4값이 온 것임
                  int insertInd = findInsertIndex(arr[i], list);
                  answer += dp[insertInd];
                  
                  //다음에는 업데이트 해야됨.
                  list.add(arr[i]);
                  Collections.sort(list);
                  int cntSame2 = 1;
                  for(int j=insertInd + 1; j<=i; j++) {
                      int back = list.get(j - 1);
                      int cur = list.get(j);
                      if(back == cur) {
                          cntSame2 += 1;
                      } else if(back < cur) {
                          dp[j] = (cntSame2 + 1) * dp[j - cntSame2]; //업데이트
                          cntSame2 = 1;
                      }
                  }
              }
          }
          sb.append(answer).append("\n");
      }
      System.out.println(sb.toString().trim());
  }
  
  static int findInsertIndex(int std, ArrayList<Integer> list) {
      int result = -1;
      for(int i=0; i<list.size(); i++) {
          if(std <= list.get(i)) {
              result = i;
              break;
          }
      }
      return result;
  }
}