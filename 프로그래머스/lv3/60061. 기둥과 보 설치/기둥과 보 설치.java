import java.util.*;
class Node {
    int x, y, a;
    Node(int x, int y, int a) {
        this.x = x;
        this.y = y;
        this.a = a;
    }
}
class Solution {
    static boolean[][][] frame;
    static int N;
    public int[][] solution(int n, int[][] build_frame) {
        N = n;
        frame = new boolean[n+1][n+1][2]; //[y좌표][x좌표][구조물] 0은 기둥, 1은 보
        for(int i=0; i<build_frame.length; i++) {
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            int a = build_frame[i][2];
            if(build_frame[i][3] == 0) frame[y][x][a] = false;
            else frame[y][x][a] = true;
            //rule_check
            if(!check_rule()) frame[y][x][a] = !frame[y][x][a]; //back 해주기
        }
        ArrayList<Node> answer_list = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            for(int j=0; j<=n; j++) {
                for(int k=0; k<2; k++) {
                    if(frame[j][i][k]) answer_list.add(new Node(i, j, k));
                }
            }
        }
        int[][] answer = new int[answer_list.size()][3];
        for(int i=0; i<answer_list.size(); i++) {
            answer[i][0] = answer_list.get(i).x;
            answer[i][1] = answer_list.get(i).y;
            answer[i][2] = answer_list.get(i).a;
        }
        return answer;
    }
    
    static boolean check_rule() {
        for(int i=0; i<frame.length; i++) {
            for(int j=0; j<frame[i].length; j++) {
                if(frame[i][j][0] && !check_pillar(j, i)) return false;
                if(frame[i][j][1] && !check_vo(j, i)) return false;
            }
        }
        return true;
    }
    
    static boolean check_pillar(int x, int y) {
        if((y == 0) || (check_range(x-1, y) && frame[y][x-1][1]) || (frame[y][x][1]) || (check_range(x, y-1) && frame[y-1][x][0])) return true;
        return false;
    }
    
    static boolean check_vo(int x, int y) {
        if((check_range(x, y-1) && frame[y-1][x][0]) || (check_range(x+1, y-1) && frame[y-1][x+1][0])) return true;
        else if((check_range(x-1, y) && frame[y][x-1][1]) && (check_range(x+1, y) && frame[y][x+1][1])) return true;
        return false;
    }
    
    static boolean check_range(int x, int y) {
        if((0 <= x && x <= N) && (0 <= y && y <= N)) return true;
        return false;
    }
}