import java.io.*;
import java.util.*;

class XYZ {
    long X, Y, Z;
    int lInd, rInd; //각 문자의 개수
    
    public XYZ(long X, long Y, long Z, int lInd, int rInd) {
        this.X = X;
        this.Y = Y;
        this.Z = Z;
        this.lInd = lInd;
        this.rInd = rInd;
    }
    
    public long getLen() {
        return X + Y + Z;
    }
    
    public long getCharLen(char c) {
        if(c == 'X') {
            return X;
        } else if(c == 'Y') {
            return Y;
        }
        return Z;
    }
}

public class Main {
    static int T, N;
    static long k;
    static char c;
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      T = Integer.parseInt(br.readLine());
      N = Integer.parseInt(br.readLine());
      if(T == 2) {
          k = Long.parseLong(br.readLine());
      } else if(T == 3) {
          c = br.readLine().charAt(0);
      }
      
      
      
      XYZ[] dp = new XYZ[N + 1];
      dp[1] = new XYZ(1, 0, 0, -1, -1);
      if(N >= 2) {
          dp[2] = new XYZ(0, 1, 1, -1, -1);
      }
      
      if(N >= 3) {
          dp[3] = new XYZ(1, 0, 1, -1, -1);
      }
      for(int i=3; i<N; i++) {
          int left = i - 2;
          int right = i - 1;
          dp[i + 1] = new XYZ(dp[left].X + dp[right].X,
                              dp[left].Y + dp[right].Y,
                              dp[left].Z + dp[right].Z,
                              left, 
                              right);
      }
      
      if(T == 1) {
          System.out.println(dp[N].getLen());
      } else if(T == 3) {
          System.out.println(dp[N].getCharLen(c));
      } else if(T == 2) {
          String[] strs = new String[4];
          strs[1] = "X";
          strs[2] = "YZ";
          strs[3] = "ZX";
          
          int curInd = N;
          while(curInd > 3) {
              XYZ left = dp[dp[curInd].lInd];
              XYZ right = dp[dp[curInd].rInd];
              if(k <= left.getLen()) {
                  //왼쪽 XYZ에 포함된다면
                  curInd = dp[curInd].lInd;
              } else {
                  //오른쪽 XYZ에 포함된다면
                  k = k - left.getLen();
                  curInd = dp[curInd].rInd;
              }
          }
          System.out.println(strs[curInd].charAt((int) k - 1));
      }
  }
}