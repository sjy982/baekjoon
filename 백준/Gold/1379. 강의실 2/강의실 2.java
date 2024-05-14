import java.io.*;
import java.util.*;

class Lecture {
    int num, roomNum, start, end;
    Lecture(int num, int start, int end) {
        this.num = num;
        this.roomNum = -1;
        this.start = start;
        this.end = end;
    }
    
    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }
    
    public int getRoomNum() {
        return this.roomNum;
    }
}

public class Main {
    static int N;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Lecture[] lectures = new Lecture[N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lectures[i] = new Lecture(num, start, end);
        }
        Arrays.sort(lectures, new Comparator<Lecture>() {
            @Override
            public int compare(Lecture l1, Lecture l2) {
                return Integer.compare(l1.num, l2.num);
            }
        });
        StringBuilder sb = new StringBuilder();
        sb.append(assignRoomNum(lectures)).append('\n');
        for(int i=0; i<N; i++) {
            sb.append(lectures[i].getRoomNum()).append('\n');
        }
        System.out.println(sb.toString().trim());
    }
    
    static int assignRoomNum(Lecture[] lectures) {
        ArrayList<Lecture> sList = new ArrayList<>();
        ArrayList<Lecture> eList = new ArrayList<>();
        for(int i=0; i<lectures.length; i++) {
            sList.add(lectures[i]);
            eList.add(lectures[i]);
        }
        
        Collections.sort(sList, new Comparator<Lecture>() {
            @Override
            public int compare(Lecture l1, Lecture l2) {
                return Integer.compare(l1.start, l2.start);
            }
        });
        
        Collections.sort(eList, new Comparator<Lecture>() {
            @Override
            public int compare(Lecture l1, Lecture l2) {
                return Integer.compare(l1.end, l2.end);
            }
        });
        
        int k = 1; //새로 배정할 강의실 번호
        int sp = 0;
        for(int i=0; i<eList.size(); i++) {
            if(sp == sList.size()) {
                break;
            }
            while(sp < sList.size()) {
                if(eList.get(i).end > sList.get(sp).start) {
                    sList.get(sp).setRoomNum(k);
                    k += 1;
                    sp += 1;
                } else {
                    sList.get(sp).setRoomNum(eList.get(i).getRoomNum());
                    sp += 1;
                    break;
                }
            }
        }
        return k -= 1;
    }
}