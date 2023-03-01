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

public class Main {
    static final int dx[] = {-1, 0, 1, 0};
    static final int dy[] = {0, -1, 0 ,1};
    static int T, H, W;
    static char map[][];
    static int dks[][];
    static int dks_sum[][];
    static ArrayList<Node> list;
    static int ans;
    public static void main(String args[])throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int c=0; c<T; c++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H+2][W+2];
            dks = new int[H+2][W+2];
            dks_sum = new int[H+2][W+2];
            list = new ArrayList<>();
            list.add(new Node(0,0,0)); //상근이
            for(int i=0; i<map.length; i++) {
                if(i==0 || i== map.length-1) {
                    for(int j=0; j<map[i].length; j++) {
                        map[i][j] = '.';
                    }
                } else {
                    String input = br.readLine();
                    for(int j=0; j<map[i].length; j++) {
                        if(j==0 || j== map[i].length-1) map[i][j] = '.';
                        else {
                            map[i][j] = input.charAt(j-1);
                            if(map[i][j]=='$') list.add(new Node(j,i,0)); //죄수
                            if(map[i][j]=='#') dks_sum[i][j] = -2;
                        }
                    }
                }
            }
            //최솟값 구하기
            for(int i=0; i<list.size(); i++) {
                dks_init(); //초기화
                BFS(list.get(i));
                for(int j=0; j<dks.length; j++) {
                    for(int k=0; k<dks[i].length; k++) {
                        dks_sum[j][k] += dks[j][k];
                    }
                }
            }
            ans = 10001;
            for(int i=0; i<dks_sum.length; i++) {
                for(int j=0; j<dks_sum[i].length; j++) {
                    ans = Math.min(ans, dks_sum[i][j]);
                }
            }
            System.out.println(ans);
        }
        
    }
    static void BFS(Node start) {
        Queue<Node> que = new LinkedList<>();
        que.add(start);
        dks[start.y][start.x] = 0;
        while(que.size()!=0) {
            Node n = que.poll();
            for(int i=0; i<4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if((nx>=0 && nx<=W+1) && (ny>=0 && ny<=H+1)) {
                    if(map[ny][nx]=='.' || map[ny][nx]=='$') {
                        //빈공간
                        if(n.c<dks[ny][nx]) {
                            que.add(new Node(nx, ny, n.c));
                            dks[ny][nx] = n.c;
                        }
                    } else if(map[ny][nx]=='#') {
                        //문
                        if(n.c+1<dks[ny][nx]) {
                            que.add(new Node(nx, ny, n.c+1));
                            dks[ny][nx] = n.c+1;
                        }
                    }
                }
            }
        }
    }
    
    static void dks_init() {
        for(int i=0; i<dks.length; i++) {
            Arrays.fill(dks[i], 10001);
        }
    }
}