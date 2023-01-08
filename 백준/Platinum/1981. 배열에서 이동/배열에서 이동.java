import java.io.*;
import java.util.*;

class Node {
    int x,y;
    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static final int dx[] = {-1, 0, 1, 0};
    static final int dy[] = {0, -1, 0, 1};
    static int arr[][];
    static int N;
    static int ans = Integer.MAX_VALUE;
    public static void main(String args[]) throws IOException {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     N = Integer.parseInt(br.readLine());
     arr = new int[N][N];
     for(int i=0; i<N; i++) {
         StringTokenizer st = new StringTokenizer(br.readLine());
         for(int j=0; j<N; j++) {
             arr[i][j] = Integer.parseInt(st.nextToken());
         }
     }
     for(int i=arr[0][0]; i<=200; i++) find_min(i);
     
     System.out.println(ans);
    }
    
    static void find_min(int max_mid) {
        int min_min = 0;
        int min_max = max_mid;
        boolean search = false;
        while(min_min <= min_max) {
            int min_mid = (min_min + min_max)/2;
            if(BFS(min_mid, max_mid)) {
                min_min = min_mid + 1;
                search = true;
            } else {
                min_max = min_mid - 1;
            }
        }
        if(search) {
            int df_v = max_mid - (min_min-1);
            if(ans > df_v) ans = df_v;
        }
    }
    
    static boolean BFS(int min, int max) {
        Queue<Node> que = new LinkedList<>();
        boolean visited[][] = new boolean[N][N];
        if(min <= arr[0][0] && arr[0][0] <= max) {
            que.add(new Node(0,0));
            visited[0][0] = true;
        }
        while(que.size() != 0) {
            Node n = que.poll();
            if((n.x == N-1) && (n.y == N-1)) return true;
            for(int i=0; i<4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if((nx>=0 && nx<=N-1) && (ny>=0 && ny<=N-1)) {
                    if(!visited[ny][nx]) {
                        if(min<= arr[ny][nx] && arr[ny][nx]<=max) {
                            que.add(new Node(nx, ny));
                            visited[ny][nx] = true;
                        }
                    }
                }
            }
        }
        return false;
    }
}