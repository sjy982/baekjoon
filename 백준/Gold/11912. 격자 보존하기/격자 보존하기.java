import java.io.*;
import java.util.*;

public class Main {
    static int n, k, d;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int before = 1;
        ArrayList < Integer > one = new ArrayList < > ();
        ArrayList < Integer > two = new ArrayList < > ();
        for (int i = 0; i < k; i++) {
            int h = Integer.parseInt(st2.nextToken());
            if (before == 1 && h != 1) {
                one.add(h - before);
            } else {
                if (h - before != 0) {
                    two.add(h - before);
                }
            }
            before = h + 1;
        }

        if (before <= n) {
            one.add(n + 1 - before);
        }
        long answer = 0;
        if (one.size() == 2) {
            ArrayList<Integer> newTwo = new ArrayList<>();
            newTwo.add(one.get(0) + one.get(1));
            for(int i=0; i<two.size(); i++) {
                newTwo.add(two.get(i));
            }
            Collections.sort(newTwo, Collections.reverseOrder());
            for(int i=1; i<=d/2; i++) {
                if(i - 1 >= newTwo.size()) {
                    break;
                }
                answer += newTwo.get(i-1);
            }
            
            long answer2 = Math.max(one.get(0), one.get(1));
            if(d % 2 == 1) {
                Collections.sort(two, Collections.reverseOrder());
                for(int i=1; i<=d/2; i++) {
                    if(i - 1 >= two.size()) {
                        break;
                    }
                    answer2 += two.get(i - 1);
                }
                answer = Math.max(answer, answer2);
            }
        } else {
            if (d % 2 == 0) {
                if (!one.isEmpty()) {
                    two.add(one.get(0));
                }
            } else {
                if(!one.isEmpty()) {
                    answer = one.get(0);
                }
            }
            Collections.sort(two, Collections.reverseOrder());
            for (int i = 1; i <= d / 2; i++) {
                if (i - 1 >= two.size()) {
                    break;
                }
                answer += two.get(i - 1);
            }
        }
        System.out.println(answer);
    }
}