import java.util.*;
import java.io.*;

class Schedule implements Comparable<Schedule> {
    int t, type;
    boolean end;
    Schedule(int t, int type) {
        this.t = t;
        this.type = type;
        this.end = false;
    }
    
    @Override
    public int compareTo(Schedule o) {
        if(this.t < o.t) {
            return -1;
        } else if(this.t > o.t) {
            return 1;
        }
        return 0;
    }
}

public class Main {
    static int T, A, B, M;
    static ArrayList<Schedule> list;
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++) {
            list = new ArrayList<>();
            M = Integer.parseInt(br.readLine());
            A = Integer.parseInt(br.readLine());
            for(int j=0; j<A; j++) {
                list.add(new Schedule(parseTimeToInt(br.readLine()), 0));
            }
            
            B = Integer.parseInt(br.readLine());
            for(int j=0; j<B; j++) {
                list.add(new Schedule(parseTimeToInt(br.readLine()), 1));
            } 
            Collections.sort(list);
            sb.append(start() + "\n");
        }
        System.out.println(sb.toString());
    }
    
    static int start() {
        int cout = 0;
        Schedule bus = new Schedule(-1, -1);
        bus.end = true;
        while(!end()) {
            if(bus.end) {
                cout += 1;
                for(int i=0; i<list.size(); i++) {
                    if(!list.get(i).end) {
                        bus = new Schedule(list.get(i).t + M, list.get(i).type == 0 ? 1 : 0);
                        list.get(i).end = true;
                        break;
                    }
                }
            }
            startNextSchedule(bus);
        }
        return cout;
    }
    
    static void startNextSchedule(Schedule bus) {
        //bus가 다음 스케줄을 실행함. 그러면 bus의 상태가 그 스케줄을 실행한 후 상태가 됨.
        //다음 스케줄은 현재 상태에서 가장 빨리 실행할 수 있는 스케줄 -> 그래야 gap이 줄어듬
        // 다음 스케줄이 없다? 그러면 bus의 상태를 end = ture로 바꾸면 됨.
        for(int i=0; i<list.size(); i++) {
            if(list.get(i).end) {
                continue;
            }
            //우리는 스케줄이 end = false인 경우만 따질거임
            //그 중 가장 먼저 찾은 실행할 수 있는 스케줄이 바로 다음 버스가 실행할 스케줄이다.
            if(list.get(i).type == 0) {
                int v = bus.type == 0 ? bus.t : bus.t + M;
                if(list.get(i).t >= v) {
                    //실행할 수 있는 스케줄임.
                    bus.t = list.get(i).t + M;
                    bus.type = 1;
                    list.get(i).end = true;
                    return;
                }
                
            } else if(list.get(i).type == 1) {
                int v = bus.type == 1 ? bus.t : bus.t + M;
                if(list.get(i).t >= v) {
                    //실행할 수 있는 스케줄
                    bus.t = list.get(i).t + M;
                    bus.type = 0;
                    list.get(i).end = true;
                    return;
                }
            }
        }
        bus.end = true;
    }
    
    static boolean end() {
        for(int i=0; i<list.size(); i++) {
            if(!list.get(i).end) {
                return false;
            }
        }
        return true;
    }
    
    static int parseTimeToInt(String time) {
        String[] split = time.split(":");
        int hour = Integer.parseInt(split[0]);
        int min = Integer.parseInt(split[1]);
        return hour * 60 + min;
    }
}