import java.io.*;
import java.util.*;

class Coordinate {
    int x,y;
    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N,M;
    static int board[][];
    static final int dx[] = {-1, 0, 1, 0};
    static final int dy[] = {0, -1, 0, 1};
    static int ans = 0;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for(int i=0; i<N; i++) {
            StringTokenizer n_st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                board[i][j] = Integer.parseInt(n_st.nextToken());
            }
        }
        DFS(0);
        System.out.println(ans);
    }
    
    static void DFS(int depth) {
        if(depth == 2) {
            ans = Math.max(ans, count_baduk());
            return;
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(board[i][j]==0) {
                    board[i][j] = 1;
                    DFS(depth+1);
                    board[i][j] = 0;
                }
            }
        }
    }
    static int count_baduk() {
        int ac = 0; //전체 개수
        boolean visited[][] = new boolean[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(board[i][j] == 2 && !visited[i][j]) {
                    int c = 1;
                    boolean isPoss = true;
                    Queue<Coordinate> que = new LinkedList<>();
                    que.add(new Coordinate(j,i));
                    visited[i][j] = true;
                    while(que.size()!=0) {
                        Coordinate n = que.poll();
                        for(int k=0; k<4; k++) {
                            int nx = n.x + dx[k];
                            int ny = n.y + dy[k];
                            if((nx>=0 && nx<=M-1) && (ny>=0 && ny<=N-1)) {
                                if(board[ny][nx] == 2 && !visited[ny][nx]) {
                                    que.add(new Coordinate(nx, ny));
                                    c += 1;
                                    visited[ny][nx] = true;
                                } else if(board[ny][nx] == 0) isPoss = false;
                            }
                        }
                    }
                    if(isPoss) ac += c;
                }
            }
        }
        return ac;
    }
}