import java.io.*;
import java.util.*;
class JewBag implements Comparable<JewBag> {
    int w,p;
    public JewBag(int w, int p) {
        this.w = w;
        this.p = p;
    }
    
    @Override
    public int compareTo(JewBag v) {
        int dif = this.w - v.w;
        if(dif>0) return 1;
        if(dif<0) return -1;
        if(dif == 0) {
            int p_dif = v.p - this.p;
            if(p_dif>0) return 1;
            if(p_dif<0) return -1;
        }
        return 0;
    }
}

public class Main {
    static int N,K;
    static long ans = 0;
    static int bag_cout = 0;
    static JewBag jew_bag[];
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      jew_bag = new JewBag[N+K];
      for(int i=0; i<N+K; i++) {
          if(i<N) {
              StringTokenizer sti = new StringTokenizer(br.readLine());
              int jw = Integer.parseInt(sti.nextToken());
              int jp = Integer.parseInt(sti.nextToken());
              jew_bag[i] = new JewBag(jw,jp);
          } else {
              int bw = Integer.parseInt(br.readLine());
              jew_bag[i] = new JewBag(bw,-1);
          }
      }
      Arrays.sort(jew_bag);
      PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
      for(int i=0; i<jew_bag.length; i++) {
          if(jew_bag[i].p == -1) {
              //가방이 나오면 보석 가격순 우선순위 큐에서 poll.
              if(!priorityQueue.isEmpty()) {
                  ans += priorityQueue.poll();
              }
              bag_cout += 1;
          } else {
              //보석이 나오면 보석 가격순 우선순위 큐에 삽입.
              priorityQueue.add(jew_bag[i].p);
          }
          if(bag_cout == K) break;
      }
      System.out.println(ans);
    }
}