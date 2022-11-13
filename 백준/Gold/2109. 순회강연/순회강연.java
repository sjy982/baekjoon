import java.io.*;
import java.util.*;

class Lecture implements Comparable<Lecture> {
    int p,d;
    public Lecture(int p, int d) {
        this.p = p;
        this.d = d;
    }
    
    @Override
    public int compareTo(Lecture l) {
        int dif = l.p - this.p;
        if(dif>0) return 1;
        if(dif<0) return -1;
        return 0;
    }
}

public class Main {
    static int N;
    static int ans = 0;
    static boolean schedule[] = new boolean[10001];
    static Lecture lec[];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        lec = new Lecture[N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int lp = Integer.parseInt(st.nextToken());
            int ld = Integer.parseInt(st.nextToken());
            lec[i] = new Lecture(lp, ld);
        }
        Arrays.sort(lec);
        for(int i=0; i<N; i++) {
            if(find_schedule(lec[i].d)) {
                ans += lec[i].p;
            }
        }
        System.out.println(ans);
    }
    
    static boolean find_schedule(int s) {
        for(int i=s; i>=1; i--) {
            if(!schedule[i]) {
                schedule[i] = true;
                return true;
            }
        }
        return false;
    }
}