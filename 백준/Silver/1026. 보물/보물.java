import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static Integer[] A;
    static Integer[] B;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new Integer[N];
        B = new Integer[N];
        for(int i=0; i<2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                if(i == 0) {
                    A[j] = Integer.parseInt(st.nextToken());
                } else {
                    B[j] = Integer.parseInt(st.nextToken());
                }
            }
        }
        Arrays.sort(A);
        Arrays.sort(B, Collections.reverseOrder());
        int answer = 0;
        for(int i=0; i<N; i++) {
            answer += A[i] * B[i];
        }
        System.out.println(answer);
    }
}