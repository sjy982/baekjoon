import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int eachMin = 1001;
    static int packMin = 1001;
    static int answer = 0;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i=0; i<M; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int pack = Integer.parseInt(st2.nextToken());
            int each = Integer.parseInt(st2.nextToken());
            packMin = Math.min(pack, packMin);
            eachMin = Math.min(each, eachMin);
        }
        
        if(N >= 6) {
            if(packMin < eachMin * 6) {
                answer += N / 6 * packMin;
                int leftN = N % 6;
                if(leftN > 0) {
                    answer += Math.min(packMin, eachMin * leftN);
                }
            } else {
                answer += eachMin * N;
            }
        } else {
            answer += Math.min(packMin, eachMin * N);
        }
        System.out.println(answer);
    }
}