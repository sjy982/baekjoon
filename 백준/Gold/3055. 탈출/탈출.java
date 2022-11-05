import java.io.*;
import java.util.*;

class Node {
    int x,y,c;
    Node(int x, int y, int c) {
        this.x = x;
        this.y = y;
        this.c = c;
    }
}

class Coordinate {
    int x,y;
    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static final int dx[] = {0,0,-1,1};
    static final int dy[] = {-1,1,0,0};
    static char map[][];
    static boolean h_visited[][];
    static boolean w_visited[][];
    static ArrayList<Coordinate> water_location = new ArrayList<Coordinate>();
    static int R,C;
    static Coordinate hgh, cave;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        h_visited= new boolean[R][C];
        w_visited= new boolean[R][C];
        for(int i=0; i<R; i++) {
            String s = br.readLine();
            for(int j=0; j<C; j++) {
                map[i][j] = s.charAt(j);
                if(map[i][j] == '*') {
                    water_location.add(new Coordinate(j,i));
                    w_visited[i][j] = true;
                } else if(map[i][j] == 'S') {
                    hgh = new Coordinate(j,i);
                } else if(map[i][j] == 'D') {
                    cave = new Coordinate(j,i);
                }
            }
        }
        int answel = BFS();
        if(answel == -1) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(answel);
        }
    }
    
    static int BFS() {
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(hgh.x, hgh.y, 0));
        while(!que.isEmpty()) {
            int qs = que.size();
            h_visited = new boolean[R][C]; //초기화
            for(int i=0; i<qs; i++) {
                Node v = que.poll();
                if(v.x == cave.x && v.y == cave.y) {
                    return v.c;
                }
                if(map[v.y][v.x] == '*') {
                    //현재 고슴도치 위치가 물로 차있으면 
                    continue;
                }
                for(int j=0; j<dx.length; j++) {
                    int nx = v.x + dx[j];
                    int ny = v.y + dy[j];
                    if((nx>=0 && nx<=C-1) && (ny>=0 && ny<=R-1)) {
                        if(map[ny][nx] != 'X' && map[ny][nx] != '*' && !h_visited[ny][nx]) {
                            h_visited[ny][nx] = true;
                            que.add(new Node(nx, ny, v.c+1));
                        }
                    }
                }
            }
            move_water();
        }
        return -1;
    }
    
    static void move_water() {
        ArrayList<Coordinate> nWater_location = new ArrayList<Coordinate>();
        for(int i=0; i<water_location.size(); i++) {
            for(int j=0; j<dx.length; j++) {
                int nx = water_location.get(i).x + dx[j];
                int ny = water_location.get(i).y + dy[j];
                if((nx>=0 && nx<=C-1) && (ny>=0 && ny<=R-1)) {
                    if(map[ny][nx] != 'X' && map[ny][nx] != 'D' && !w_visited[ny][nx]) {
                        w_visited[ny][nx] = true;
                        map[ny][nx] = '*';
                        nWater_location.add(new Coordinate(nx, ny));
                    }
                }
            }
        }
        water_location = nWater_location;
    }
}