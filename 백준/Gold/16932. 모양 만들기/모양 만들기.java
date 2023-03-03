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
    static final int dx[] = {-1, 0, 1, 0};
    static final int dy[] = {0, -1, 0, 1};
    static int N,M;
    static int arr[][];
    static boolean visited[][];
    static ArrayList<Coordinate> one_list = new ArrayList<>();
    static int ans[][];
    static int max_size = -1;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new boolean[N][M];
        ans = new int[N][M];
        for(int i=0; i<N; i++) {
            StringTokenizer n_st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(n_st.nextToken());
                if(arr[i][j]==1) one_list.add(new Coordinate(j,i));
            }
        }
        //탐색
        for(int i=0; i<one_list.size(); i++) {
            Coordinate c = one_list.get(i);
            ArrayList<Coordinate> zero_list = new ArrayList<>();
            if(!visited[c.y][c.x]) {
                make_shape(BFS(c, zero_list), zero_list);
            }
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                max_size = Math.max(max_size, ans[i][j]);
            }
        }
        max_size += 1;
        System.out.println(max_size);
    }
    static int BFS(Coordinate start, ArrayList<Coordinate> zero_list) {
        Queue<Coordinate> que = new LinkedList<>();
        que.add(start);
        visited[start.y][start.x] = true;
        int size = 1;
        while(que.size()!=0) {
            Coordinate c = que.poll();
            for(int i=0; i<4; i++) {
                int nx = c.x + dx[i];
                int ny = c.y + dy[i];
                if((nx>=0 && nx<=M-1) && (ny>=0 && ny<=N-1)) {
                    if(!visited[ny][nx]) {
                        if(arr[ny][nx]==1) {
                            que.add(new Coordinate(nx, ny));
                            visited[ny][nx] = true;
                            size += 1;
                        } else {
                            //0이면
                            zero_list.add(new Coordinate(nx, ny));
                            visited[ny][nx] = true;
                        }
                    } 
                }
            }
        }
        return size;
    }
    static void make_shape(int size, ArrayList<Coordinate> zero_list) {
        for(int i=0; i<zero_list.size(); i++) {
            Coordinate c = zero_list.get(i);
            ans[c.y][c.x] += size;
            visited[c.y][c.x] = false;
        }
    }
}