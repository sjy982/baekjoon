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
        long tpv = Long.parseLong(String.valueOf(this.v) + String.valueOf(n.v));
        long npv = Long.parseLong(String.valueOf(n.v) + String.valueOf(this.v));
        if (tpv > npv) return -1;
        if (tpv < npv) return 1;
        return 0;
    }
}

public class Main {
    static int N;
    static NumInfo list[];
    static StringBuilder ans = new StringBuilder();
    static boolean all_zero = true;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new NumInfo[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            String s = st.nextToken();
            int pv[] = new int[s.length()];
            for (int j = 0; j < pv.length; j++) {
                pv[j] = s.charAt(j) - '0';
            }
            long v = Long.parseLong(s);
            if (v != 0) all_zero = false;
            list[i] = new NumInfo(v, pv);
        }

        if (all_zero) System.out.println(0);
        else {
            Arrays.sort(list);
            for (int i = 0; i < N; i++) {
                ans.append(list[i].v);
            }
            System.out.println(ans.toString());
        }
    }
}