import java.io.*;
import java.util.*;

class Node {
    int v,c;
    Node(int v, int c) {
        this.v = v;
        this.c = c;
    }
}

public class Main {
    static int F, S, G, U, D;
    static boolean visited[];
    static int ans;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        visited = new boolean[F+1];
        if(BFS()) {
            System.out.println(ans);
        } else {
            System.out.println("use the stairs");
        }
    }
    static boolean BFS() {
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(S, 0));
        visited[S] = true;
        while(que.size()!=0) {
            Node n = que.poll();
            if(n.v == G) {
                ans = n.c;
                return true;
            }
            int n_u = n.v + U;
            int n_d = n.v - D;
            if(n_u<=F && !visited[n_u]) {
                que.add(new Node(n_u, n.c+1));
                visited[n_u] = true;
            }
            if(n_d>=1 && !visited[n_d]) {
                que.add(new Node(n_d, n.c+1));
                visited[n_d] = true;
            }
        }
        return false;
    }
}