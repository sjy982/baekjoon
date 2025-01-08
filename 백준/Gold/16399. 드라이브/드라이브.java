import java.io.*;
import java.util.*;

class GasStation {
    int x, p;
    GasStation(int x, int p) {
        this.x = x;
        this.p = p;
    }
}

public class Main {
    static int C, E, D, N;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(br.readLine());

        GasStation[] gs = new GasStation[N + 2]; //N+1은 목적지
        gs[0] = new GasStation(0, 0);
        gs[N + 1] = new GasStation(D, 0);
        if (N != 0) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            StringTokenizer st3 = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                int x = gs[i - 1].x + Integer.parseInt(st2.nextToken());
                int p = Integer.parseInt(st3.nextToken());
                gs[i] = new GasStation(x, p);
            }
        }

        long[][] dp = new long[N + 2][C + 1]; //마지막은 목적지
        initDp(dp);

        int fgs = C - (gs[1].x * E);
        if (fgs < 0) {
            System.out.println(-1);
            return;
        }

        dp[1][fgs] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= C; j++) {
                if (dp[i][j] != Long.MAX_VALUE) {
                    //존재한다면
                    //0 ~ (C - j)까지 주유를 넣는 경우의 수를 넣어준다.
                    int nd = gs[i + 1].x - gs[i].x; //다음 목적지까지 거리
                    for (int k = 0; k <= (C - j); k++) {
                        int curFuel = j + k; //현재 연료.
                        curFuel -= (nd * E);
                        if (curFuel >= 0) {
                            //다음 목적지에 도착했을 때 연료가 0 이상이라면 가능함.
                            dp[i + 1][curFuel] = Math.min(dp[i + 1][curFuel], dp[i][j] + (gs[i].p * k));
                        }
                    }
                }
            }
        }

        long answer = Long.MAX_VALUE;
        for (int i = 0; i <= C; i++) {
            answer = Math.min(answer, dp[N + 1][i]);
        }

        if (answer == Long.MAX_VALUE) {
            answer = -1;
        }
        System.out.println(answer);
    }

    static void initDp(long[][] dp) {
        for (int i = 1; i <= N + 1; i++) {
            for (int j = 0; j <= C; j++) {
                dp[i][j] = Long.MAX_VALUE;
            }
        }
    }
}