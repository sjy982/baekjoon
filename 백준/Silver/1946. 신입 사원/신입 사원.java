import java.io.*;
import java.util.*;

class Score implements Comparable<Score> {
    int a, b;
    Score(int a, int b) {
        this.a = a;
        this.b = b;
    }
    
    @Override
    public int compareTo(Score other) {
        if (this.a > other.a) {
            return 1;
        } else if(this.a < other.a) {
            return -1;
        }
        return 0;
    }
}

public class Main {
    static int T, N, highRank;
    static ArrayList<Score> list;
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++) {
            N = Integer.parseInt(br.readLine());
            list = new ArrayList<>();
            for(int j=0; j<N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list.add(new Score(a, b));
            }
            Collections.sort(list);
            highRank = list.get(0).b;
            for(int j=1; j<list.size(); j++) {
                if(list.get(j).b < highRank) {
                    highRank = list.get(j).b;
                } else {
                    N -= 1;
                }
            }
            sb.append(N).append("\n");
        }
        System.out.println(sb.toString().trim());
    }
}