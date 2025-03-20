import java.io.*;
import java.util.*;

class Solution {
    static int N, M;
    static final int dx[] = {0, -1, 1, 0};
    static final int dy[] = {1, 0, 0, -1};
    static final char[] dir = {'d', 'l', 'r', 'u'};
    static boolean[][][] visited;
    static StringBuilder sb = new StringBuilder();
    static String answer;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        //입력에서는 x가 행이고, y가 열임. 바꿔준다. 
        N = n;
        M = m;
        visited = new boolean[n][m][k + 1];
        if(!dfs(y - 1, x - 1, 0, r - 1, c - 1, k)) {
            return "impossible";
        }
        return answer;
    }
    
    static boolean dfs(int cx, int cy, int ck, int r, int c, int k) {
        //여기서 x는 열, y는 행, r은 행, c는 열  
        if(ck == k) {
            //총 이동 횟수가 k라면.
            if(cx == c && cy == r) {
                //목적지라면.
                answer = sb.toString();
                return true;
            }
            return false;
        }
        
        for(int i=0; i<4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];
            if(checkRange(nx, ny) && !visited[ny][nx][ck + 1]) {
                visited[ny][nx][ck + 1] = true;
                sb.append(dir[i]);
                if(dfs(nx, ny, ck + 1, r, c, k)) {
                    return true;
                }
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return false;
    }
    
    static boolean checkRange(int x, int y) {
        if((0 <= x && x <= M - 1) && (0 <= y && y <= N - 1)) {
            return true;
        }
        return false;
    }
}