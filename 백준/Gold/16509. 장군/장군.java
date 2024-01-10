import java.io.*;
import java.util.*;

class Node {
    int x, y, c;
    Node(int x, int y, int c) {
        this.x = x;
        this.y = y;
        this.c = c;
    }
}

public class Main {
    static ArrayList<Node>[] direct = new ArrayList[8];
    static Node king;
    static boolean[][] visited = new boolean[10][9];
    public static void main(String args[]) throws IOException {
        for(int i=0; i<8; i++) {
            direct[i] = new ArrayList<>();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int sy = Integer.parseInt(st1.nextToken());
        int sx = Integer.parseInt(st1.nextToken());
        Node sang = new Node(sx, sy, 0);
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int ky = Integer.parseInt(st2.nextToken());
        int kx = Integer.parseInt(st2.nextToken());
        king = new Node(kx, ky, 0);
        makeDirect();
        System.out.println(bfs(sang));
    }
    
    static int bfs(Node start) {
        Queue<Node> que = new LinkedList<>();
        que.add(start);
        visited[start.y][start.x] = true;
        while(que.size() != 0) {
            Node n = que.poll();
            if(n.x == king.x && n.y == king.y) {
                return n.c;
            }
            for(int i=0; i<8; i++) {
                Node movedNode = move(n, direct[i]);
                if(movedNode.x != -1 && !visited[movedNode.y][movedNode.x]) {
                    visited[movedNode.y][movedNode.x] = true;
                    que.add(movedNode);
                }
            }
        }
        return -1;
    }
    
    static Node move(Node n, ArrayList<Node> route) {
        Node result = new Node(n.x, n.y, n.c + 1);
        for(int i=0; i<3; i++) {
            int nx = result.x + route.get(i).x;
            int ny = result.y + route.get(i).y;
            if(!checkRange(nx, ny)) {
                return new Node(-1, -1, -1);
            }
            if(i != 2 && (king.x == nx && king.y == ny)) {
                return new Node(-1, -1, -1);
            }
            result.x = nx;
            result.y = ny;
        }
        return result;
    }
    
    static boolean checkRange(int x, int y) {
        if((0 <= x && x <= 8) && (0 <= y && y <= 9)) {
            return true;
        }
        return false;
    }
    
    static void makeDirect() {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        int[] dgx = {-1, -1, 1, 1};
        int[] dgy = {1, -1, -1, 1};
        ArrayList<Integer>[] dgIndList = new ArrayList[4];
        for(int i=0; i<4; i++) {
            dgIndList[i] = new ArrayList<>();
        }
        dgIndList[0].add(0);
        dgIndList[0].add(1);
        dgIndList[1].add(1);
        dgIndList[1].add(2);
        dgIndList[2].add(2);
        dgIndList[2].add(3);
        dgIndList[3].add(3);
        dgIndList[3].add(0);
        for(int i=0; i<8; i++) {
            direct[i].add(new Node(dx[i/2], dy[i/2], 0));
            for(int j=0; j<2; j++) {
                direct[i].add(new Node(dgx[dgIndList[i/2].get(i%2)], dgy[dgIndList[i/2].get(i%2)], 0));
            }
        }
    }
}