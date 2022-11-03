import java.io.*;
import java.util.*;

class Node {
    int x,y,k,c,an;
    Node(int x, int y, int k, int c, int an) {
        //an => 낮, 밤 구분 낮은 0, 밤은 1
        this.x = x;
        this.y = y;
        this.k = k;
        this.c = c;
        this.an = an;
    }
}
public class Main {
    static final int dx[] = {0,0,-1,1};
    static final int dy[] = {-1,1,0,0};
    static int map[][];
    static int visited_min[][];
    static int N, M, K;
    public static void main(String args[])throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        visited_min = new int[N][M];
        for(int i=0; i<N; i++) {
            String s = br.readLine();
            Arrays.fill(visited_min[i], K+1);
            for(int j=0; j<M; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }
        System.out.println(BFS());
    }
    
    static int BFS() {
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(0,0,0,1,0));
        while(!que.isEmpty()) {
            Node v = que.poll();
            if(v.x == M-1 && v.y == N-1) {
                return v.c;
            }
            for(int i=0; i<4; i++) {
                int nx = v.x + dx[i];
                int ny = v.y + dy[i];
                if((nx>=0 && nx<=M-1) && (ny>=0 && ny<=N-1)) {
                    if(map[ny][nx] == 0) {
                        if(visited_min[ny][nx] > v.k) {
                            visited_min[ny][nx] = v.k;
                            int af_ni = (v.an == 0) ? 1 : 0;
                            que.add(new Node(nx, ny, v.k, v.c+1, af_ni));
                        }
                    } else {
                        //벽을 만났을때
                        if(v.an == 0) {
                            //현재 낮이면.
                            if(visited_min[ny][nx] > v.k+1) {
                                visited_min[ny][nx] = v.k+1;
                                que.add(new Node(nx, ny, v.k+1, v.c+1, 1));
                            }
                        } else {
                            //현재 밤이면. 칸에 머물러있는 경우 밖에 없음.
                            que.add(new Node(v.x, v.y, v.k, v.c+1, 0));
                        }
                    }
                }
            }
        }
        return -1;
    }
}