import java.io.*;
import java.util.*;

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      int[] per = new int[N];
      int[][] nums = new int[N + 1][2]; //0 -> index, 1 -> 남은 수
      nums[0][0] = -1;
      int[] changeNum = new int[N + 1];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int i=0; i<N; i++) {
          int num = Integer.parseInt(st.nextToken());
          per[i] = num;
          nums[num][0] = i;
          if(num == 1 || num == N) {
              nums[num][1] = 2;
          } else {
              nums[num][1] = 3;
          }
      }
      
      for(int i=N; i>=1; i--) {
          ArrayList<Integer> list = new ArrayList<>();
          if(i == N) {
              fillList(N, N-1, list, changeNum);
          } else if(i == 1) {
              fillList(2, 1, list, changeNum);
          } else {
              fillList(i + 1, i - 1, list, changeNum);
          }
          
          int num = 0;
          for(int j=0; j<list.size(); j++) {
              if(nums[list.get(j)][1] == 1) {
                  //하나 남은 경우
                  num = list.get(j);
                  break;
              }
              if(nums[num][0] < nums[list.get(j)][0]) {
                  num = list.get(j);
              }
          }
          changeNum[num] = i;
          
          for(int j=i + 1; j>= i - 1; j--) {
              if(j != N + 1) {
                  nums[j][1] -= 1;
              }
          }
      }
      
      StringBuilder sb = new StringBuilder();
      for(int i=0; i<N; i++) {
          sb.append(changeNum[per[i]]).append(" ");
      }
      System.out.println(sb.toString().trim());
  }
  
  static void fillList(int start, int end, ArrayList<Integer> list, int[] changeNum) {
      for(int i=start; i>=end; i--) {
          if(changeNum[i] == 0) {
              list.add(i);
          }
      }
  }
}