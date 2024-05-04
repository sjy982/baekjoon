import java.io.*;
import java.util.*;

public class Main {
    static int[][] A;
    static int N, M; //N은 행, M은 열
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        for(int i=0; i<N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                A[i][j] = Integer.parseInt(st2.nextToken());
            }
        }
        if(N == 2 && M == 2) {
            System.out.println(calArrB(A));
        }
        
        System.out.println(answer());
        
    }
    
    static int answer() {
        return Math.max(calRow(), calCol());
    }
    
    static int calRow() {
        int[] row = new int[M];
        for(int i=0; i<M; i++) {
            int v = 0;
            for(int j=0; j<N; j++) {
                if(j == 0 || j == N-1) {
                    //사이드인 경우
                    v += A[j][i] * 2;
                } else {
                    v += A[j][i] * 4;
                }
            }
            row[i] = v;
        }
        int ind = -1;
        if(row[0] <= row[M - 1]) {
            ind = M-1;
        } else {
            ind = 0;
        }
        int minInd = -1;
        
        for(int i=1; i<M-1; i++) {
            if(row[i] < row[ind]) {
                //작은 것 중에 가장 작은 값을 구해야 됨
                if(minInd == -1 || row[i] < row[minInd]) {
                    //최초거나 더 작은 값이면
                    minInd = i;
                }
            }
        }
        if(minInd == -1) {
            return calArrB(A);
        }
        int[][] copyA = deepCopyArr(A);
        swap(true, copyA, ind, minInd);
        return calArrB(copyA);
    }
    
    static int calCol() {
        int[] col = new int[N];
        for(int i=0; i<N; i++) {
            int v = 0;
            for(int j=0; j<M; j++) {
                if(j == 0 || j == M-1) {
                    v += A[i][j] * 2;
                } else {
                    v += A[i][j] * 4;
                }
            }
            col[i] = v;
        }
        int ind = -1;
        if(col[0] <= col[N-1]) {
            ind = N-1;
        } else {
            ind = 0;
        }
        int minInd = -1;
        for(int i=1; i<N-1; i++) {
            if(col[i] < col[ind]) {
                if(minInd == -1 || col[i] < col[minInd]) {
                    minInd = i;
                }
            }
        }
        if(minInd == -1) {
            return calArrB(A);
        }
        int[][] copyA = deepCopyArr(A);
        swap(false, copyA, ind, minInd);
        return calArrB(copyA);
    }
    
    static void swap(boolean isRow, int[][] arr, int ind1, int ind2) {
        int tmp = -1;
        if(isRow) {
            for(int i=0; i<N; i++) {
                tmp = arr[i][ind1];
                arr[i][ind1] = arr[i][ind2];
                arr[i][ind2] = tmp;
            }
        } else {
            for(int i=0; i<M; i++) {
                tmp = arr[ind1][i];
                arr[ind1][i] = arr[ind2][i];
                arr[ind2][i] = tmp;
            }
        }
    }
    
    static int[][] deepCopyArr(int[][] arr) {
        int[][] result = new int[arr.length][arr[0].length];
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[i].length; j++) {
                result[i][j] = arr[i][j];
            }
        }
        return result;
    } 
    
    
    static int calArrB(int[][] arr) {
        int result = 0;
        for(int i=0; i<N - 1; i++) {
            for(int j=0; j<M - 1; j++) {
                result += (arr[i][j] + arr[i + 1][j] + arr[i][j + 1] + arr[i + 1][j + 1]);
            }
        }
        return result;
    }
}