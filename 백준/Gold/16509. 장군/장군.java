import java.io.*;
import java.util.*;

class Node {
    int x, y, c;
    Node(int x, int y, int c) {
        this.x = x;
        this.y = y;
        this.c = c;
    }
}

public class Main {
    static final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static final int[][] direct = {
        {0, 7, 7}, 
        {0, 1, 1},
        {2, 1, 1},
        {2, 3, 3},
        {4, 3, 3},
        {4, 5, 5},
        {6, 5, 5},
        {6, 7, 7}
    };
    static Node king;
    static boolean[][] visited = new boolean[10][9];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int sy = Integer.parseInt(st1.nextToken());
        int sx = Integer.parseInt(st1.nextToken());
        Node sang = new Node(sx, sy, 0);
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int ky = Integer.parseInt(st2.nextToken());
        int kx = Integer.parseInt(st2.nextToken());
        king = new Node(kx, ky, 0);
        System.out.println(bfs(sang));
    }
    
    static int bfs(Node start) {
        Queue<Node> que = new LinkedList<>();
        que.add(start);
        visited[start.y][start.x] = true;
        while(que.size() != 0) {
            Node n = que.poll();
            if(n.x == king.x && n.y == king.y) {
                return n.c;
            }
            for(int i=0; i<8; i++) {
                Node mv = new Node(n.x, n.y, n.c + 1);
                boolean isPosi = true;
                for(int j=0; j<direct[i].length; j++) {
                    int nx = mv.x + dx[direct[i][j]];
                    int ny = mv.y + dy[direct[i][j]];
                    if(!checkRange(nx, ny)) {
                        isPosi = false;
                        break;
                    }
                    if(j != 2 && (nx == king.x && ny == king.y)) {
                        isPosi = false;
                        break;
                    }
                    mv = new Node(nx, ny, mv.c);
                }
                if(isPosi && !visited[mv.y][mv.x]) {
                    que.add(mv);
                    visited[mv.y][mv.x] = true;
                }
                
            }
        }
        return -1;
    }
    
    static boolean checkRange(int x, int y) {
        if((0 <= x && x <= 8) && (0 <= y && y <= 9)) {
            return true;
        }
        return false;
    }
}
