import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int x, y, n;
    Node(int x, int y, int n) {
        this.x = x;
        this.y = y;
        this.n = n;
    }
    
    @Override
    public int compareTo(Node o) {
        if(this.n > o.n) {
            return 1;
        } else if(this.n < o.n) {
            return -1;
        }
        return 0;
    }
}

public class Main {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};
    static int N, K, S, X, Y;
    static int[][] room;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        room = new int[N][N];
        ArrayList<Node> virusList = new ArrayList<>();
        for(int i=0; i<N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                room[i][j] = Integer.parseInt(st2.nextToken());
                if(room[i][j] > 0) {
                    virusList.add(new Node(j, i, room[i][j]));
                }
            }
        }
        StringTokenizer st3 = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st3.nextToken());
        X = Integer.parseInt(st3.nextToken());
        Y = Integer.parseInt(st3.nextToken());
        bfs(virusList);
        System.out.println(room[X-1][Y-1]);
    }
    
    static void bfs(ArrayList<Node> virusList) {
        Queue<Node> que = new LinkedList<>();
        insertASCVirusListToQue(virusList, que);
        int time = 0;
        if(time == S) {
            return;
        }
        while(que.size() != 0) {
            int sz = que.size();
            ArrayList<Node> spreadVirusList =new ArrayList<>();
            for(int i=0; i<sz; i++) {
                Node node = que.poll();
                for(int j=0; j<4; j++) {
                    int nx = node.x + dx[j];
                    int ny = node.y + dy[j];
                    if(checkRange(nx, ny) && room[ny][nx] == 0) {
                        spreadVirusList.add(new Node(nx, ny, node.n));
                        room[ny][nx] = node.n;
                    }
                }
            }
            time += 1;
            if(time == S) {
                return;
            }
            insertASCVirusListToQue(spreadVirusList, que);
        }
    }
    
    static void insertASCVirusListToQue(ArrayList<Node> virusList, Queue<Node> que) {
        Collections.sort(virusList);
        for(int i=0; i<virusList.size(); i++) {
            que.add(virusList.get(i));
        }
    }
    
    static boolean checkRange(int x, int y) {
        if((0 <= x && x <= N-1) && (0 <= y && y <= N-1)) {
            return true;
        }
        return false;
    }
}