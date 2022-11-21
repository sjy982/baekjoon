import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Integer> sn_list = new ArrayList<>();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if(sn_list.size()==0) {
                sn_list.add(n);
            } else {
                if(sn_list.get(sn_list.size()-1) < n) sn_list.add(n);
                else {
                    int j = binary_search(n);
                    sn_list.set(j, n);
                }
            }
        }
        System.out.println(sn_list.size());
    }
    static int binary_search(int target) {
        int left = 0;
        int right = sn_list.size() -1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(sn_list.get(mid) == target) return mid;
            else if(sn_list.get(mid) > target) right = mid - 1;
            else left = mid + 1;
        }
        return left;
    }
}