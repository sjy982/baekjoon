import java.io.*;
import java.util.*;

class Schedule implements Comparable<Schedule> {
    int a, s;
    Schedule(int a, int s) {
        this.a = a;
        this.s = s;
    }
    
    @Override
    public int compareTo(Schedule o) {
        if(this.a < o.a) {
            return -1;
        } else if(this.a > o.a) {
            return 1;
        }
        return 0;
    }
}

public class Main  {
    static int N, M;
    static Schedule[] list;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new Schedule[N];
        for(int i=0; i<N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            list[i] = new Schedule(Integer.parseInt(st2.nextToken()), Integer.parseInt(st2.nextToken()));
        }
        Arrays.sort(list);
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(list[0].a + list[0].s);
        
        for(int i=1; i<N; i++) {
            while(pq.size() != 0) {
                if(list[i].a < pq.peek()) {
                    break;
                }
                //1단계 통과
                if(list[i].a> pq.peek() + M) {
                    //이미 잠겨버림
                    pq.poll();
                } else {
                    //잠기지 않음
                    pq.poll();
                    answer += 1;
                    break;
                }
            }
            pq.add(list[i].a + list[i].s); //워크스테이션 배정
        }
        System.out.println(answer);
    }
}