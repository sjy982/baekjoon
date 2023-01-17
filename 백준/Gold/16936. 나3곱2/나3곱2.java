import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList < Long > b_sn = new ArrayList < > ();
    static Stack < Long > a_sn = new Stack < > ();
    static boolean end = false;
    static StringBuilder ans = new StringBuilder();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            b_sn.add(Long.parseLong(st.nextToken()));
        }
        for (int i = 0; i < N; i++) {
            a_sn.push(b_sn.get(i));
            DFS();
            a_sn.pop();
            if (end) break;
        }
        System.out.println(ans.toString().trim());
    }

    static void DFS() {
        if (a_sn.size() == N) {
            end = true;
            for (int i = 0; i < N; i++) {
                ans.append(String.valueOf(a_sn.get(i)) + " ");
            }
            return;
        }
        long n = a_sn.peek();
        long gob2 = n * 2;
        if (b_sn.contains(gob2) && !a_sn.contains(gob2)) {
            a_sn.push(gob2);
            DFS();
            a_sn.pop();
        }
        if (n % 3 == 0 && !end) {
            long na3 = n / 3;
            if (b_sn.contains(na3) && !a_sn.contains(na3)) {
                a_sn.push(na3);
                DFS();
                a_sn.pop();
            }
        }
    }
}