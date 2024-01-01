import java.io.*;
import java.util.*;

class Node {
    int x, y;
    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};
    static int T;
    static int M, N, K;
    static int[][] field;
    static boolean[][] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken()); // 가로
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            field = new int[N][M];
            visited = new boolean[N][M];
            for(int j=0; j<K; j++) {
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st2.nextToken());
                int y = Integer.parseInt(st2.nextToken());
                field[y][x] = 1;
            }
            int cout = 0;
            for(int k=0; k<N; k++) {
                for(int l=0; l<M; l++) {
                    if(field[k][l] == 1 && !visited[k][l]) {
                        bfs(new Node(l, k));
                        cout += 1;
                    }
                }
            }
            sb.append(cout).append("\n");
        }
        System.out.println(sb.toString().trim());
    }
    
    static void bfs(Node start) {
        Queue<Node> que = new LinkedList<>();
        que.add(start);
        visited[start.y][start.x] = true;
        while(que.size() != 0) {
            Node n = que.poll();
            for(int i=0; i<4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if(checkRange(nx, ny) && !visited[ny][nx] && field[ny][nx] == 1) {
                    que.add(new Node(nx, ny));
                    visited[ny][nx] = true;
                }
            }
        }
    }
    
    static boolean checkRange(int x, int y) {
        if((0 <= x && x <= M - 1) && (0 <= y && y <= N-1)) {
            return true;
        }
        return false;
    }
}