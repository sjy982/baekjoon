import java.io.*;
import java.util.*;

class Coordinate {
    int x, y;
    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N,M;
    static char board[][];
    static Stack<Integer> result = new Stack<>();
    static int max_size;
    static int ans = -1;
    static final int dx[] = {-1, 0, 1, 0};
    static final int dy[] = {0, -1, 0 ,1};
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        max_size = (Math.min(N,M) * 2 - 1)/4;
        for(int i=0; i<N; i++) {
            String n_st = new String(br.readLine());
            for(int j=0; j<M; j++) {
                board[i][j] = n_st.charAt(j);
            }
        }
        DFS(0);
        System.out.println(ans);
    }
    static void DFS(int ind) {
        if(result.size()==2) {
            char copy_board[][] = new char[N][M];
            for(int i=0; i<copy_board.length; i++) {
                copy_board[i] = board[i].clone();
            }
            if(check(copy_board,result,0)) {
                int v = (result.get(0) * 4 + 1) * (result.get(1) * 4 + 1);
                ans = Math.max(ans, v);
            }
            return;
        }
        for(int i= ind; i<=max_size; i++) {
            result.push(i);
            DFS(i);
            result.pop();
        }
    }
    
    static boolean check(char cb[][] ,Stack<Integer> s, int si) {
        if(si == 2) return true;
        int size = s.get(si);
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(cb[i][j] == '#') {
                    boolean posi = true;
                    ArrayList<Coordinate> back = new ArrayList<>();
                    for(int k=0; k<4; k++) {
                        Coordinate nc = new Coordinate(j, i);
                        for(int a=0; a<size; a++) {
                            nc.x += dx[k];
                            nc.y += dy[k];
                            if((nc.x>=0 && nc.x<=M-1) && (nc.y>=0 && nc.y<=N-1)) {
                                if(cb[nc.y][nc.x] != '#') {
                                    posi = false;
                                    break;
                                } else {
                                    cb[nc.y][nc.x] = '*';
                                    back.add(new Coordinate(nc.x, nc.y));
                                }
                            } else {
                                posi = false;
                                break;
                            }
                            if(!posi) break;
                        }
                        if(!posi) break;
                    }
                    if(posi) {
                        if(check(cb, s, si+1)) return true;
                    }
                    for(int q=0; q<back.size(); q++) {
                        Coordinate bn = back.get(q);
                        cb[bn.y][bn.x] = '#';
                    }
                }
            }
        }
        return false;
    }
}