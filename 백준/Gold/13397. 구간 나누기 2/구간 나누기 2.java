import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static int arr[];
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      arr = new int[N];
      StringTokenizer n_st = new StringTokenizer(br.readLine());
      for(int i=0; i<N; i++) {
          arr[i] = Integer.parseInt(n_st.nextToken());
      }
      //이분 탐색
      int min = 0;
      int max = 9999;
      while(min<=max) {
          int mid = (min + max)/2;
          int cout = 1;
          ArrayList<Integer> section = new ArrayList<>();
          for(int i=0; i<N; i++) {
              section.add(arr[i]);
              int score = cal_score(section);
              if(score > mid) {
                  section = new ArrayList<>(Arrays.asList(arr[i]));
                  cout += 1;
              }
          }
          if(cout<=M) max = mid - 1;
          else min = mid + 1;
      }
      System.out.println(max+1);
    }
    static int cal_score(ArrayList<Integer> sec) {
        int max_score = Collections.max(sec); 
        int min_score = Collections.min(sec);
        return max_score - min_score;
    }
}