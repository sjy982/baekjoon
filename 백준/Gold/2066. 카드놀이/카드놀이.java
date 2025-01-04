import java.io.*;
import java.util.*;

class Pair {
    int cInd1, cInd2;
    
    Pair(int c1, int c2) {
        this.cInd1 = c1;
        this.cInd2 = c2;
    }
}

public class Main {
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String[][] cards = new String[10][5];
      for(int i=1; i<=9; i++) {
          StringTokenizer st = new StringTokenizer(br.readLine());
          for(int j=1; j<=4; j++) {
              cards[i][j] = st.nextToken();
          }
      }
      double[][][][][][][][][] dp = new double[5][5][5][5][5][5][5][5][5]; //초기값 0, 불가능 -1
      int[] g = new int[10];
      for(int i=1; i<=9; i++) {
          g[i] = 4;
      }
      
      System.out.printf("%.6f\n", findAnswer(g, cards, dp));
      
  }
  
  public static double findAnswer(int[] g, String[][] cards, double[][][][][][][][][] dp) {
      if(g[1] == 0 && g[2] == 0 && g[3] == 0 && g[4] == 0 && g[5] == 0 && g[6] == 0 && g[7] == 0 && g[8] == 0 && g[9] == 0) {
          return 1.0;
      }
      
      if(dp[g[1]][g[2]][g[3]][g[4]][g[5]][g[6]][g[7]][g[8]][g[9]] > 0) {
          return dp[g[1]][g[2]][g[3]][g[4]][g[5]][g[6]][g[7]][g[8]][g[9]];
      }
      
      if(dp[g[1]][g[2]][g[3]][g[4]][g[5]][g[6]][g[7]][g[8]][g[9]] == -1) {
          return 0.0;
      }
      
      ArrayList<Pair> pairs = findPairs(g, cards);
      
      if(pairs.size() == 0) {
          dp[g[1]][g[2]][g[3]][g[4]][g[5]][g[6]][g[7]][g[8]][g[9]] = -1;
          return 0.0;
      }
      
      double result = 0.0;
      double prob = 1.0 / pairs.size(); //확률
      for(int i=0; i<pairs.size(); i++) {
          int[] cG = copy(g);
          cG[pairs.get(i).cInd1] -= 1;
          cG[pairs.get(i).cInd2] -= 1;
          
          double nr = findAnswer(cG, cards, dp);
          result += (nr * prob); 
      }
      
      if(result == 0) {
          dp[g[1]][g[2]][g[3]][g[4]][g[5]][g[6]][g[7]][g[8]][g[9]] = -1;
      } else {
          dp[g[1]][g[2]][g[3]][g[4]][g[5]][g[6]][g[7]][g[8]][g[9]] = result;
      }
      
      return result;
  }
  
  public static ArrayList<Pair> findPairs(int[] g, String[][] cards) {
      ArrayList<Pair> result = new ArrayList<>();
      for(int i=1; i<=8; i++) {
          for(int j=i + 1; j<=9; j++) {
              if(g[i] == 0 || g[j] == 0) {
                  continue;
              }
              if(cards[i][g[i]].charAt(0) == cards[j][g[j]].charAt(0)) {
                  //숫자가 같다면
                  result.add(new Pair(i, j));
              }
          }
      }
      return result;
  }
  
  public static int[] copy(int[] arr) {
      int[] result = new int[arr.length];
      for(int i=0; i<arr.length; i++) {
          result[i] = arr[i];
      }
      return result;
  }
}