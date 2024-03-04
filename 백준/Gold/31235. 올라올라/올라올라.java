import java.util.*;
import java.io.*;

public class Main {
    static int[] A;
    static int max = -1;
    static int maxInd = -1;
    static int K = 1;
    static int N;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        int i = 0;
        while(i < N - K + 1) {
            if(max <= A[i + K - 1]) {
                max = A[i + K - 1];
                maxInd = i + K - 1;
                i += 1;
            } else {
                if(i > maxInd) {
                    //포함되어 있지 않으면 K 값을 늘린다.
                    K += 1;
                } else {
                    //포함되어 있다면
                    i+= 1;
                }
            }
        }
        System.out.println(K);
    }
}