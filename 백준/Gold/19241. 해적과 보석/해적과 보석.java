import java.io.*;
import java.util.*;

class Box implements Comparable<Box> {
    int A, B, diff;
    Box(int A, int B) {
        this.A = A;
        this.B = B;
        this.diff = Math.abs(this.A - this.B);
    }
    
    @Override
    public int compareTo(Box o) {
        int v1 = this.A - o.B;
        int v2 = o.A - this.B;
        if(v1 > v2) {
            return -1;
        } else if(v1 < v2) {
            return 1;
        }
        return 0;
    }
}

public class Main {
    static int T;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<T; i++) {
            int N = Integer.parseInt(br.readLine());
            Box[] boxs = new Box[N];
            for(int j=0; j<N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                boxs[j] = new Box(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            Arrays.sort(boxs);
            sb.append(answer(boxs)).append("\n");
        }
        System.out.println(sb.toString().trim());
    }
    
    static long answer(Box[] boxs) {
        long scoreA = 0;
        long scoreB = 0;
        boolean turnA = true;
        for(int i=0; i<boxs.length; i++) {
            if(turnA) {
                scoreA += boxs[i].A;
            } else {
                scoreB += boxs[i].B;
            }
            turnA = !turnA;
        }
        return scoreA - scoreB;
    }
}