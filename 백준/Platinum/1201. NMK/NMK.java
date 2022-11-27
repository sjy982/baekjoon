import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static boolean end = false;
    static StringBuilder ans = new StringBuilder();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if ((M + K - 1 <= N) && (N <= M * K)) {
            for (int i = K; i >= 1; i--) {
                ans.append(i);
                ans.append(" ");
            }
            while (!end) {
                Stack<Integer> stack = new Stack<>();
                if(N < N - (M - 2)) end = true;
                for (int i = N; i >= N - (M - 2); i--) {
                    if(K == i) {
                        end = true;
                        break;
                    }
                    stack.push(i);
                }
                while(stack.size() != 0) {
                    ans.append(stack.pop());
                    ans.append(" ");
                }
                N = N - (M - 2) - 1;
            }
            System.out.println(ans.toString().trim());
        } else System.out.println(-1);
    }
}