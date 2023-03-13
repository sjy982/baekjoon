import java.io.*;
import java.util.*;

class Point {
    int x,y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static final int dx[] = {-1, 0, 1 ,0};
    static final int dy[] = {0, -1, 0, 1};
    static final int dir[] = {1, 2, 4, 8};
    static int N,M;
    static int castle[][];
    static int room_area[][];
    static boolean visited[][];
    static int room_number = 0;
    static int max_size = -1;
    static int max_size2 = -1;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        castle = new int[M][N];
        room_area = new int[M][N];
        visited = new boolean[M][N];
        for(int i=0; i<M; i++) {
            StringTokenizer n_st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                castle[i][j] = Integer.parseInt(n_st.nextToken());
            }
        }
        //탐색
        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                if(!visited[i][j]) {
                    room_number += 1;
                    BFS(new Point(j, i));
                }
            }
        }
        //벽 제거했을 때 가장 넓은 방 크기
        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                for(int k=0; k<4; k++) {
                    int nx = j + dx[k];
                    int ny = i + dy[k];
                    if((nx>=0 && nx<=N-1) && (ny>=0 && ny<=M-1)) {
                        if(castle[i][j] != castle[ny][nx]) {
                            //방 번호가 다르면
                            max_size2 = Math.max(max_size2, room_area[i][j] + room_area[ny][nx]);
                        }
                    }
                }
            }
        }
        System.out.printf("%d\n%d\n%d", room_number, max_size, max_size2);
    }
    static void BFS(Point start) {
        Queue<Point> que = new LinkedList<>();
        ArrayList<Point> vis_list = new ArrayList<>();
        que.add(start);
        vis_list.add(start);
        visited[start.y][start.x] = true;
        int cout = 1;
        while(que.size()!=0) {
            Point n = que.poll();
            for(int i=0; i<4; i++) {
                if((castle[n.y][n.x] & dir[i])==0) {
                    int nx = n.x + dx[i];
                    int ny = n.y + dy[i];
                    if(!visited[ny][nx]) {
                        Point next_n = new Point(nx, ny);
                        que.add(next_n);
                        vis_list.add(next_n);
                        visited[ny][nx] = true;
                        cout += 1;
                    }
                }
            }
        }
        max_size = Math.max(max_size, cout);
        for(int i=0; i<vis_list.size(); i++) {
            castle[vis_list.get(i).y][vis_list.get(i).x] = room_number;
            room_area[vis_list.get(i).y][vis_list.get(i).x] = cout;
        }
    }
}