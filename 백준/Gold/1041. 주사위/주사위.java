import java.io.*;
import java.util.*;

public class Main {
    static Integer[][] adjSide = { // A:0 , B:1, C:2, D:3, E:4, F:5
        {1, 2, 3, 4},
        {0, 2, 3, 5},
        {0, 1, 4, 5},
        {0, 1, 4, 5},
        {0, 2, 3, 5},
        {1, 2, 3, 4},
    };
    static long oMin = 301;
    static long tMin = 301;
    static long thMin = 301;
    static int[] dice = new int[6];
    static long N;
    static ArrayList < Integer > result = new ArrayList < > ();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
            oMin = Math.min(oMin, dice[i]);
        }
        if (N == 1) {
            int answer = 301;
            for (int i = 0; i < 6; i++) {
                int sum = dice[i];
                for (int j = 0; j < adjSide[i].length; j++) {
                    sum += dice[adjSide[i][j]];
                }
                answer = Math.min(answer, sum);
            }
            System.out.println(answer);
        } else {
            for (int i = 0; i < 6; i++) {
                result.add(i);
                dfs(i, 0);
                result.remove(0);
            }
            long A = thMin * 4;
            long B = tMin * ((N - 2) * 4 + (N - 1) * 4);
            long C = oMin * ((N * N - ((N - 2) * 4 + 4)) * 5 + (N - 2) * 4);
            System.out.println(A + B + C);
        }
    }

    static void dfs(int sideInd, int ind) {
        if (result.size() == 3) {
            if (Arrays.asList(adjSide[result.get(1)]).contains(result.get(2))) {
                int sum = 0;
                for (int i = 0; i < result.size(); i++) {
                    sum += dice[result.get(i)];
                }
                thMin = Math.min(sum, thMin);
            }
            return;
        }
        if (result.size() == 2) {
            int sum = 0;
            for (int i = 0; i < result.size(); i++) {
                sum += dice[result.get(i)];
            }
            tMin = Math.min(sum, tMin);
        }
        for (int i = ind; i < adjSide[sideInd].length; i++) {
            result.add(adjSide[sideInd][i]);
            dfs(sideInd, i + 1);
            result.remove(result.size() - 1);
        }

    }
}