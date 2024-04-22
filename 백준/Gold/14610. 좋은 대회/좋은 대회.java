import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static int[][] board;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M + 2]; //M은 총 푼 문제, M + 1은 현재 푼 문제
        for(int i=0; i<N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int cout = 0;
            for(int j=0; j<M + 1; j++) {
                if(j == 0) {
                    board[i][M] = Integer.parseInt(st2.nextToken());
                } else {
                    int v = Integer.parseInt(st2.nextToken());
                    if(v == 1) {
                        cout += 1;
                    }
                    board[i][j - 1] = v;
                }
            }
            board[i][M + 1] = cout;
        }
        if(!isPosible()) {
            System.out.println("NO");
        } else {
            if(checkSec()) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
    
    static boolean isPosible() {
        for(int i=0; i<N; i++) {
            if(board[i][M] == 0 || board[i][M] == M) {
                return false;
            }
        }
        return true;
    }
    
    static boolean checkSec() {
        //두 번째 조건 확인 모든 문제는 한 명 이상의 참가자에게 풀려야 한다.
        for(int i=0; i<M; i++) {
            if(!checkSolved(i)) {
                if(!note(i)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    static boolean checkSolved(int ind) {
        for(int i=0; i<N; i++) {
            if(board[i][ind] == 1) {
                return true;
            }
        }
        return false;
    }
    
    static boolean note(int ind) {
        for(int i=0; i<N; i++) {
            if(board[i][ind] == -1 && board[i][M] > board[i][M + 1]) {
                board[i][M + 1] += 1;
                return true;
            }
        }
        return false;
    }
}