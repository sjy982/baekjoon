import java.io.*;
import java.util.*;

public class Main {
    static int[] world;
    static int H, W;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        world = new int[W];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i=0; i<W; i++) {
            world[i] = Integer.parseInt(st2.nextToken());
        }
        int sum = 0;
        int curInd = 0;
        while(curInd != W - 1) {
            int ind = findNextBlock(curInd);
            sum += calRain(curInd, ind);
            curInd = ind;
        }
        System.out.println(sum);
    }
    
    static int findNextBlock(int startInd) {
        int maxInd = startInd + 1;
        for(int i=startInd + 1; i<W; i++) {
            if(world[maxInd] < world[i]) {
                maxInd = i;
            }
            if(world[startInd] <= world[maxInd]) {
                return maxInd;
            }
        }
        return maxInd;
    }
    
    static int calRain(int startInd, int endInd) {
        int result = 0;
        int stdInd = world[startInd] <= world[endInd] ? startInd : endInd;
        for(int i=startInd + 1; i<endInd; i++) {
            result += world[stdInd] - world[i];
        }
        return result;
    }
}