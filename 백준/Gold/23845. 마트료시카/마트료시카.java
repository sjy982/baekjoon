import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] list;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(list);
        int[] c = new int[100002];
        for (int i = 0; i < list.length; i++) {
            c[list[i]] += 1;
        }
        long answer = 0;
        for (int i = 0; i < list.length; i++) {
            answer += cal(list[i], c);
        }
        System.out.println(answer);
    }

    static long cal(int v, int[] c) {
        if (c[v] > 0) {
            c[v] -= 1;
            int T = 1;
            int Q = v;
            while (true) {
                if (c[Q + 1] > 0) {
                    c[Q + 1] -= 1;
                    T += 1;
                    Q += 1;
                } else {
                    break;
                }
            }
            return (long) T * (long) Q;

        }
        return 0;
    }
}