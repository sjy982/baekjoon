import java.io.*;
import java.util.*;

public class Main {
    static long T;
    static long A[]; //누적합
    static long B[]; //누적합
    static int N,M;
    static ArrayList<Long> ps_A = new ArrayList<>(); //부분합 list
    static Hashtable<Long, Integer> ps_B = new Hashtable<>(); //부분합 list
    static long ans = 0;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Long.parseLong(br.readLine());
        N = Integer.parseInt(br.readLine());
        A = new long[N];
        StringTokenizer n_st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            if(i==0) A[i] = Long.parseLong(n_st.nextToken());
            else A[i] = A[i-1] + Long.parseLong(n_st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        B = new long[M];
        StringTokenizer m_st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            if(i==0) B[i] = Long.parseLong(m_st.nextToken());
            else B[i] = B[i-1] + Long.parseLong(m_st.nextToken());
        }
        for(int i=0; i<N; i++) {
            ps_A.add(A[i]);
            for(int j=0; j<i; j++) {
                ps_A.add(A[i]-A[j]);
            }
        }
        for(int i=0; i<M; i++) {
            if(ps_B.get(B[i])==null) ps_B.put(B[i],1);
            else ps_B.replace(B[i], ps_B.get(B[i])+1);
            for(int j=0; j<i; j++) {
                long pv = B[i] - B[j];
                if(ps_B.get(pv)==null) ps_B.put(pv, 1);
                else ps_B.replace(pv, ps_B.get(pv)+1);
            }
        }
        for(int i=0; i<ps_A.size(); i++) {
            long v = T - ps_A.get(i);
            if(ps_B.get(v)!=null) ans += ps_B.get(v);
        }
        System.out.println(ans);
    }
}