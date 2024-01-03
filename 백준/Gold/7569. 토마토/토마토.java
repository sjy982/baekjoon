import java.io.*;
import java.util.*;

class Node {
    int x, y, z;
    Node(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

public class Main {
    static final int[] dx = {-1, 0, 1, 0, 0, 0};
    static final int[] dy = {0, -1, 0, 1, 0, 0};
    static final int[] dz = {0, 0, 0, 0, -1, 1};
    static int N, M, H;
    static int[][][] storage;
    static int answer;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        storage = new int[H][N][M];
        ArrayList<Node> list = new ArrayList<>();
        for(int i=0; i<H; i++) {
            for(int j=0; j<N; j++) {
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                for(int k=0; k<M; k++) {
                    storage[i][j][k] = Integer.parseInt(st2.nextToken());
                    if(storage[i][j][k] == 1) {
                        list.add(new Node(k, j, i));
                    }
                }
            }
        }
        answer = bfs(list);
        if(checkStorage()) {
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }
    }
    
    static int bfs(ArrayList<Node> list) {
        Queue<Node> que = new LinkedList<>();
        for(int i=0; i<list.size(); i++) {
            que.add(list.get(i));
        }
        int cout = 0;
        while(que.size() != 0) {
            int queSize = que.size();
            boolean isRipe = false;
            for(int i=0; i<queSize; i++) {
                Node n = que.poll();
                for(int j=0; j<6; j++) {
                    int nx = n.x + dx[j];
                    int ny = n.y + dy[j];
                    int nz = n.z + dz[j];
                    if(checkRange(nx, ny, nz) && storage[nz][ny][nx] == 0) {
                        storage[nz][ny][nx] = 1;
                        que.add(new Node(nx, ny, nz));
                        if(!isRipe) {
                            isRipe = true;
                        }
                    }
                }
            }
            if(isRipe) {
                cout += 1;
            }
        }
        return cout;
    }
    
    static boolean checkRange(int x, int y, int z) {
        if((0 <= x && x <= M - 1) && (0 <= y && y <= N-1) && (0 <= z && z <= H - 1)) {
            return true;
        }
        return false;
    }
    
    static boolean checkStorage() {
        for(int i=0; i<H; i++) {
            for(int j=0; j<N; j++) {
                for(int k=0; k<M; k++) {
                    if(storage[i][j][k] == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}