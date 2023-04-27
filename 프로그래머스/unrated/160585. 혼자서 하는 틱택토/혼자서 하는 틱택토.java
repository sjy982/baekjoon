import java.util.*;
class Point {
    int x,y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
class Solution {
    static ArrayList<Point> o_list = new ArrayList<>();
    static ArrayList<Point> x_list = new ArrayList<>();
    static int o_cout = 0, x_cout = 0, answer = 0;
    static boolean[] o_visited, x_visited;
    public int solution(String[] board) {
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length(); j++) {
                if(board[i].charAt(j) == 'O') o_list.add(new Point(j, i));
                else if(board[i].charAt(j) == 'X') x_list.add(new Point(j, i));
            }
        }
        o_visited = new boolean[o_list.size()];
        x_visited = new boolean[x_list.size()];
        char[][] char_board = {{'.','.','.'}, {'.','.','.'}, {'.','.','.'}};
        DFS(0, char_board, false);
        return answer;
    }
    static void DFS(int order, char[][] board, boolean end) {
        if((o_cout == o_list.size()) && (x_cout == x_list.size())) answer = 1;
        if(end) return;
        if(order == 0) {
            //O 차례
            for(int i=0; i<o_list.size(); i++) {
                if(!o_visited[i]) {
                    board[o_list.get(i).y][o_list.get(i).x] = 'O';
                    o_cout += 1;
                    o_visited[i] = true;
                    DFS(1, board, check(board, 'O'));
                    board[o_list.get(i).y][o_list.get(i).x] = '.';
                    o_cout -= 1;
                    o_visited[i] = false;
                }
            }
        } else if(order == 1) {
            //X 차례
            for(int i=0; i<x_list.size(); i++) {
                if(!x_visited[i]) {
                    board[x_list.get(i).y][x_list.get(i).x] = 'X';
                    x_cout += 1;
                    x_visited[i] = true;
                    DFS(0, board, check(board, 'X'));
                    board[x_list.get(i).y][x_list.get(i).x] = '.';
                    x_cout -= 1;
                    x_visited[i] = false;
                }
            }
        }
    }
    static boolean check(char[][] board, char n) {
        int[][] check_list = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0, 4, 8},{2, 4, 6}};
        for(int i=0; i<check_list.length; i++) {
            boolean end = true;
            for(int j=0; j<check_list[i].length; j++) {
                if(board[check_list[i][j]/3][check_list[i][j]%3] != n) {
                    end = false;
                    break;
                }
            }
            if(end) return true;
        }
        return false;
    }
}