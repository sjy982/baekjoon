import java.io.*;
import java.util.*;

public class Main {
    static int N;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      
      int[] A = new int[101];
      int[] B = new int[101];
      StringBuilder sb = new StringBuilder();
      for(int i=1; i<=N; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          int ai = Integer.parseInt(st.nextToken());
          int bi = Integer.parseInt(st.nextToken());
          
          A[ai] += 1;
          B[bi] += 1;
          
          int aCursor = 1;
          int bCursor = 100;
          int max = -1;
          int cntPair = 0;
          
          int[] cpA = copy(A);
          int[] cpB = copy(B);
          while(cntPair != i) {
              if(cpA[aCursor] == 0 || cpB[bCursor] == 0) {
                  // A, B에서 0이 아닌 지점을 찾는다.
                  if(cpA[aCursor] == 0) {
                      aCursor += 1;
                  }
                  if(cpB[bCursor] == 0) {
                      bCursor -= 1;
                  }
                  continue;
              }
              
              //이제 a, b cursor가 0이 아닌 지점을 가리키고 있음
              max = Math.max(max, aCursor + bCursor);
              if(cpA[aCursor] <= cpB[bCursor]) {
                  cpB[bCursor] -= cpA[aCursor];
                  cntPair += cpA[aCursor];
                  cpA[aCursor] = 0;
                  aCursor += 1;
              } else {
                  cpA[aCursor] -= cpB[bCursor];
                  cntPair += cpB[bCursor];
                  cpB[bCursor] = 0;
                  bCursor -= 1;
              }
          }
          sb.append(max).append("\n");
      }
      
      System.out.println(sb.toString().trim());
  }
  
  static int[] copy(int[] arr) {
      int[] result = new int[arr.length];
      for(int i=0; i<arr.length; i++) {
          result[i] = arr[i];
      }
      return result;
  }
}