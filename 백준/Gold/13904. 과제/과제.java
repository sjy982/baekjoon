import java.util.*;
import java.io.*;

class Assignment implements Comparable<Assignment> {
    int d, s;
    Assignment(int d, int s) {
        this.d = d;
        this.s = s;
    }
    
    public int compareTo(Assignment o) {
        if(this.s < o.s) {
            return 1;
        } else if(this.s > o.s) {
            return -1;
        }
        return 0;
    }
    
    
}

public class Main {
    static int N;
    static Assignment[] list;
    static int[] answer = new int[1001];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new Assignment[N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            list[i] = new Assignment(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(list);
        for(int i=0; i<N; i++) {
            makeSchedule(list[i]);
        }
        int sum = 0;
        for(int i=1; i<answer.length; i++) {
            sum += answer[i];
        }
        System.out.println(sum);
    }
    
    static void makeSchedule(Assignment a) {
        int cursor = a.d;
        while(cursor > 0) {
            if(answer[cursor] == 0) {
                answer[cursor] = a.s;
                return;
            } 
            cursor -= 1;
        }
    }
}