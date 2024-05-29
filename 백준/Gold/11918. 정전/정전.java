import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long L;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Long.parseLong(st.nextToken());
        
        long[] list = new long[N];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        
        for(int i=0; i<N; i++) {
            list[i] = Long.parseLong(st2.nextToken());
        }
        
        Arrays.sort(list);
        
        System.out.println(answer(list));
    }
    
    static long answer(long[] list) {
        long cnt = 0;
        long last = Long.MIN_VALUE;
        for(int i=0; i<N -1; i++) {
            long curMax = list[i] + L;
            
            long nextMin = list[i + 1] - L;
            if(nextMin < last) {
                nextMin = last;
            }
            if(nextMin < curMax) {
                cnt += curMax - nextMin;
                last = curMax;
            }
            
        }
        return cnt;
    }
}