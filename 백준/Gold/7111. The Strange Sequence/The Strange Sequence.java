import java.io.*;
import java.util.*;

class Num {
    String strNum;
    int sum, len; //자릿수의 합
    
    Num(String strNum) {
        this.strNum = strNum;
        this.sum = getDigitsSum(strNum);
        this.len = strNum.length();
    }
    
    static public int getDigitsSum(String strNum) {
        int result = 0;
        for(int i=0; i<strNum.length(); i++) {
            result += strNum.charAt(i) - '0';
        }
        return result;
    }
}

public class Main {
    static int a, n;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      Num before = new Num(st.nextToken()); //a1 -> ai - 1
      n = Integer.parseInt(st.nextToken());
      
      for(int r=2; r<=n; r++) {
          //a2~an까지
          StringBuilder sb = new StringBuilder();
          int len = before.len;
          int reqSum = Num.getDigitsSum(String.valueOf(Long.parseLong(before.strNum) * (long) 4)); //필요한 합 ai-1 * 4의 자릿수 함
          int subCnt = len * 9 - reqSum; // 뺄 횟수 -> 이게 0이 되어야 자릿수의 합이 reqSum이 됨. 9 9 9..에서
          
          if(subCnt >= 0) {
              int spot = len - 1; //이 부분은 항상 전에 값의 자릿수보다 +1을 가져야됨 2번째 조건을 만족하기 위함
              //spot + 1 이상부터는 최대 자리수 차감 횟수가 9가 된다.
              while(spot >= 0) {
                  if(before.strNum.charAt(spot) == '9') {
                      //9인 경우에 +1하면 다음 자릿수가 +1되기 때문에 다음 자릿수로 넘겨준다.
                      spot -= 1;
                      continue;
                  }
                  
                  int copySubCnt = subCnt;
                  for(int i=0; i<len; i++) {
                      int subDigMax = 9 - (before.strNum.charAt(i) - '0'); //각 자릿수마다 최대로 뺄 수 있는 횟수;
                      if(i == spot) {
                          subDigMax -= 1;
                      } else if(spot + 1 <= i) {
                          subDigMax = 9;
                      }
                      
                      int subDigCnt = copySubCnt >= subDigMax ? subDigMax : copySubCnt; //실제로 뺄 횟수
                      copySubCnt -= subDigCnt;
                      sb.append(9 - subDigCnt);
                  }
                  
                  if(copySubCnt == 0) {
                      break;
                  }
                  
                  sb = new StringBuilder();
                  spot -= 1;
              }
              
              if(spot == -1) {
                  len += 1;
              }
              
          } else {
              len += 1;
          }
          
          if(before.len + 1 == len) {
              //이 경우는 ai-1보다 자릿수가 하나 더 있는 경우임 그래서 무조건 가능함.
              subCnt = len * 9 - reqSum;
              for(int i=0; i<len; i++) {
                  int subDigMax =9; //각 자릿수마다 최대로 뺄 수 있는 횟수; 
                  if(i == 0) {
                      subDigMax -= 1; //가장 앞의 자릿수는 0이 될 수 없음
                  }
                  int subDigCnt = subCnt >= subDigMax ? subDigMax : subCnt; //실제로 뺄 횟수
                  subCnt -= subDigCnt;
                  sb.append(9 - subDigCnt);
              }
          }
          
          before = new Num(sb.toString());
      }
      
      System.out.println(before.strNum);
  }
}