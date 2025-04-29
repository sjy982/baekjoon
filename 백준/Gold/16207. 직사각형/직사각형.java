import java.io.*;
import java.util.*;

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      Integer[] arr = new Integer[N];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int i=0; i<N; i++) {
          arr[i] = Integer.parseInt(st.nextToken());
      }
      
      Arrays.sort(arr, Collections.reverseOrder());
      Queue<Integer> p1Que = new LinkedList<>();
      Queue<Integer> p2Que = new LinkedList<>();
      long answer = 0;
      
      for(int i=0; i<N; i++) {
          if(p1Que.size() < 2) {
              p1Que.add(arr[i]);
              if(p1Que.size() == 2) {
                  if(p1Que.peek().intValue() != arr[i].intValue() && p1Que.peek().intValue() - 1 != arr[i].intValue()) {
                      p1Que.poll();
                  }
              }
          } else {
              //p1Que가 2인 경우
              if(p2Que.size() < 2) {
                  p2Que.add(arr[i]);
                  if(p2Que.size() == 2) {
                      if(p2Que.peek().intValue() != arr[i].intValue() && p2Que.peek().intValue() - 1 != arr[i].intValue()) {
                          p2Que.poll();
                      }
                  }
                  
                  //같은지 검사하는 로직을 통과하고 나서도 사이즈가 2라면.
                  if(p2Que.size() == 2) {
                      
                      p1Que.poll();
                      p2Que.poll();
                      
                      answer += ((long) p1Que.poll() * (long) p2Que.poll());
                  }
              }
          }
      }
      
      System.out.println(answer);
  }
}