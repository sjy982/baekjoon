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
    static int N;
    static ArrayList <ArrayList<Long>> sum_triangle = new ArrayList<>();
    static long ans = -320000000;
    static int count = 1;
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if(N == 0) break;
            sum_triangle = new ArrayList<>();
            ans = -320000000;
            for (int i = 0; i < N; i++) sum_triangle.add(new ArrayList<>());
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 1 + i * 2; j++) {
                    if(j==0) sum_triangle.get(i).add(Long.parseLong(st.nextToken()));
                    else sum_triangle.get(i).add(sum_triangle.get(i).get(j-1) + Long.parseLong(st.nextToken()));
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < sum_triangle.get(i).size(); j++) {
                    if (j % 2 == 0) {
                        //짝수면 부분 삼가형 찾기
                        find_triangle(new Coordinate(j, i));
                    } else {
                        //홀수면 부분 역삼각형 찾기
                        find_inverted_triangle(new Coordinate(j, i));
                    }
                }
            }
            sb.append(String.valueOf(count) + ". " + String.valueOf(ans) + "\n");
            count += 1;
        }
        System.out.println(sb.toString().trim());
    }

    static void find_triangle(Coordinate c) {
        long sum = 0;
        for (int i = c.y; i < N; i++) {
            if(c.x == 0) sum += sum_triangle.get(i).get(c.x + (i - c.y) * 2);
            else sum += sum_triangle.get(i).get(c.x + (i - c.y) * 2) - sum_triangle.get(i).get(c.x-1);
            ans = Math.max(ans, sum);
        }
    }

    static void find_inverted_triangle(Coordinate c) {
        long sum = 0;
        int rc = 0;
        while (true) {
            if(c.x-(rc * 2) == 0) sum += sum_triangle.get(c.y-rc).get(c.x);
            else sum += sum_triangle.get(c.y-rc).get(c.x) - sum_triangle.get(c.y-rc).get(c.x-(rc * 2)-1);
            rc += 1;
            ans = Math.max(ans, sum);
            if ((c.x + 1 - (1 + rc * 2)) < 0 || c.x == sum_triangle.get(c.y - rc).size()) break;
        }
    }
}