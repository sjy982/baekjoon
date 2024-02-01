import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] line;
    static Integer[] dif;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        line = new int[N];
        dif = new Integer[N - 1];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            line[i] = Integer.parseInt(st2.nextToken());
        }
        if(N == 1) {
            System.out.println(0);
        } else {
            for(int i=0; i<N - 1; i++) {
                dif[i] = line[i + 1] - line[i];
            }
            Arrays.sort(dif, Collections.reverseOrder());
            int ck = 1;
            while(ck < K) {
                dif[ck - 1] = 0;
                ck += 1;
            }
            int answer = 0;
            for(int i=0; i<dif.length; i++) {
                answer += dif[i];
            }
            System.out.println(answer);
        }
    }
}