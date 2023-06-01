import java.util.*;
class Node implements Comparable<Node> {
    int x, y, w, dir;
    Node(int x, int y, int w, int dir) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.dir = dir;
    }
    public int compareTo(Node o) {
        return this.w - o.w;
    }
}
class Solution {
    static final int[] dx = {-1, 0, 1, 0}; //좌 상 우 하
    static final int[] dy = {0, -1, 0, 1};
    static int H, W;
    static int[][][] dp;
    static boolean[][][] visited;
    public int solution(int[][] board) {
        int answer = 0;
        H = board.length;
        W = board[0].length;
        dp = new int[H][W][4];
        visited = new boolean[H][W][4];
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) Arrays.fill(dp[i][j], Integer.MAX_VALUE);
        }
        dijkstra(board);
        return Math.min(dp[H-1][W-1][2], dp[H-1][W-1][3]);
    }
    
    static void dijkstra(int[][] board) {
        PriorityQueue<Node> pque = new PriorityQueue<>();
        dp[0][0][2] = 0;
        dp[0][0][3] = 0;
        pque.add(new Node(0, 0, 0, 2)); //우
        pque.add(new Node(0, 0, 0, 3)); //하
        while(!pque.isEmpty()) {
            Node n = pque.poll();
            if(visited[n.y][n.x][n.dir]) continue;
            visited[n.y][n.x][n.dir] = true;
            for(int i=0; i<4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if((0<=nx && nx<=W-1) && (0<=ny && ny<=H-1)) {
                    if(board[ny][nx] == 0) {
                        int next_w = n.w;
                        if(n.dir == 0 || n.dir == 2) {
                            //좌우로 진행중 일 때
                            if(i == 0 || i == 2) next_w += 100; //직선
                            else next_w += 600; //코너
                        } else if(n.dir == 1 || n.dir == 3){
                            //상하면 진행중 일 때
                            if(i == 0 || i == 2) next_w += 600; //코너
                            else next_w += 100; //직선
                        }
                        if(dp[ny][nx][i] > next_w) {
                            dp[ny][nx][i] = next_w;
                            pque.add(new Node(nx, ny, next_w, i));
                        }
                    }
                }
            }
        }
    }
}