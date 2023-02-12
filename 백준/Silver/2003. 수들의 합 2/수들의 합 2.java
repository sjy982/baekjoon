import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long M;
    static long arr[];
    static int ans = 0;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        arr = new long[N];
        StringTokenizer n_st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Long.parseLong(n_st.nextToken());
        }
        for(int i=0; i<N; i++) {
            long sum = 0;
            for(int j=i; j<N; j++) {
                sum += arr[j];
                if(sum >= M) {
                    if(sum == M) ans += 1;
                    break;
                }
            }
        }
        System.out.println(ans);
    }
}