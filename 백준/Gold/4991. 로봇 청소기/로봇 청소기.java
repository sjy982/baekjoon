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
    static final int dy[] = {0, -1, 0, 1};
    static int w,h;
    static char room[][];
    static boolean visited[][];
    static int move[][];
    static Node start;
    static ArrayList<Node> list;
    static boolean is_posi;
    static int move_min;
    static ArrayList<Integer> result = new ArrayList<>();
    static boolean d_visited[];
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if(w==0 && h==0) break;
            room = new char[h][w];
            list = new ArrayList<>();
            is_posi = true;
            move_min = Integer.MAX_VALUE;
            for(int i=0; i<h; i++) {
                String input = br.readLine();
                for(int j=0; j<w; j++) {
                    room[i][j] = input.charAt(j);
                    if(room[i][j]=='o') start = new Node(j,i,0);
                    else if(room[i][j]=='*') list.add(new Node(j,i,0));
                }
            }
            list.add(0, start);
            move = new int[list.size()][list.size()];
            d_visited = new boolean[list.size()];
            for(int i=0; i<list.size(); i++) {
                for(int j=i+1; j<list.size(); j++) {
                    visited = new boolean[h][w];
                    if(!BFS(list.get(i), list.get(j), i, j)) {
                        //방문할 수 없는 더러운 칸이 존재
                        is_posi = false;
                        break;
                    }
                }
                if(!is_posi) break;
            }
            if(is_posi) {
                DFS();
                sb.append(move_min + "\n");
            } else {
                sb.append(-1 + "\n");
            }
        }
        System.out.println(sb.toString().trim());
    }
    static boolean BFS(Node s, Node e, int si, int ei) {
        Queue<Node> que = new LinkedList<>();
        que.add(s);
        visited[s.y][s.x] = true;
        while(que.size()!=0) {
            Node n = que.poll();
            if((n.x == e.x) && (n.y == e.y)) {
                move[si][ei] = n.c;
                move[ei][si] = n.c;
                return true;
            }
            for(int i=0; i<4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if((nx>=0 && nx<=w-1) && (ny>=0 && ny<=h-1)) {
                    if(!visited[ny][nx] && room[ny][nx] != 'x') {
                        que.add(new Node(nx, ny, n.c+1));
                        visited[ny][nx] = true;
                    }
                }
            }
        }
        return false;
    }
    static void DFS() {
        if(result.size()==list.size()-1) {
            int mc = move[0][result.get(0)];
            for(int i=0; i<result.size()-1; i++) {
                mc+= move[result.get(i)][result.get(i+1)];
            }
            move_min = Math.min(move_min, mc);
            return;
        }
        for(int i=1; i<=list.size()-1; i++) {
            if(!d_visited[i]) {
                result.add(i);
                d_visited[i] = true;
                DFS();
                result.remove(result.size()-1);
                d_visited[i] = false;
            }
        }
    }
}