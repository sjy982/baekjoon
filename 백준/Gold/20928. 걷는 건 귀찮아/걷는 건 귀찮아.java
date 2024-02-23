import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] p, x;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        p = new int[N];
        x = new int[N];
        for(int i=0; i<2; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                if(i == 0) {
                    p[j] = Integer.parseInt(st2.nextToken());
                } else {
                    x[j] = Integer.parseInt(st2.nextToken());
                }
            }
        }
        int max = p[0] + x[0];
        int ind = 1;
        int cout = 0;
        while(max < M) {
            int bMax = max;
            for(int i = ind; i<N; i++) {
                if(bMax >= p[i]) {
                    max = Math.max(max, p[i] + x[i]);
                } else {
                    ind = i;
                    break;
                }
            }
            if(bMax >= max) {
                cout = -1;
                break;
            }
            cout += 1;
        }
        System.out.println(cout);
    }
}