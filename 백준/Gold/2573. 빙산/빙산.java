import java.io.*;
import java.util.*;

class Node {
    int x, y;
    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Ice {
    Node n;
    int h;
    Ice(Node n, int h) {
        this.n = n;
        this.h = h;
    }
}

public class Main {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};
    static int N, M;
    static int[][] map;
    static int answer = 0;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i=0; i<N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st2.nextToken());
            }
        }
        ArrayList<Node> iceList = findIce();
        while(countIceLump(iceList) < 2) {
              if(iceList.size() == 0) {
                  answer = 0;
                  break;
              }
              answer += 1;
              meltIce(iceList);
              iceList = findIce();
        }
        System.out.println(answer);
    }
    
    static int countIceLump(ArrayList<Node> list) {
        int result = 0;
        boolean[][] visited = new boolean[N][M]; 
        for(int i=0; i<list.size(); i++) {
            if(!visited[list.get(i).y][list.get(i).x]) {
                bfs(list.get(i), visited);
                result += 1;
            }
        }
        return result;
    }
    
    static void bfs(Node start, boolean[][] visited) {
        Queue<Node> que = new LinkedList<>();
        que.add(start);
        visited[start.y][start.x] = true;
        while(que.size() != 0) {
            Node n = que.poll();
            for(int i=0; i<4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if(checkRange(nx, ny) && !visited[ny][nx] && map[ny][nx] > 0) {
                    visited[ny][nx] = true;
                    que.add(new Node(nx, ny));
                }
            }
        }
    }
    
    static void meltIce(ArrayList<Node> list) {
        ArrayList<Ice> changeList = new ArrayList<>();
        for(int i=0; i<list.size(); i++) {
            int cout = 0;
            for(int j=0; j<4; j++) {
                int nx = list.get(i).x + dx[j];
                int ny = list.get(i).y + dy[j];
                if(checkRange(nx, ny) && map[ny][nx] == 0) {
                    cout += 1;
                }
            }
            int nh = (map[list.get(i).y][list.get(i).x] - cout) < 0 ? 0 : map[list.get(i).y][list.get(i).x] - cout;
            changeList.add(new Ice(new Node(list.get(i).x, list.get(i).y), nh));
        }
        for(int i=0; i<changeList.size(); i++) {
            map[changeList.get(i).n.y][changeList.get(i).n.x] =changeList.get(i).h;
        }
    }
    
    static ArrayList<Node> findIce() {
        ArrayList<Node> result = new ArrayList<>();
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] != 0) {
                    result.add(new Node(j, i));
                }
            }
        }
        return result;
    }
    
    static boolean checkRange(int x, int y) {
        if((0 <= x && x <= M - 1) && (0 <= y && y <= N - 1)) {
            return true;
        }
        return false;
    }
}