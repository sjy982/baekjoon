import java.io.*;
import java.util.*;

class Node {
    int x,y,s,e,t;
    Node(int x, int y, int s, int e, int t) {
        this.x = x;
        this.y = y;
        this.s = s;
        this.e = e;
        this.t = t;
    }
}

class EatenFish {
    int x,y,t;
    EatenFish(int x, int y, int t) {
        this.x = x;
        this.y = y;
        this.t = t;
    }
}

public class Main {
    static final int dx[] = {0,-1,1,0};
    static final int dy[] = {-1,0,0,1};
    static int map[][];
    static boolean visited[][];
    static int N,next;
    static Node baby_shark;
    static EatenFish eaten_fish;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        next = 1;
        map = new int[N][N];
        for(int i=0; i<N; i++) {
            StringTokenizer sti = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(sti.nextToken());
                if(map[i][j] == 9) {
                    map[i][j] = 0;
                    baby_shark = new Node(j,i,2,0,0);
                }
            }
        }
        
        while(next!=-1) {
            visited = new boolean[N][N];
            eaten_fish = new EatenFish(20,20,-1);
            next = BFS();
        }
        System.out.println(baby_shark.t);
    }
    
    static int BFS() {
        Queue<Node> que = new LinkedList<>();
        que.add(baby_shark);
        visited[baby_shark.y][baby_shark.x] = true;
        while(!que.isEmpty()) {
            int qs = que.size();
            for(int i=0; i<qs; i++) {
                Node n = que.poll();
                for(int j=0; j<dx.length; j++) {
                    int nx = n.x + dx[j];
                    int ny = n.y + dy[j];
                    if((nx>=0 && nx<=N-1) && (ny>=0 && ny<=N-1)) {
                        if(!visited[ny][nx]) {
                            if(map[ny][nx] == 0 || map[ny][nx] == n.s) {
                                //????????? ????????? ??????
                                visited[ny][nx] = true;
                                que.add(new Node(nx,ny,n.s,n.e,n.t+1));
                            } else if(map[ny][nx]>=1 && map[ny][nx]<n.s) {
                                //????????? ?????? ???????????? ?????? ??????
                                visited[ny][nx] = true;
                                if(eaten_fish.y > ny) {
                                    //?????? ???????????? ??? ?????? ???????????????.
                                    eaten_fish = new EatenFish(nx, ny, n.t+1);
                                } else if(eaten_fish.y == ny) {
                                    if(eaten_fish.x > nx) {
                                        //?????? ????????? + ?????? y????????? x??? ??? ????????? ?????????.
                                        eaten_fish = new EatenFish(nx, ny, n.t+1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if(eaten_fish.y != 20) {
                //20??? ???????????? 20??? ???????????? ?????? ???????????? ???????????? ???
                map[eaten_fish.y][eaten_fish.x] = 0;
                int ne = baby_shark.e + 1;
                int cs = baby_shark.s;
                if(ne == cs) {
                    ne = 0;
                    cs += 1;
                }
                baby_shark = new Node(eaten_fish.x, eaten_fish.y, cs, ne, eaten_fish.t);
                return 1;
            }
        }
        return -1;
    }
}