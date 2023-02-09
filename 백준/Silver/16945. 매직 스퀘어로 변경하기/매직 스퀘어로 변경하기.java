import java.io.*;
import java.util.*;

public class Main {
    static int board[][] = new int[3][3];
    static boolean visited[] = new boolean[10];
    static int ans = Integer.MAX_VALUE;
    static ArrayList<Integer> result = new ArrayList<>();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        DFS();
        System.out.println(ans);
    }
    static void DFS() {
        if(result.size()==9) {
            if(find_mk()) {
                int cost = 0;
                for(int i=0; i<9; i++) {
                    cost += Math.abs(board[i/3][i%3] - result.get(i));
                }
                ans = Math.min(ans, cost);
            }
            return;
        }
        for(int i=1; i<=9; i++) {
            if(!visited[i]) {
                result.add(i);
                visited[i] = true;
                DFS();
                result.remove(result.size()-1);
                visited[i] = false;
            }
        }
    }
    static boolean find_mk() {
        ArrayList<Integer> sum = new ArrayList<>();
        //열
        for(int i=0; i<3; i++) {
            sum.add(result.get(i) + result.get(i+3) + result.get(i+6));
        }
        //행
        for(int i=0; i<3; i++) {
            sum.add(result.get(i*3) + result.get(i*3+1) + result.get(i*3+2));
        }
        //대각선
        sum.add(result.get(0) + result.get(4) + result.get(8));
        sum.add(result.get(2) + result.get(4) + result.get(6));
        for(int i=0; i<7; i++) {
            if(sum.get(i) != sum.get(i+1)) return false;
        }
        return true;
    }
}