import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      
      HashMap<Integer, PriorityQueue<Integer>> tester = new HashMap<>();
      StringTokenizer st1 = new StringTokenizer(br.readLine());
      StringTokenizer st2 = new StringTokenizer(br.readLine());
      
      for(int i=0; i<N; i++) {
          int a = Integer.parseInt(st1.nextToken());
          int b = Integer.parseInt(st2.nextToken());
          if(tester.get(a) == null) {
              tester.put(a, new PriorityQueue<>());
          }
          tester.get(a).add(b);
      }
      
      PriorityQueue<Long> problems = new PriorityQueue<>(Comparator.reverseOrder());
      StringBuilder sb = new StringBuilder();
      long sum = 0;
      for(int i=1; i<=N; i++) {
          Iterator<Map.Entry<Integer, PriorityQueue<Integer>>> iterator = tester.entrySet().iterator();
          while(iterator.hasNext()) {
              Map.Entry<Integer, PriorityQueue<Integer>> entry = iterator.next();
              long b = (long) entry.getValue().poll();
              problems.add(b);
              sum += b;
              if(entry.getValue().isEmpty()) {
                  iterator.remove();
              }
          }
          if(problems.size() >= K) {
              int size = problems.size();
              for(int j=1; j<=size - K; j++) {
                  sum -= problems.poll();
              }
              sb.append(sum).append(" ");
          } else {
              sb.append(-1).append(" ");
          }
      }
      System.out.println(sb.toString().trim());
  }
}