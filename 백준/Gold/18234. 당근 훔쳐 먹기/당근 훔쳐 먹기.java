import java.io.*;
import java.util.*;

class Carrot implements Comparable<Carrot> {
    long w, p;
    Carrot(long w, int p) {
        this.w = w;
        this.p = p;
    } 
    
    @Override
    public int compareTo(Carrot o) {
        if(this.p < o.p) {
            return -1;
        } else if(this.p > o.p) {
            return 1;
        }
        return 0;
    }
}

public class Main {
    static int N, T;
    static Carrot[] list;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        list = new Carrot[N];
        for(int i=0; i<N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            list[i] = new Carrot(Long.parseLong(st2.nextToken()), Integer.parseInt(st2.nextToken()));
        }
        Arrays.sort(list);
        for(int i=0; i<N; i++) {
            list[i].w += (long) (T - N - 1) * (long) list[i].p;
        }
        long answer = 0;
        for(int i=0; i<N; i++) {
            answer += list[i].w + ((i + 1) * (long) list[i].p);
        }
        System.out.println(answer);
    }
}