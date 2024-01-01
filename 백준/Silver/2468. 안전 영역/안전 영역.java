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
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int N;
    static int max = 0;
    static int[][] map;
    static boolean[][] visited;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i=0; i<=100; i++) {
            visited = new boolean[N][N];
            int cout = 0;
            for(int j=0; j<N; j++) {
                for(int k=0; k<N; k++) {
                    if(map[j][k] > i && !visited[j][k]) {
                        bfs(new Node(k, j), i);
                        cout += 1;
                    }
                }
            }
            max = Math.max(max, cout);
        }
        System.out.println(max);
    }
    
    static void bfs(Node start, int rain) {
        Queue<Node> que = new LinkedList<>();
        que.add(start);
        visited[start.y][start.x] = true;
        while(que.size() != 0) {
            Node n = que.poll();
            for(int i=0; i<4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if(checkRange(nx, ny) && map[ny][nx] > rain && !visited[ny][nx]) {
                    que.add(new Node(nx, ny));
                    visited[ny][nx] = true;
                }
            }
        }
    }
    
    static boolean checkRange(int x, int y) {
        if((0 <= x && x <= N - 1) && (0 <= y && y <= N-1)) {
            return true;
        }
        return false;
    }
}