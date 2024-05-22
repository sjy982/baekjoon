import java.io.*;
import java.util.*;

public class Main {
    static int n, p, k, d;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        
        int[] students = new int[n];
        boolean isPosible = false;
        if(d != 1) {
            int sum = 0;
            for(int i = d - 1; i >= 0; i--) {
                students[d - 1 - i] = i;
                sum += students[d - 1 - i];
            }
            if(p >= sum) {
                students[0] += p - sum;
                isPosible = true;
            }
        } else {
            //d가 1인 경우
            //p / k만큼 상위 k명이 점수를 갖는다.
            for(int i=0; i<k; i++) {
                students[i] = p/k;
            }
            int min = p/k; //나머지 하위 랭크들은 p/k를 넘어서는 안됨
            int mod = p%k;
            for(int i=k; i<n; i++) {
                if(mod - min > 0) {
                    mod -= min;
                    students[i] = min;
                } else {
                    students[i] = mod;
                    mod = 0;
                    break;
                }
            }
            if(mod == 0) {
                isPosible = true;
            }
        }
        
        if(isPosible) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<n; i++) {
                sb.append(students[i]).append("\n");
            }
            System.out.println(sb.toString().trim());
        } else {
            System.out.println("Wrong information");
        }
    }
}