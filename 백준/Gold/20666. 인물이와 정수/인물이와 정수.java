import java.io.*;
import java.util.*;

class Tip {
    int b;
    long t;
    Tip(int b, long t) {
        this.b = b;
        this.t = t;
    }
}

class Monster implements Comparable<Monster> {
    int num;
    long lev;
    Monster(int num, long lev) {
        this.num = num;
        this.lev = lev;
    }
    
    @Override
    public int compareTo(Monster o) {
        if(this.lev < o.lev) {
            return -1;
        } else if(this.lev > o.lev) {
            return 1;
        }
        
        return 0;
    }
}

public class Main {
    static int N, M, p;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        long[] monsters = new long[N + 1];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            monsters[i] = Integer.parseInt(st2.nextToken());
        }
        
        p = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Tip>> tipList = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            tipList.add(new ArrayList<>());
        }
        
        for(int i=0; i<p; i++) {
            StringTokenizer st3 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st3.nextToken());
            int b = Integer.parseInt(st3.nextToken());
            long t = Long.parseLong(st3.nextToken());
            tipList.get(a).add(new Tip(b, t));
        }
        
        System.out.println(answer(monsters, tipList));
    }
    
    static long answer(long[] monsters, ArrayList<ArrayList<Tip>> tipList) {
        //처음에는 아이템이 없음 그래서 모든 tip 무시
        for(int i=1; i<tipList.size(); i++) {
            for(int j=0; j<tipList.get(i).size(); j++) {
                int b = tipList.get(i).get(j).b;
                long t = tipList.get(i).get(j).t;
                monsters[b] += t;
            }
        }
        
        //우선 순위 큐에 다 넣어준다. 난이도만 넣어주는 것이 아닌 몬스터의 number도 같이 넣어준다.
        PriorityQueue<Monster> pq = new PriorityQueue<>();
        for(int i=1; i<monsters.length; i++) {
            pq.add(new Monster(i, monsters[i]));
        }
        //잡은 몬스터는 표시를 해줘야 중복을 방지할 수 있음
        boolean[] kill = new boolean[N + 1];
        
        long max = -1;
        for(int i=0; i<M; i++) {
            Monster mon = pq.poll();
            while(kill[mon.num]) {
                //죽이지 않은 몬스터를 찾을 때까지 반복
                mon = pq.poll();
            }
            kill[mon.num] = true;
            max = Math.max(max, mon.lev); //잡은 몬스터 중에서 최대 난이도로 업데이트
            //몬스터를 잡았기 때문에 mon.num아이템을 얻었다. 관련된 tip을 적용해줌
            
            for(int j=0; j<tipList.get(mon.num).size(); j++) {
                //이미 죽은 몬스터는 넣어주면 안됨
                int b = tipList.get(mon.num).get(j).b;
                long t = tipList.get(mon.num).get(j).t;
                if(!kill[b]) {
                    //아직 죽이지 않았다면 update
                    monsters[b] -= t;
                    pq.add(new Monster(b, monsters[b]));
                }
            }
        }
        return max;
    }
}