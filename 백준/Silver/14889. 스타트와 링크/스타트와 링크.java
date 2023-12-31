import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static boolean[] team;
    static int[][] ability;
    static int min = Integer.MAX_VALUE;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        team = new boolean[N];
        ability = new int[N][N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                ability[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0);
        System.out.println(min);
    }
    
    static void dfs(int depth, int ind) {
        if(depth == N / 2) {
            ArrayList<Integer> team1 = new ArrayList<>();
            ArrayList<Integer> team2 = new ArrayList<>();
            for(int i=0; i<N; i++) {
                if(team[i]) {
                    team1.add(i);
                } else {
                    team2.add(i);
                }
            }
            min = Math.min(min, Math.abs(calAbility(team1) - calAbility(team2)));
            return;
        }
        for(int i=ind; i<N; i++) {
            team[i] = true;
            dfs(depth + 1, i + 1);
            team[i] = false;
        }
    }
    
    static int calAbility(ArrayList<Integer> team) {
        int result = 0;
        for(int i=0; i<N/2; i++) {
            for(int j=0; j<N/2; j++) {
                result += ability[team.get(i)][team.get(j)];
            }
        }
        return result;
    }
}