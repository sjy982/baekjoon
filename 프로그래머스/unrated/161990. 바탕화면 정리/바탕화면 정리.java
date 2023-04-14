import java.util.*;

class Solution {
    static int H,W;
    public int[] solution(String[] wallpaper) {
        int[] answer = {-1, -1, -1, -1};
        H = wallpaper.length;
        W = wallpaper[0].length();
        //맨 위 좌표
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                if(wallpaper[i].charAt(j) == '#') {
                    answer[0] = i;
                    break;
                }
            }
            if(answer[0] != -1) break;
        }
        //맨 왼쪽 좌표
        for(int i=0; i<W; i++) {
            for(int j=0; j<H; j++) {
                if(wallpaper[j].charAt(i) == '#') {
                    answer[1] = i;
                    break;
                }
            }
            if(answer[1] != -1) break;
        }
        //맨 아래 좌표
        for(int i=H-1; i>=0; i--) {
            for(int j=W-1; j>=0; j--) {
                if(wallpaper[i].charAt(j) == '#') {
                    answer[2] = i+1;
                    break;
                }
            }
            if(answer[2] != -1) break;
        }
        //맨 오른쪽 좌표
        for(int i=W-1; i>=0; i--) {
            for(int j=H-1; j>=0; j--) {
                if(wallpaper[j].charAt(i) == '#') {
                    answer[3] = i+1;
                    break;
                }
            }
            if(answer[3] != -1) break;
        }
        return answer;
    }
}