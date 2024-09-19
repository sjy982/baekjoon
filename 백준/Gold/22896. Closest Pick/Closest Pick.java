import java.io.*;
import java.util.*;

public class Main {
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int T = Integer.parseInt(br.readLine());
      StringBuilder sb = new StringBuilder();
      
      for(int r=1; r<=T; r++) {
          sb.append("Case #" + r + ": ");
          
          StringTokenizer st = new StringTokenizer(br.readLine());
          int N = Integer.parseInt(st.nextToken());
          int K = Integer.parseInt(st.nextToken());
          int arr[] = new int[N];
          
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          for(int i=0; i<N; i++) {
              arr[i] = Integer.parseInt(st2.nextToken());
          }
          
          Arrays.sort(arr);
          
          ArrayList<Integer> list1 = new ArrayList<>(); //구간에서 하나만 선택하는 경우
          ArrayList<Integer> list2 = new ArrayList<>(); //구간에서 두 수를 선택하는 경우
          
          if(arr[0] - 1 > 0) {
              list1.add(arr[0] - 1);
          }
          
          if((K + 1) - arr[N - 1] - 1 > 0) {
              list1.add((K + 1) - arr[N - 1] - 1);
          }
          
          for(int i=1; i<N; i++) {
              int std = arr[i] - arr[i - 1] - 1; //전체 구간
              if(std <= 0) {
                  continue;
              }
              list2.add(std); //구간에 양쪽을 선택하면 양쪽 포함 안쪽 수를 포함할 수 있음
              
              //하나를 선택하는 경우 
              int s = arr[i - 1] + 1;
              int e = arr[i];
              
              if((s + e) % 2 == 0) {
                  list1.add((s + e) / 2 - s);
              } else {
                  list1.add((s + e) / 2 - s + 1);
              }
          }
          
          Collections.sort(list1, Collections.reverseOrder());
          Collections.sort(list2, Collections.reverseOrder());
          
          int mol1 = 0;
          for(int i=0; i<2; i++) {
              if(i >= list1.size()) {
                  break;
              }
              mol1 += list1.get(i);
          }
          
          int mol2 = list2.size() == 0 ? 0 : list2.get(0);
          
          int mol = mol1 >= mol2 ? mol1 : mol2;
          
          sb.append((double) mol / (double) K).append("\n");
      }
      System.out.println(sb.toString().trim());
  }
}