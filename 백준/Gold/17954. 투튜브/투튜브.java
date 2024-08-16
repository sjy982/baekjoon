import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] apple = new int[2][N];
        long size = ((2 * N) * ((2 * N) + 1)) / 2;
        long answer = 0;

        if (N == 1) {
            apple[0][0] = 1;
            apple[1][0] = 2;
        } else {
            apple[0][0] = 2 * N - 3;
            apple[0][N - 1] = apple[0][0] + 1;

            apple[1][0] = 2 * N - 1;
            apple[1][N - 1] = apple[1][0] + 1;
            for (int i = 0; i <= 1; i++) {
                for (int j = 1; j <= N - 2; j++) {
                    apple[i][j] = apple[0][0] - ((i * (N - 2)) + j);
                }
            }

        }

        for (int i = 0; i <= N - 1; i++) {
            size -= apple[0][i];
            answer += (i + 1) * size;
        }

        for (int i = 0; i <= N - 2; i++) {
            size -= apple[1][i];
            answer += (N + i + 1) * size;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(answer).append("\n");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(apple[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString().trim());
    }
}