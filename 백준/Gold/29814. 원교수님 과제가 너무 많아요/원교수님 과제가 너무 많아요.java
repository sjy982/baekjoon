import java.io.*;
import java.util.*;

public class Main {
    static int N, C;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      C = Integer.parseInt(st.nextToken());
      ArrayList<Integer>[] sc = new ArrayList[N + 1];
      
      for(int i=1; i<=N; i++) {
          sc[i] = new ArrayList<>();
      }
      
      for(int i=0; i<N; i++) {
          StringTokenizer st2 = new StringTokenizer(br.readLine());
          int d = Integer.parseInt(st2.nextToken());
          int t = Integer.parseInt(st2.nextToken());
          int p = Integer.parseInt(st2.nextToken());
          sc[d - t + 1].add(p);
      }
      
      PriorityQueue<Integer> pq = new PriorityQueue<>();
      
      for(int i=1; i<=N; i++) {
          
          for(int j=0; j<sc[i].size(); j++) {
              pq.add(sc[i].get(j));
          }
          
          if(pq.size() > i) {
              //pq의 size가 현재 일보다 크다면. i에 맞춰줘야 됨.
              int sz = pq.size();
              for(int j=1; j<=sz - i; j++) {
                  pq.poll(); //버린다.
              }
          }
      }
      
      ArrayList<Integer> list = new ArrayList<>();
      int sum = 0;
      while(!pq.isEmpty()) {
          int p = pq.poll();
          sum += p;
          list.add(p);
      }
      
      if(sum < C) {
          //포인트를 최대한 모았는데도 커트라인이 더 높다면 불가능.
          System.out.println("I'm sorry professor Won!");
      } else {
          //가능하다면 이번엔 과제봇을 최소화
          int answer = list.size();
          for(int i=0; i<list.size(); i++) {
              if(sum - list.get(i) < C) {
                  //작은 것부터 제외하는데, 커트라인을 못 넘기면 break;
                  break;
              }
              //커트라인을 그래도 넘기면
              answer -= 1;
              sum -= list.get(i);
          }
          System.out.println(answer);
      }
  }
}