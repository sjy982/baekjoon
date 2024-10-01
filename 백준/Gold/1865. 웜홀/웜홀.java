import java.io.*;
import java.util.*;

class Node {
    int v, w;
    Node(int v, int w) {
        this.v = v;
        this.w = w;
    }
}

public class Main {
    static final int MAX = 10000000;
    static int TC;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TC = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < TC; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            int[] dp = new int[N + 1];
            ArrayList < ArrayList < Node >> graph = new ArrayList < > ();

            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList < > ());
            }

            for (int i = 0; i < M; i++) {
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st2.nextToken());
                int b = Integer.parseInt(st2.nextToken());
                int w = Integer.parseInt(st2.nextToken());

                graph.get(a).add(new Node(b, w));
                graph.get(b).add(new Node(a, w));
            }

            for (int i = 0; i < W; i++) {
                StringTokenizer st3 = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st3.nextToken());
                int e = Integer.parseInt(st3.nextToken());
                int w = Integer.parseInt(st3.nextToken());

                graph.get(s).add(new Node(e, w * (-1)));
            }
            
            
            if(bellman(graph, dp, 1, N)) {
                sb.append("YES");
            } else {
                sb.append("NO");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString().trim());
    }

    static boolean bellman(ArrayList < ArrayList < Node >> graph, int[] dp, int s, int N) {
        dp[s] = 0;
        for (int i = 0; i < N; i++) {
            boolean update = false;
            for (int j = 1; j <= N; j++) {
                for (int k = 0; k < graph.get(j).size(); k++) {
                    Node n = graph.get(j).get(k);
                    if (dp[n.v] > dp[j] + n.w) {
                        dp[n.v] = dp[j] + n.w;
                        if (!update) {
                            update = true;
                        }
                    }
                }
            }
            if (i == (N - 1)) {
                return update;
            }
        }
        return false;
    }
    
    static void initDp(int[] dp) {
        for(int i=1; i<dp.length; i++) {
            dp[i] = MAX;
        }
    }
}