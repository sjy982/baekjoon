import java.io.*;
import java.util.*;

public class Main {
    static int N,S;
    static int f_sn[];
    static int s_sn[];
    static Hashtable<Integer, Long> f_sum = new Hashtable<>();
    static Hashtable<Integer, Long> s_sum = new Hashtable<>();
    static long ans = 0;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        f_sn = new int[N/2];
        s_sn = new int[N-N/2];
        StringTokenizer n_st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            if(i<f_sn.length) f_sn[i] = Integer.parseInt(n_st.nextToken());
            else s_sn[i-f_sn.length] = Integer.parseInt(n_st.nextToken());
        }
        f_sum.put(0, 1L); //공집합
        s_sum.put(0, 1L); //공집합
        DFS(f_sn, 0, 0, f_sum);
        DFS(s_sn, 0, 0, s_sum);
        Iterator<Integer> keys = f_sum.keySet().iterator();
        while(keys.hasNext()) {
            int key = keys.next();
            int s_key = S - key;
            if(s_sum.get(s_key) != null) ans += f_sum.get(key) * s_sum.get(s_key);
        }
        if(S==0) ans -= 1; //S가 0이면 공집합 + 공집합 경우의 수도 추가됨 크기가 양수이기 때문에 -1
        System.out.println(ans);
    }
    
    static void DFS(int sn[], int ind, int sum, Hashtable<Integer, Long> table) {
        if(sn.length == ind) return;
        for(int i=ind; i<sn.length; i++) {
            int n_sum = sum + sn[i];
            if(table.get(n_sum)==null) table.put(n_sum, 1L);
            else table.replace(n_sum, table.get(n_sum)+1);
            DFS(sn, i+1, n_sum, table);
        }
    }
}