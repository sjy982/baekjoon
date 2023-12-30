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
    static boolean[][] visited;
    static int[][] map;
    static int N;
    static ArrayList<Integer> arrList = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        for(int i=0; i<N; i++) {
            String inputStr = new String(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = inputStr.charAt(j) - '0';
            }
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] == 1 && !visited[i][j]) {
                    arrList.add(bfs(new Node(j, i)));
                }
            }
        }
        sb.append(arrList.size()).append("\n");
        Collections.sort(arrList);
        for(int i=0; i<arrList.size(); i++) {
            sb.append(arrList.get(i)).append("\n");
        }
        System.out.println(sb.toString().trim());
    }
    
    static int bfs(Node start) {
        Queue<Node> que = new LinkedList<>();
        que.add(start);
        visited[start.y][start.x] = true;
        int cout = 1;
        while(que.size() != 0) {
            Node node = que.poll();
            for(int i=0; i<dx.length; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if(checkRange(nx, ny) && map[ny][nx] == 1 && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    que.add(new Node(nx, ny));
                    cout += 1;
                }
            }
        }
        return cout;
    }
    
    static boolean checkRange(int x, int y) {
        if((0 <= x && x < N) && (0 <= y && y < N)) {
            return true;
        }
        return false;
    }
}