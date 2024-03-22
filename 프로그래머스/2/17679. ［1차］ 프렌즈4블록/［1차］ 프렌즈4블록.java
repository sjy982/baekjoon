import java.io.*;
import java.util.*;

class Block {
    Character c;
    boolean isExist;
    Block(Character c, boolean isExist) {
        this.c = c;
        this.isExist = isExist;
    }
}

class Direct {
    int dx, dy;
    Direct(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}

class Solution {
    static ArrayList<Direct>[] dir_list = new ArrayList[4];
    static Block[][] fBoard;
    static int N, M;
    public int solution(int m, int n, String[] board) {
        dir_list[0] = new ArrayList<Direct>(Arrays.asList(new Direct(0, -1), new Direct(1, 0),new Direct(1, -1)));
        dir_list[1] = new ArrayList<Direct>(Arrays.asList(new Direct(0, -1), new Direct(-1, -1),new Direct(-1, 0)));
        dir_list[2] = new ArrayList<Direct>(Arrays.asList(new Direct(-1, 0), new Direct(-1, 1),new Direct(0, 1)));
        dir_list[3] = new ArrayList<Direct>(Arrays.asList(new Direct(1, 0), new Direct(1, 1),new Direct(0, 1)));
        fBoard = new Block[m][n];
        M = m;
        N = n;
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length(); j++) {
                fBoard[i][j] = new Block(board[i].charAt(j), true);
            }
        }
        gameStart();
        return findAnswer();
    }
    
    static int findAnswer() {
        int cout = 0;
        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                if(fBoard[i][j] == null) {
                    cout += 1;
                }
            }
        }
        return cout;
    }
    
    static void gameStart() {
        while(true) {
            boolean noGroup = true;
            for(int i=0; i<M; i++) {
                for(int j=0; j<N; j++) {
                    if(fBoard[i][j] != null && !noteGroup(j, i)) {
                        noGroup = false;
                    }
                }
            }
            if(noGroup) {
                return;
            }
            empty(); //지워주고
            compress(); //압축
            // StringBuilder sb = new StringBuilder();
            // for(int i=0; i<M; i++) {
            //     for(int j=0; j<N; j++) {
            //         if(fBoard[i][j] == null) {
            //             sb.append("n ");
            //         } else {
            //             sb.append(fBoard[i][j].c + " ");
            //         }
            //     }
            //     sb.append("\n");
            // }
            // System.out.println(sb.toString() + "\n" + "-------------------");
        }
    }
    
    static void compress() {
        for(int i=0; i<N; i++) {
            //열 별로
            for(int j=M-2; j>=0; j--) {
                //마지막 행 다음부터
                if(fBoard[j][i] != null && fBoard[j + 1][i] == null) {
                    //밑에 행이 비어 있으면 이때는 자리를 찾아가야됨
                    down(i, j);
                }
            }
        }
    }
    
    static void down(int x, int y) {
        Block target = fBoard[y][x]; //내릴 타겟 저장
        fBoard[y][x] = null; //비워줌
        int cursor = y + 1;
        while(cursor != M) {
            if(fBoard[cursor][x] != null) {
                fBoard[cursor - 1][x] = target;
                break;
            }
            if(cursor == M-1 && fBoard[cursor][x] == null) {
                fBoard[cursor][x] = target;
                break;
            }
            cursor += 1;
        }
    }
    
    static void empty() {
        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                if(fBoard[i][j] != null && !fBoard[i][j].isExist) {
                    fBoard[i][j] = null;
                }
            }
        }
    }
    
    static boolean noteGroup(int x, int y) {
        Character c = fBoard[y][x].c;
        boolean isEnd = true;
        for(int i=0; i<4; i++) {
            boolean isPosible = true;
            ArrayList<Direct> list = new ArrayList<>();
            for(int j=0; j<dir_list[i].size(); j++) {
                int nx = x + dir_list[i].get(j).dx;
                int ny = y + dir_list[i].get(j).dy;
                if(!checkRange(nx, ny) || fBoard[ny][nx] == null) {
                    isPosible = false;
                    break;
                }
                
                if(fBoard[ny][nx].c != c) {
                    isPosible = false;
                    break;
                }
                list.add(new Direct(nx, ny));
            }
            if(isPosible) {
                fBoard[y][x].isExist = false;
                for(int j=0; j<list.size(); j++) {
                    fBoard[list.get(j).dy][list.get(j).dx].isExist = false;
                }
                isEnd = false;
            }
        }
        return isEnd;
    }
    
    static boolean checkRange(int x, int y) {
        if((0 <= x && x <= N - 1) && (0<= y && y <= M - 1)) {
            return true;
        }
        return false;
    }
}