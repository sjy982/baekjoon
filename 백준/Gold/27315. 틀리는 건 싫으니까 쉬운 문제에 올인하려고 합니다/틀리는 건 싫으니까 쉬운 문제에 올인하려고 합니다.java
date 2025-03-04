import java.io.*;
import java.util.*;

class Problem implements Comparable<Problem> {
    int d, p, t;
    
    Problem(int d, int p, int t) {
        this.d = d;
        this.p = p;
        this.t = t;
    }
    
    @Override
    public int compareTo(Problem o) {
        if(this.t < o.t) {
            return 1;
        } else if(this.t > o.t) {
            return -1;
        } else {
            //this.t == o.t
            if(this.t == 1) {
                return -1;
            } else {
                //this.t도 0이고, o.t도 0일 때는
                //여기서는 구현 난이도가 더 작은 순
                if(this.p < o.p) {
                    return -1;
                } else if(this.p > o.p) {
                    return 1;
                }
            }
        }
        return 0;
    }
}

public class Main {
    static int N, M, HD, HP;
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    
    Problem[] pArr = new Problem[N];
    for(int i=0; i<N; i++) {
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int d = Integer.parseInt(st2.nextToken());
        int p = Integer.parseInt(st2.nextToken());
        int t = Integer.parseInt(st2.nextToken());
        int e = Integer.parseInt(st2.nextToken());
        
        if(e == 0) {
            pArr[i] = new Problem(d, p, t);
        } else {
            //에디토리얼이 있는 경우는
            pArr[i] = new Problem(d / 2 + d % 2, p / 2, t);
        }
    }
    
    Arrays.sort(pArr, new Comparator<Problem>() {
        @Override
        public int compare(Problem p1, Problem p2) {
            if(p1.d < p2.d) {
                return -1;
            } else if(p1.d > p2.d) {
                return 1;
            }
            return 0;
        }
    });
    
    StringTokenizer st3 = new StringTokenizer(br.readLine());
    HD = Integer.parseInt(st3.nextToken());
    HP = Integer.parseInt(st3.nextToken());
    
    long answer = 0;
    boolean isPosible = true;
    PriorityQueue<Problem> pq = new PriorityQueue<>();
    
    int nextCursor = 0; //다음 커서 위치
    int sol = 0;
    while(sol < M) {
        //푼 문제가 M보다 작다면
        //먼저 풀 수 있는 문제를 넣는다.
        for(int i=nextCursor; i<N; i++) {
            //다음 커서부터
            if(HD < pArr[i].d) {
                nextCursor = i;
                break;
            }
            pq.add(pArr[i]);
            
            if(i == N - 1) {
                //cursor가 끝까지 도달하면 pq에 모든 문제가 들어갔음을 의미.
                nextCursor = N;
            }
        }
        
        if(pq.isEmpty()) {
            //pq가 비어있다면 불가능함.
            isPosible = false;
            break;
        }
        
        //pq가 비어있지 않다면
        Problem p = pq.poll();
        if(p.t == 0) {
            //데이터를 소유하고 있지 않다면
            //구현 난이도를 비교해야됨
            if(HP < p.p) {
                //구현 난이도가 구현 능력보다 높은 경우
                answer += p.p - HP;
            }
        }
        
        sol += 1;
        HD += 1;
        HP += 1;
    }
    
    if(!isPosible) {
        System.out.println(-1);
    } else {
        System.out.println(answer);
    }
  }
}