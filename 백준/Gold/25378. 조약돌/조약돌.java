import java.io.*;
import java.util.*;

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      int[] arr = new int[N];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int i=0; i<N; i++) {
          arr[i] = Integer.parseInt(st.nextToken());
      }
      HashMap<Integer, Integer> map = new HashMap<>();
      map.put(arr[0], 0);
      for(int i=0; i<N - 1; i++) {
          HashMap<Integer, Integer> nextMap = new HashMap<>();
          for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
              int A = entry.getKey();
              int v = entry.getValue();
              int B = arr[i + 1];
              if(A == 0) {
                  update(B, v, nextMap);
              } else {
                  if(A < B) {
                      update(B - A, v + 1, nextMap);
                      update(B, v + 1, nextMap);
                  } else if(A > B) {
                      update(B, v + 1, nextMap);
                  } else {
                      update(0, v + 1, nextMap);
                  }
              }
          }
          map = nextMap;
      }
      int answer = Integer.MAX_VALUE;
      for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
          int num = entry.getKey();
          int value = num == 0 ? entry.getValue() : entry.getValue() + 1;
          answer = Math.min(answer, value);
      }
      System.out.println(answer);
  }
  
  static void update(int num, int cnt, HashMap<Integer, Integer> map) {
      if(map.get(num) == null) {
          map.put(num, cnt);
      } else {
          map.put(num, Math.min(map.get(num), cnt));
      }
  }
}