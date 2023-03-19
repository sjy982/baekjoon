import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static int set[];
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        set = new int[N+1];
        for(int i=0; i<=N; i++) set[i] = i;
        for(int i=0; i<M; i++) {
            StringTokenizer n_st = new StringTokenizer(br.readLine());
            int operation = Integer.parseInt(n_st.nextToken());
            int operand1 = Integer.parseInt(n_st.nextToken());
            int operand2 = Integer.parseInt(n_st.nextToken());
            if(operation == 0) {
                //union
                union(operand1, operand2);
            } else if(operation == 1) {
                //tlp_find
                int tlp1 = tlp_find(operand1);
                int tlp2 = tlp_find(operand2);
                if(tlp1 == tlp2) sb.append("YES\n");
                else sb.append("NO\n");
            }
        }
        System.out.println(sb.toString().trim());
    }
    
    static void union(int oprand1, int oprand2) {
        int tlp_op1 = tlp_find(oprand1);
        int tlp_op2 = tlp_find(oprand2);
        if(tlp_op1 != tlp_op2) {
            //서로 다른 집합이기 때문에 합쳐준다.
            //합쳐주는 기준은 딱히 없고 op1집합의 op2집합을 넣어줌
            set[tlp_op2] = set[tlp_op1];
        }
    }
    
    static int tlp_find(int oprand) {
        if(oprand == set[oprand]) {
            return oprand;
        }
        return tlp_find(set[oprand]);
    }
}