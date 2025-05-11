import java.util.*;
import java.io.*;

class Position {
    int x, y;
    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};
    static int N, M;
    static int answer = Integer.MAX_VALUE;
    public int solution(int[][] maze) {
        N = maze.length - 1;
        M = maze[0].length - 1;
        Position red = new Position(-1, -1);
        Position blue = new Position(-1, -1);
        for(int i=0; i<=N; i++) {
            for(int j=0; j<=M; j++) {
                if(maze[i][j] == 1) {
                    red = new Position(j, i);
                } else if(maze[i][j] == 2) {
                    blue = new Position(j, i);
                }
            }
        }
        
        boolean[][] rVis = new boolean[N + 1][M + 1];
        boolean[][] bVis = new boolean[N + 1][M + 1];
        rVis[red.y][red.x] = true;
        bVis[blue.y][blue.x] = true;
        
        dfs(red, new Position(-1, -1), rVis, false, blue, bVis, false, maze, 0, true);
        if(answer == Integer.MAX_VALUE) {
            answer = 0;
        }
        return answer;
    }
    
    static void dfs(Position red, Position beforeRed, boolean[][] rVisited, boolean rEnd, Position blue, boolean[][] bVisited, boolean bEnd, int[][] maze, int cntTurn, boolean turn) {
        //turn -> true이면 레드, trun -> false이면 블루
        if(rEnd && bEnd) {
            answer = Math.min(answer, cntTurn);
            return;
        }
        
        Position p = red;
        boolean[][] vis = rVisited;
        if(!turn) {
            p = blue;
            vis = bVisited;
        }
        for(int i=0; i<4; i++) {
            int nx = p.x + dx[i];
            int ny = p.y + dy[i];
            if(checkRange(nx, ny) && maze[ny][nx] != 5 && !vis[ny][nx]) {
                //범위를 벗어나지 않고, 벽도 아니고, 방문하지 않았다면.
                if(turn) {
                    //red turn인 경우
                    int nextCntTurn = cntTurn;
                    boolean nextTurn = false;
                    if(bEnd) {
                        //블루가 end 상황이면서, blue 쪽으로 이동하려 한다면 못감.
                        if((nx == blue.x && ny == blue.y)) {
                            continue;
                        }
                        nextCntTurn += 1;
                        nextTurn = true;
                    }
                    //그렇지 않다면 무조건 갈 수 있음.
                    vis[ny][nx] = true;
                    boolean nextEnd = false;
                    if(maze[ny][nx] == 3) {
                        //다음 가는 곳이 도착지라면
                        nextEnd = true;
                    }
                    dfs(new Position(nx, ny), red, vis, nextEnd, blue, bVisited, bEnd, maze, nextCntTurn, nextTurn);
                    vis[ny][nx] = false;
                } else {
                    //blue turn인 경우
                    if((nx == red.x) && (ny == red.y)) {
                        //다음 목적지가 red가 있는 곳이라면
                        continue;
                    }
                    
                    if((blue.x == red.x) && (blue.y == red.y)) {
                        //현재 blue, red 위치가 같다면 before 위치로는 갈 수 없음. 스위칭이기 때문
                        if((nx == beforeRed.x && ny == beforeRed.y)) {
                            continue;
                        }
                    }
                    boolean nextTurn = true;
                    if(rEnd) {
                        //red가 끝났다면 턴은 계속 블루임
                        nextTurn = false;
                    }
                    
                    //무조건 갈 수 있음.
                    vis[ny][nx] = true;
                    boolean nextEnd = false;
                    if(maze[ny][nx] == 4) {
                        //다으 가는 곳이 도착지라면
                        nextEnd = true;
                    }
                    dfs(red, beforeRed, rVisited, rEnd, new Position(nx, ny), vis, nextEnd, maze, cntTurn + 1, nextTurn);
                    vis[ny][nx] = false;
                }
                
            }
        }
    }
    
    static boolean checkRange(int x, int y) {
        if((0 <= x  && x <= M) && (0 <= y && y <= N)) {
            return true;
        }
        return false;
    }
}