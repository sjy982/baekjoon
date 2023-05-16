import java.util.*;
class Node {
    int x, y, d;
    Node(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}
class Solution {
    static boolean[][] point = new boolean[501][501]; //1은 0.1, 10은 1
    static boolean[][] border = new boolean[501][501];
    static boolean[][] visited = new boolean[501][501];
    static final int[] dx = {-5, 0, 5, 0};
    static final int[] dy = {0, -5, 0, 5};
    static int answer = 0;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        for(int i=0; i<rectangle.length; i++) {
            for(int y=rectangle[i][1] * 10; y<=rectangle[i][3] * 10; y++) {
                //y하단 ~ y상단
                for(int x=rectangle[i][0] * 10; x<=rectangle[i][2] * 10; x++) {
                    //좌측 x ~ 우측 x
                    if(!point[y][x]) {
                        //겹치지 않는 부분
                        point[y][x] = true;
                        if(border_check(rectangle[i], x, y)) {
                            //테두리라면
                            border[y][x] =true;
                        }
                    } else {
                        //겹치는 부분
                        if(!border[y][x] || !border_check(rectangle[i], x, y)) {
                            //겹치면서 두 개체 모두가 테두리가 아니라면 지워준다.
                            border[y][x] = false;
                        }
                    }
                }
            }
        }
        BFS(new Node(characterX * 10, characterY * 10, 0), new Node(itemX * 10, itemY * 10, 0));
        return answer;
    }
    static void BFS(Node start, Node end) {
        Queue<Node> que = new LinkedList<>();
        que.add(start);
        visited[start.y][start.x] = true;
        while(que.size() != 0) {
            Node n = que.poll();
            if((n.x == end.x) && (n.y == end.y)) {
                answer = n.d / 10;
                return;
            }
            for(int i=0; i<4; i++) {
                //5씩 움직임 좌표상으로는 0.5씩 움직임
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if((0 <= nx && nx <= 500) && (0 <= ny && ny <= 500)) {
                    if(border[ny][nx] && !visited[ny][nx]) {
                        //테두리이면서, 방문하지 않았다면
                        que.add(new Node(nx, ny, n.d + 5));
                        visited[ny][nx] = true;
                    }
                }
            }
        }
    }
    static boolean border_check(int[] rectangle, int x, int y) {
        //테두리인지 체크
        if(rectangle[0] * 10 == x) return true;
        else if(rectangle[2] * 10 == x) return true;
        else if(rectangle[1] * 10 == y) return true;
        else if(rectangle[3] * 10 == y) return true;
        return false;
    }
}