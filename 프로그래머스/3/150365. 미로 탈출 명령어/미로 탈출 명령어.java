import java.util.*;

class Info {
    Info before;
    char c;
    Info(Info before, char c) {
        this.before = before;
        this.c = c;
    }
}

class Info2 {
    Info info;
    int k, y, x;
    Info2(Info info, int k, int y, int x) {
        this.info = info;
        this.k = k;
        this.y = y;
        this.x = x;
    }
}

class Solution {
    static final int[] dx = {0, -1, 1, 0};
    static final int[] dy = {1, 0, 0, -1};
    static final char[] dc = {'d', 'l', 'r', 'u'};
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        Info[][][] map = new Info[k + 1][n + 1][m + 1]; //[k][행][열]
        Queue<Info2> que = new LinkedList<>();
        que.add(new Info2(null, 0, x, y));
        
        while(!que.isEmpty()) {
            Info2 node = que.poll();
            if (node.k == k && node.y == r&& node.x == c) {
                break;
            }
            
            if (node.k >= k) {
                continue;
            }
            
            for(int i=0; i<4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if((1 <= nx && nx <= m) && (1 <= ny && ny <= n)) {
                    if(map[node.k + 1][ny][nx] != null) {
                        continue;
                    }
                    Info info = new Info(node.info, dc[i]);
                    map[node.k + 1][ny][nx] = info;
                    que.add(new Info2(info, node.k + 1, ny, nx));
                }
            }
        }
        if(map[k][r][c] == null) {
            return "impossible";
        }
        StringBuilder sb = new StringBuilder();
        Info cur = map[k][r][c];
        while(cur != null) {
            sb.append(cur.c);
            cur = cur.before;
        }
        
        return sb.reverse().toString();
    }
}