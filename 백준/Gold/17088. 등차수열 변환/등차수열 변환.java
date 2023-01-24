import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long B[];
    static long w[] = {0,-1,1};
    static long count = 100001;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        B = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i] = Long.parseLong(st.nextToken());
        }
        if (N <= 2) System.out.println(0);
        else {
            for (int i = 0; i < w.length; i++) {
                long fn = B[0] + w[i];
                for (int j = 0; j < w.length; j++) {
                    long sn = B[1] + w[j];
                    long n_cout = check(sn ,fn-sn);
                    if(n_cout != -1) {
                        n_cout += Math.abs(w[i]) + Math.abs(w[j]);
                        if(count > n_cout) count = n_cout;
                    }
                }
            }
            if(count == 100001) System.out.println(-1);
            else System.out.println(count);
        }
    }
    
    static long check(long bn, long d) {
        long cout = 0;
        for(int i=2; i<N; i++) {
            boolean ctn = false;
            for(int j=0; j<w.length; j++) {
                if(bn-(B[i]+w[j]) == d) {
                    bn = B[i]+w[j];
                    cout += Math.abs(w[j]);
                    ctn = true;
                    break;
                }
            }
            if(!ctn) return -1;
        }
        return cout;
    }
}