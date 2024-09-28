import java.io.*;
import java.util.*;

class Block {
    int A, cnt;
    Block(int A, int cnt) {
        this.A = A;
        this.cnt = cnt;
    }
}

public class Main {
    static int N;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(0);
            return;
        }

        int[] rpNum = new int[3]; //반복되는 길이 3 수열
        int[][][] pyramid = new int[3][N][N]; //0은 원본, 1은 type1, 2는 type2
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                pyramid[0][i][j] = Integer.parseInt(st.nextToken());
            }

            if (i <= 1) {
                if (i == 1) {
                    //1층과 2층이 세 가지 색으로 이루어져 있는지 검사해야 됨
                    rpNum[0] = pyramid[0][1][1];
                    rpNum[1] = pyramid[0][0][0];
                    rpNum[2] = pyramid[0][1][0];

                    if (!check(rpNum)) {
                        System.out.println(-1);
                        return;
                    }

                    setTypesPyramid(pyramid); //type1 type2 피라미드 2층까지 채우기
                }
                continue;
            }

            //type1과 type2 채우기
            for (int j = 0; j <= i; j++) {
                pyramid[1][i][j] = rpNum[j % 3];
            }

            for (int j = 0; j <= i; j++) {
                pyramid[2][i][j] = pyramid[1][i][i - j];
            }

            rpNum = getNextRpNum(rpNum);
        }

        //비교해 준다.
        int answer = Integer.MAX_VALUE;
        for (int k = 1; k <= 2; k++) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                //먼저 swapList를 생성해 준다.
                int[][] swapList = new int[3][3]; // [A][B] A를 B로 스왑해야 됨
                for (int j = 0; j <= i; j++) {
                    if (pyramid[0][i][j] != pyramid[k][i][j]) {
                        int A = pyramid[0][i][j];
                        int B = pyramid[k][i][j];
                        swapList[A][B] += 1;
                    }
                }
                //우선적으로 서로 원하는 블록끼리
                for (int j = 0; j < 3; j++) {
                    for (int l = 0; l < 3; l++) {
                        if (swapList[j][l] > 0) {
                            int pair = Math.min(swapList[j][l], swapList[l][j]);
                            swapList[j][l] -= pair;
                            swapList[l][j] -= pair;
                            cnt += pair;
                        }
                    }
                }

                //남아 있는 개수가 전부 같아야됨.
                int left = findLeft(swapList);
                if (left == -1) {
                    System.out.println(-1);
                    return;
                }

                cnt += left * 2; //한 번씩 스왑되기 때문에

            }
            answer = Math.min(answer, cnt);
        }
        System.out.println(answer);

    }

    static int findLeft(int[][] swapList) {
        ArrayList < Block > list = new ArrayList < > ();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (swapList[i][j] > 0) {
                    if (list.size() != 0 && list.get(list.size() - 1).A == i) {
                        return -1;
                    }
                    list.add(new Block(i, swapList[i][j]));
                }
            }
        }
        if (list.size() <= 2) {
            if (list.size() == 0) {
                return 0;
            }
            return -1;
        }

        int left = list.get(0).cnt;
        for (int i = 1; i < list.size(); i++) {
            if (left != list.get(i).cnt) {
                return -1;
            }
        }
        return left;
    }

    static int[] getNextRpNum(int[] rpNum) {
        int[] nextRpNum = new int[3];
        nextRpNum[0] = rpNum[1];
        nextRpNum[1] = rpNum[2];
        nextRpNum[2] = rpNum[0];
        return nextRpNum;
    }


    static boolean check(int[] rpNum) {
        boolean ck[] = new boolean[3];
        for (int i = 0; i < 3; i++) {
            if (ck[rpNum[i]]) {
                return false;
            }
            ck[rpNum[i]] = true;
        }
        return true;
    }

    static void setTypesPyramid(int[][][] pyramid) {
        pyramid[1][0][0] = pyramid[0][0][0];
        pyramid[2][0][0] = pyramid[0][0][0];

        pyramid[1][1][0] = pyramid[0][1][0];
        pyramid[1][1][1] = pyramid[0][1][1];

        pyramid[2][1][0] = pyramid[0][1][1];
        pyramid[2][1][1] = pyramid[0][1][0];
    }
}