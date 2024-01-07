import java.io.*;
import java.util.*;

class Node {
    int sumRain, ind;
    Node(int sumRain, int ind) {
        this.sumRain = sumRain;
        this.ind = ind;
    }
}

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
            Node n = calRain(curInd);
            sum += n.sumRain;
            curInd = n.ind;
        }
        System.out.println(sum);
    }
    
    static Node calRain(int startInd) {
        int sumRain = 0;
        int secondMaxInd = startInd + 1;
        for(int i=startInd + 1; i<W; i++) {
            if(world[startInd] <= world[i]) {
                return new Node(sumRain, i);
            } else {
                sumRain += world[startInd] - world[i];
            }
            if(world[secondMaxInd] < world[i]) {
                secondMaxInd = i;
            }
        }
        int sumRain2 = 0;
        for(int i=startInd + 1; i<secondMaxInd; i++) {
            sumRain2 += world[secondMaxInd] - world[i];
        }
        return new Node(sumRain2, secondMaxInd);
    }
}