import java.io.*;
import java.util.*;

class Pipe {
    int x, y, s;
    Pipe(int x, int y, int s) {
        this.x = x;
        this.y = y;
        this.s = s;
    }
}

public class Main {
    static final int NOC[][] = {{0,2}, {1,2}, {0,1,2}};//0은 가로, 1은 세로, 2는 대각선
    static int N;
    static int board[][];
    static long weight[][][];
    static boolean visited[][][];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        weight = new long[N][N][3];
        visited = new boolean[N][N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        DFS(new Pipe(1, 0, 0));
        System.out.println(weight[0][1][0]);
    }

    static long DFS(Pipe p) {
        if ((p.x == N - 1) && (p.y == N - 1)) {
            weight[p.y][p.x][p.s] = 1;
            return 1;
        }
        long sum = 0;
        for (int i = 0; i < NOC[p.s].length; i++) {
            if (NOC[p.s][i] == 0) {
                Pipe np = new Pipe(p.x+1, p.y, 0);
                if((np.x>=0 && np.x<=N-1) && (board[np.y][np.x] != 1)) {
                    if(!visited[np.y][np.x][np.s]) {
                        sum += DFS(np);
                        visited[np.y][np.x][np.s] = true;
                    } else sum += weight[np.y][np.x][np.s];
                }
            } else if (NOC[p.s][i] == 1) {
                Pipe np = new Pipe(p.x, p.y+1, 1);
                if((np.y>=0 && np.y<=N-1) && (board[np.y][np.x] != 1)) {
                    if(!visited[np.y][np.x][np.s]) {
                        sum += DFS(np);
                        visited[np.y][np.x][np.s] = true;
                    } else sum += weight[np.y][np.x][np.s];
                }

            } else if (NOC[p.s][i] == 2) {
                Pipe np = new Pipe(p.x+1, p.y+1, 2);
                if((np.x>=0 && np.x<=N-1) && (np.y>=0 && np.y<=N-1) && (board[np.y][np.x] != 1 && board[np.y-1][np.x] != 1 && board[np.y][np.x-1] != 1)) {
                    if(!visited[np.y][np.x][np.s]) {
                        sum += DFS(np);
                        visited[np.y][np.x][np.s] = true;
                    } else sum += weight[np.y][np.x][np.s];
                }
            }
        }
        weight[p.y][p.x][p.s] = sum;
        return sum;
    }
}