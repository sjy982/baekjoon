import java.io.*;
import java.util.*;

class Word {
    String s;
    int[] wc; //word count;
    Word(String s) {
        this.s = s;
        wc = new int[26];
        fillWc(this.s, this.wc);
    }
    
    static void fillWc(String str, int[] wordCount) {
        for(int i=0; i<str.length(); i++) {
            wordCount[transCharToInt(str.charAt(i))] += 1;
        }
    }
    
    static int transCharToInt(char c) {
        return (int) c - 97;
    }
}

public class Main {
  public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String str = br.readLine();
      int N = Integer.parseInt(br.readLine());
      ArrayList<Word> wList = new ArrayList<>();
      for(int i=0; i<N; i++) {
          wList.add(new Word(br.readLine()));
      }
      
      int[] dp = new int[str.length() + 1];
      
      for(int i=1; i<=str.length(); i++) {
          int cost = Integer.MAX_VALUE;
          for(int j=i - 1; j>=0; j--) {
              if(dp[j] != -1) {
                  //앞에 분할된 부분이 단어로 만들어질 수 있다면
                  String leftStr = str.substring(j, i);
                  ArrayList<Word> list = new ArrayList<>();
                  for(int k=0; k<N; k++) {
                      //가능한 단어를 일단 넣어준다.
                      if(isInclude(leftStr, wList.get(k))) {
                          list.add(wList.get(k));
                      }
                  }
                  
                  if(list.size() != 0) {
                      //가능한 단어가 있다면 그 중 가장 작은 비용을 구한다.
                      int min = Integer.MAX_VALUE;
                      for(int k=0; k<list.size(); k++) {
                          min = Math.min(min, findCost(leftStr, list.get(k).s));
                      }
                      cost = Math.min(cost, dp[j] + min);
                  }
              }
          }
          dp[i] = cost;
          if(cost == Integer.MAX_VALUE) {
              dp[i] = -1;
          }
      }
      
      System.out.println(dp[str.length()]);
  }
  
  static boolean isInclude(String stdStr, Word w) {
      //포함되었는지 체크하는 함수.
      int[] copyWc = new int[26];
      for(int i=0; i<26; i++) {
         copyWc[i] = w.wc[i];
      }
      
      for(int i=0; i<stdStr.length(); i++) {
          int ind = Word.transCharToInt(stdStr.charAt(i));
          copyWc[ind] -= 1;
      }
      
      for(int i=0; i<26; i++) {
          if(copyWc[i] != 0) {
              return false;
          }
      }
      return true;
  }
  
  static int findCost(String stdStr, String word) {
      //이 함수에 들어오는 두 단어는 길이도 같으며 요소도 같음 (isInclude를 통과함)
      int result = 0;
      for(int i=0; i<stdStr.length(); i++) {
          if(stdStr.charAt(i) != word.charAt(i)) {
              result += 1;
          }
      }
      return result;
  }
}