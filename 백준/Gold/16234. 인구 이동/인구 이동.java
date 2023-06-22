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
    static int N, L, R;
    static int[][] land;
    static boolean[][] visited;
    static int answer = 0;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();
        land = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                land[i][j] = sc.nextInt();
            }
        }
        while(true) {
            visited = new boolean[N][N];
            boolean isMove = false;
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(!visited[i][j]) {
                        if(BFS(new Node(j, i)) && !isMove) isMove = true;
                    }
                }
            }
            if(isMove) answer += 1;
            else break;
        }
        System.out.println(answer);
    }
    
    static boolean BFS(Node start) {
        Queue<Node> que = new LinkedList<>();
        ArrayList<Node> union_list = new ArrayList<>();
        que.add(start);
        union_list.add(start);
        int sum = land[start.y][start.x];
        boolean move = false;
        visited[start.y][start.x] = true;
        while(que.size() != 0) {
            Node n = que.poll();
            for(int i=0; i<4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if(check_range(nx, ny) && !visited[ny][nx]) {
                    int dif = Math.abs(land[n.y][n.x] - land[ny][nx]);
                    if(L <= dif && dif <= R) {
                        union_list.add(new Node(nx, ny));
                        que.add(new Node(nx, ny));
                        sum += land[ny][nx];
                        visited[ny][nx] = true;
                        if(!move) move = true;
                    }
                }
            }
        }
        for(int i=0; i<union_list.size(); i++) {
            land[union_list.get(i).y][union_list.get(i).x] = sum / union_list.size();
        }
        return move;
    }
    
    static boolean check_range(int x, int y) {
        if((0<= x && x <= N-1) && (0 <= y && y <= N-1)) return true;
        return false;
    }
}