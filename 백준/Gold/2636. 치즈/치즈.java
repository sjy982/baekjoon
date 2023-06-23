import java.util.*;
import java.io.*;
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
    static int W, H;
    static int[][] board;
    static int cheese = 0;
    static int answer_time = 0;
    static int answer_left = 0;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        H = sc.nextInt();
        W = sc.nextInt();
        board = new int[H][W]; //외부 공간
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                board[i][j] = sc.nextInt();
                if(board[i][j] == 1) cheese += 1;
            }
        }
        while(true) {
            if(cheese == 0) break;
            BFS();
        }
        System.out.println(answer_time);
        System.out.println(answer_left);
    }
    
    static void BFS() {
        Queue<Node> que = new LinkedList<>();
        boolean[][] visited = new boolean[H][W];
        que.add(new Node(0, 0));
        visited[0][0] = true;
        ArrayList<Node> c_list = new ArrayList<>();
        while(que.size() != 0) {
            Node n = que.poll();
            for(int i=0; i<4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if(check_range(nx, ny) && !visited[ny][nx]) {
                    if(board[ny][nx] == 1) c_list.add(new Node(nx, ny));
                    else que.add(new Node(nx, ny));
                    visited[ny][nx] = true;
                }
            }
        }
        answer_time += 1;
        remove_cheese(c_list);
        if(cheese - c_list.size() == 0) answer_left = cheese;
        cheese -= c_list.size();
        
    }
    
    static void remove_cheese(ArrayList<Node> c_list) {
        for(int i=0; i<c_list.size(); i++) board[c_list.get(i).y][c_list.get(i).x] = 0;
    }
    
    static boolean check_range(int x, int y) {
        if((0 <= x && x <= W-1) && (0 <= y && y <= H-1)) return true;
        return false;
    }
}