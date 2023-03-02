import java.io.*;
import java.util.*;
class Node {
    int x,y,c;
    Node(int x, int y, int c) {
        this.x = x;
        this.y = y;
        this.c = c;
    }
}
public class Main {
    static final int dx[] = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int dy[] = {0, -1, -1, -1, 0, 1, 1, 1};
    static int N,M;
    static int space[][];
    static int ans = -1;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        space = new int[N][M];
        for(int i=0; i<N; i++) {
            StringTokenizer n_st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                space[i][j] = Integer.parseInt(n_st.nextToken());
            }
        }
        int clone_space[][] = new int[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(space[i][j]==0) {
                    deep_clone(clone_space);
                    BFS(new Node(j, i, 0), clone_space);
                }
            }
        }
        System.out.println(ans);
    }
    static void BFS(Node start, int sp[][]) {
        Queue<Node> que = new LinkedList<>();
        que.add(start);
        sp[start.y][start.x] = 2; //2는 방문했다는 의미
        while(que.size()!=0) {
            Node n = que.poll();
            if(space[n.y][n.x]==1) {
                ans = Math.max(ans, n.c);
                return;
            }
            for(int i=0; i<8; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if((nx>=0 && nx<=M-1) && (ny>=0 && ny<=N-1)) {
                    if(sp[ny][nx]!=2) {
                        que.add(new Node(nx, ny, n.c+1));
                        sp[ny][nx] = 2;
                    }
                }
            }
        }
    }
    static void deep_clone(int target[][]) {
        for(int i=0; i<N; i++) {
            target[i] = space[i].clone();
        }
    }
}