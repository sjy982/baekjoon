import java.io.*;
import java.util.*;

class NumInfo implements Comparable < NumInfo > {
    long v;
    int pv[];
    public NumInfo(long v, int pv[]) {
        this.v = v;
        this.pv = pv;
    }

    public int compareTo(NumInfo n) {
        int spv[] = this.pv.length <= n.pv.length ? this.pv : n.pv;
        for (int i = 0; i < spv.length; i++) {
            int df = n.pv[i] - this.pv[i];
            if (df > 0) return 1;
            if (df < 0) return -1;
        }
        String tpv = String.valueOf(this.v) + String.valueOf(n.v);
        String npv = String.valueOf(n.v) + String.valueOf(this.v);
        for(int i=0; i<tpv.length(); i++) {
            int tv = tpv.charAt(i) - '0';
            int nv = npv.charAt(i) - '0';
            if(tv > nv) return -1;
            if(tv < nv) return 1;
        }
        return 0;
    }
}

public class Main {
    static int N, K;
    static NumInfo list[];
    static NumInfo max = new NumInfo(-1, new int[0]);
    static StringBuilder sb = new StringBuilder();
    static int insert_ind = -1;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        list = new NumInfo[N];
        for (int i = 0; i < K; i++) {
            String s = br.readLine();
            int pv[] = new int[s.length()];
            for (int j = 0; j < pv.length; j++) {
                pv[j] = s.charAt(j) - '0';
            }
            long v = Long.parseLong(s);
            list[i] = new NumInfo(v, pv);
            if(max.v < v) max = list[i];
        }
        for(int i = K; i<N; i++) {
            list[i] = new NumInfo(max.v, max.pv);
        }
        Arrays.sort(list);
        for (int i = 0; i < N; i++) {
            sb.append(list[i].v);
        }
        System.out.println(sb.toString());
    }
}