import java.io.*;
import java.util.*;

public class Main {
    static int Z;
    public static void main(String args[]) throws IOException {
        int X, Y;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        Z = calPercent(X, Y);
        System.out.println(binarySearch(X, Y));
    }
    
    static int binarySearch(int total, int win) {
        int max = 1000000000;
        int min = 1;
        boolean isFind = false;
        while(min <= max) {
            int mid = (max + min) / 2;
            int curTotal = mid + total;
            int curWin = mid + win;
            int curZ = calPercent(curTotal, curWin);
            if(curZ > Z) {
                max = mid - 1;
                isFind = true;
            } else {
                min = mid + 1;
            }
        }
        if(isFind) {
            return min;
        }
        return -1;
    }
    
    static int calPercent(int total, int win) {
        double result = (double) win * 100 / (double) total;
        return (int) result;
    }
}