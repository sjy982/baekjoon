import java.io.*;
import java.util.*;

class Coordinate {
    int x, y;
    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class CoordinateInfo {
    int x, y;
    char d;
    CoordinateInfo(int x, int y, char d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}

public class Main {
    static int map[][];
    static int R, C;
    static char d;
    static StringBuilder ans = new StringBuilder();
    static Coordinate min_coordinate = new Coordinate(-1, -1);
    static CoordinateInfo cur;
    static char pattern_d;
    static char next_d;
    static int last_x;
    static int wx;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            StringTokenizer sti = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(sti.nextToken());
                if (((i % 2 == 1) && (j % 2 == 0)) || ((i % 2 == 0) && (j % 2 == 1))) {
                    if (min_coordinate.x == -1) {
                        min_coordinate = new Coordinate(j, i);
                    } else {
                        if (map[min_coordinate.y][min_coordinate.x] > map[i][j]) {
                            min_coordinate = new Coordinate(j, i);
                        }
                    }
                }
            }
        }
        if (R % 2 == 1) {
            cur = new CoordinateInfo(0, 0, 'R');
            while (cur.x != C - 1 || cur.y != R - 1) {
                if (cur.d == 'R') {
                    if (cur.x == C - 1) {
                        ans.append("D");
                        cur = new CoordinateInfo(cur.x, cur.y + 1, 'L');
                    } else {
                        ans.append("R");
                        cur = new CoordinateInfo(cur.x + 1, cur.y, 'R');
                    }
                } else if (cur.d == 'L') {
                    if (cur.x == 0) {
                        ans.append("D");
                        cur = new CoordinateInfo(cur.x, cur.y + 1, 'R');
                    } else {
                        ans.append("L");
                        cur = new CoordinateInfo(cur.x - 1, cur.y, 'L');
                    }
                }
            }
        } else if (C % 2 == 1) {
            cur = new CoordinateInfo(0, 0, 'D');
            while (cur.x != C - 1 || cur.y != R - 1) {
                if (cur.d == 'D') {
                    if (cur.y == R - 1) {
                        ans.append("R");
                        cur = new CoordinateInfo(cur.x + 1, cur.y, 'U');
                    } else {
                        ans.append("D");
                        cur = new CoordinateInfo(cur.x, cur.y + 1, 'D');
                    }
                } else if (cur.d == 'U') {
                    if (cur.y == 0) {
                        ans.append("R");
                        cur = new CoordinateInfo(cur.x + 1, cur.y, 'D');
                    } else {
                        ans.append("U");
                        cur = new CoordinateInfo(cur.x, cur.y - 1, 'U');
                    }
                }
            }
        } else {
            cur = new CoordinateInfo(0, 0, 'R');
            if (min_coordinate.y == R - 1) {
                //마지막 줄에 칸이 제외 됐다면
                while (cur.x != C - 1 || cur.y != R - 1) {
                    if (cur.y + 1 == min_coordinate.y) {
                        if (cur.d == 'R') {
                            if (cur.x == min_coordinate.x) {
                                ans.append("R");
                                cur = new CoordinateInfo(cur.x + 1, cur.y, 'R');
                            } else {
                                ans.append("D");
                                cur = new CoordinateInfo(cur.x, cur.y + 1, 'D');
                            }
                        } else if (cur.d == 'U') {
                            ans.append("R");
                            cur = new CoordinateInfo(cur.x + 1, cur.y, 'R');
                        }
                    } else if (cur.y == min_coordinate.y) {
                        if (cur.d == 'R') {
                            ans.append("U");
                            cur = new CoordinateInfo(cur.x, cur.y - 1, 'U');
                        } else if (cur.d == 'D') {
                            ans.append("R");
                            cur = new CoordinateInfo(cur.x + 1, cur.y, 'R');
                        }
                    } else {
                        if (cur.d == 'R') {
                            if (cur.x == C - 1) {
                                ans.append("D");
                                cur = new CoordinateInfo(cur.x, cur.y + 1, 'L');
                            } else {
                                ans.append("R");
                                cur = new CoordinateInfo(cur.x + 1, cur.y, 'R');
                            }
                        } else if (cur.d == 'L') {
                            if (cur.x == 0) {
                                ans.append("D");
                                cur = new CoordinateInfo(cur.x, cur.y + 1, 'R');
                            } else {
                                ans.append("L");
                                cur = new CoordinateInfo(cur.x - 1, cur.y, 'L');
                            }
                        }
                    }
                }

            } else {
                if(min_coordinate.y % 2 == 0) {
                    pattern_d = 'R';
                    next_d = 'L';
                    wx = 1;
                    last_x = C-1;
                } else {
                    pattern_d = 'L';
                    next_d = 'R';
                    wx = -1;
                    last_x = 0;
                }
                while (cur.x != C - 1 || cur.y != R - 1) {
                    if(cur.y == min_coordinate.y) {
                        if(cur.d == pattern_d) {
                            ans.append("D");
                            cur = new CoordinateInfo(cur.x,cur.y+1,'D');
                        } else if(cur.d == 'U') {
                            ans.append(pattern_d);
                            cur = new CoordinateInfo(cur.x + wx, cur.y, pattern_d);
                        }
                        
                    } else if(cur.y-1 == min_coordinate.y) {
                        if(cur.d == pattern_d) {
                            if(cur.x == min_coordinate.x) {
                                if(cur.x == last_x) {
                                    ans.append("D");
                                    cur = new CoordinateInfo(cur.x, cur.y+1, next_d);
                                } else {
                                    ans.append(pattern_d);
                                    cur = new CoordinateInfo(cur.x + wx, cur.y, pattern_d);
                                }
                            } else {
                                ans.append("U");
                                cur = new CoordinateInfo(cur.x, cur.y-1, 'U');
                            }
                        } else {
                            if(cur.x == last_x) {
                                ans.append("D");
                                cur = new CoordinateInfo(cur.x, cur.y+1, next_d);
                            } else {
                                ans.append(pattern_d);
                                cur = new CoordinateInfo(cur.x + wx, cur.y, pattern_d);
                            }
                        }
                    } else {
                        if(cur.d == 'R') {
                            if(cur.x == C - 1) {
                                ans.append("D");
                                cur = new CoordinateInfo(cur.x, cur.y + 1, 'L');
                            } else {
                                ans.append("R");
                                cur = new CoordinateInfo(cur.x+1, cur.y, 'R');
                            }
                        } else if(cur.d == 'L') {
                            if(cur.x == 0) {
                                ans.append("D");
                                cur = new CoordinateInfo(cur.x, cur.y+1,'R');
                            } else {
                                ans.append("L");
                                cur = new CoordinateInfo(cur.x-1, cur.y, 'L');
                            }
                        }
                    }
                }
            }

        }
        System.out.println(ans);
    }
}