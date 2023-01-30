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
    static final int dx[] = {
        -1,
        0,
        1,
        0
    };
    static final int dy[] = {
        0,
        -1,
        0,
        1
    };
    static int N, M;
    static char board[][];
    static int ans = 1000001;
    static ArrayList < Coordinate > blank = new ArrayList < > ();
    static StringBuilder sb = new StringBuilder();
    static int count = 1;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String str_input = br.readLine();
            if (str_input == null) break;
            else {
                StringTokenizer st = new StringTokenizer(str_input);
                N = Integer.parseInt(st.nextToken());
                M = Integer.parseInt(st.nextToken());
                board = new char[N][M];
                for (int i = 0; i < N; i++) {
                    String input = br.readLine();
                    for (int j = 0; j < M; j++) {
                        board[i][j] = input.charAt(j);
                        if (board[i][j] == '.') blank.add(new Coordinate(j, i));
                    }
                }
                for (int i = 0; i < blank.size(); i++) {
                    int x = blank.get(i).x;
                    int y = blank.get(i).y;
                    board[y][x] = '*';
                    DFS(x, y, 0);
                    board[y][x] = '.';
                }
                if(ans == 1000001) {
                    sb.append("Case " + String.valueOf(count) + ": -1\n" );
                    blank = new ArrayList<>();
                } else {
                    sb.append("Case " + String.valueOf(count) + ": " + String.valueOf(ans) + "\n");
                    blank = new ArrayList<>();
                    ans = 1000001;
                }
                count += 1;
            }
        }
        System.out.println(sb.toString().trim());
    }

    static void DFS(int x, int y, int cout) {
        if (check()) {
            if (ans > cout) ans = cout;
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x;
            int ny = y;
            ArrayList < Coordinate > back = new ArrayList < > ();
            while (true) {
                nx += dx[i];
                ny += dy[i];
                if (((nx >= 0 && nx <= M - 1) && (ny >= 0 && ny <= N - 1)) && board[ny][nx] == '.') {
                    board[ny][nx] = '*';
                    back.add(new Coordinate(nx, ny));
                } else {
                    if (back.size() == 0) break;
                    else {
                        DFS(nx - dx[i], ny - dy[i], cout + 1);
                        for (int j = 0; j < back.size(); j++) {
                            board[back.get(j).y][back.get(j).x] = '.';
                        }
                        break;
                    }
                }
            }
        }
    }

    static boolean check() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') return false;
            }
        }
        return true;
    }
}