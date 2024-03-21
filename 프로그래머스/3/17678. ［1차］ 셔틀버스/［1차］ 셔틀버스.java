import java.io.*;
import java.util.*;
class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        Queue<Integer> que = new LinkedList<>();
        for(int i=0; i<timetable.length; i++) {
            que.add(parseMin(timetable[i]));
        }
        que = asc(que);
        
        int i=0;
        int fstBus = 540;
        int answerMin = 0;
        while(true) {
            int cout = 0;
            int last = 0;
            while(que.size() != 0 && que.peek() <= fstBus + i * t) {
                int minTime = que.poll();
                cout += 1;
                if(cout == m) {
                    last = minTime;
                    break;
                } 
            }
            
            if(i + 1 == n) {
                System.out.println(cout);
                if(cout < m) {
                    answerMin = fstBus + i * t;
                } else {
                    answerMin = last - 1;
                }
                break;
            }
            i += 1;
        }
        return parseStrTime(answerMin);
    }
    
    static int parseMin(String strTime) {
        int hour = Integer.parseInt(strTime.split(":")[0]);
        int min = Integer.parseInt(strTime.split(":")[1]);
        return hour * 60 + min;
    } 
    
    static String parseStrTime(int time) {
        String hour = String.valueOf(time/60).length() == 1 ? "0" + String.valueOf(time/60) : String.valueOf(time/60);
        String min = String.valueOf(time%60).length() == 1 ? "0" + String.valueOf(time%60) : String.valueOf(time%60);
        return hour + ":" + min;
    }
    
    static Queue<Integer> asc(Queue<Integer> que) {
        List<Integer> list = new ArrayList<>(que);
        Collections.sort(list);
        return new LinkedList<>(list);
    }
}