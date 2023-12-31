import java.util.*;
import java.io.*;

public class Main {
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;
    static int[] nums;
    static int N;
    static int[] operators = new int[4];
    static int[] result;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        result = new int[N - 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(st.nextToken());
            nums[i] = num;
        }
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++) {
            operators[i] = Integer.parseInt(st2.nextToken());
        }
        dfs(0);
        System.out.println(max);
        System.out.println(min);
    }
    
    static void dfs(int depth) {
        if(depth == N - 1) {
            int sum = nums[0];
            for(int i=0; i<N - 1; i++) {
                sum = calculate(sum, nums[i + 1], result[i]);
            }
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }
        for(int i=0; i<4; i++) {
            if(operators[i] > 0) {
                operators[i] -= 1;
                result[depth] = i;
                dfs(depth + 1);
                operators[i] += 1;
            }
        }
    }
    
    static int calculate(int n1, int n2, int operator) {
        int result = 0;
        switch (operator) {
            case 0:
                result = n1 + n2;
            break;
            
            case 1:
                result = n1 - n2;
            break;
            
            case 2:
                result = n1 * n2;
            break;
            
            case 3:
                result = n1 / n2;
            break;
        }
        return result;
    }
}