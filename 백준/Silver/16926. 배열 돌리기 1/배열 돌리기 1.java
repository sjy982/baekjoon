import java.io.*;
import java.util.*;

class Node {
    int x, y;
    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};
    static int[][] arr;
    static int N, M, R;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for(int i=0; i<N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                arr[i][j] = Integer.parseInt(st2.nextToken());
            }
        }
        Node topLeft = new Node(0, 0);
        for(int i=0; i<R; i++) {
            roll();
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                sb.append(arr[i][j]);
                if(j != M -1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString().trim());
    }
    
    static void roll() {
        int w = M;
        int h = N;
        Node topLeft = new Node(0, 0);
        while(w > 0 && h > 0) {
            rollPart(topLeft, new Node(topLeft.x + w - 1, topLeft.y + h -1));
            topLeft = new Node(topLeft.x + 1, topLeft.y + 1);
            w -= 2;
            h -= 2;
        }
    }
    
    static void rollPart(Node topLeft, Node downRight) {
        int curInd = 0;
        Node cursor = new Node(topLeft.x + dx[curInd], topLeft.y + dy[curInd]);
        int beforeValue = arr[cursor.y][cursor.x];
        arr[cursor.y][cursor.x] = arr[topLeft.y][topLeft.x];
        while(cursor.x != topLeft.x ||  cursor.y != topLeft.y) {
            int nextInd = moveNextInd(cursor, topLeft, downRight);
            if(nextInd != -1) {
                curInd = nextInd;
            }
            cursor = new Node(cursor.x + dx[curInd], cursor.y + dy[curInd]);
            int curValue = arr[cursor.y][cursor.x];
            arr[cursor.y][cursor.x] = beforeValue;
            beforeValue = curValue;
        }
    }
    
    static int moveNextInd(Node cur, Node topLeft, Node downRight) {
        int result = -1;
        if(cur.x == topLeft.x && cur.y == topLeft.y) {
            result = 0;
        } else if(cur.y == downRight.y && cur.x == topLeft.x) {
            result = 1;
        } else if(cur.x == downRight.x && cur.y == downRight.y) {
            result = 2;
        } else if(cur.y == topLeft.y && cur.x == downRight.x) {
            result = 3;
        }
        return result;
    }
}