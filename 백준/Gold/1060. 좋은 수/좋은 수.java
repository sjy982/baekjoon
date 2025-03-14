import java.io.*;
import java.util.*;

//구간 class
class Interval {
    int A, B, lc, rc;
    Interval(int A, int B) {
        this.A = A;
        this.B = B;
        this.lc = A; //left cursor
        this.rc = B; //right curcor
    }
}

class IntegerInfo implements Comparable < IntegerInfo > {
    int ind; //어떤 구간에 정수인지.
    int num; //
    long v; //값.

    IntegerInfo(int ind, int num, long v) {
        this.ind = ind;
        this.num = num;
        this.v = v;
    }

    @Override
    public int compareTo(IntegerInfo o) {
        if (this.v < o.v) {
            return -1;
        } else if (this.v == o.v) {
            //같다면 더 작은 정수.
            if (this.num < o.num) {
                return -1;
            } else if (this.num > o.num) {
                return 1;
            }
        } else {
            return 1;
        }
        return 0;
    }
}

public class Main {
    static int L, n;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        L = Integer.parseInt(br.readLine());
        int[] S = new int[L];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < L; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(S); //오름차순 정렬
        n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        ArrayList < Interval > itvList = new ArrayList < > ();

        PriorityQueue < IntegerInfo > pq = new PriorityQueue < > ();
        //구간 넣기
        int left = 1;
        for (int i = 0; i < L; i++) {
            pq.add(new IntegerInfo(-1, S[i], 0));
            int right = S[i] - 1;
            if (left < right) {
                //조건을 만족한다면.
                itvList.add(new Interval(left, right));
            } else if (left == right) {
                //좋은 구간에 포함되는 값이 0임
                pq.add(new IntegerInfo(-1, left, 0));
            }
            left = S[i] + 1;
        }

        for (int i = 0; i < itvList.size(); i++) {
            int A = itvList.get(i).A;
            int B = itvList.get(i).B;
            pq.add(new IntegerInfo(i, A, findIncludedSectionValue(A, B, A)));
            itvList.get(i).lc += 1;
        }

        while (!pq.isEmpty()) {
            IntegerInfo intInfo = pq.poll();
            sb.append(intInfo.num).append(" ");
            n -= 1;
            if (n == 0) {
                break;
            }
            if (intInfo.ind != -1) {
                Interval itv = itvList.get(intInfo.ind);
                if (itv.lc <= itv.rc) {
                    long lcV = findIncludedSectionValue(itv.A, itv.B, itv.lc);
                    long rcV = findIncludedSectionValue(itv.A, itv.B, itv.rc);
                    if (lcV <= rcV) {
                        pq.add(new IntegerInfo(intInfo.ind, itv.lc, lcV));
                        itv.lc += 1;
                    } else {
                        pq.add(new IntegerInfo(intInfo.ind, itv.rc, rcV));
                        itv.rc -= 1;
                    }
                }
            }
        }

        if (n == 0) {
            System.out.println(sb.toString().trim());
        } else {
            //n이 아직 남아있다면.
            int start = S[L - 1] + 1;
            int end = start + n - 1;
            for (int i = start; i <= end; i++) {
                sb.append(i).append(" ");
            }
            System.out.println(sb.toString().trim());
        }

    }

    static long findIncludedSectionValue(int A, int B, int num) {
        return ((long) B - num) + (((long) B - (num - 1)) * (num - A));
    }
}