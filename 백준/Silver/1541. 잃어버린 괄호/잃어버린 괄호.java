import java.io.*;
import java.util.*;

public class Main {
    static boolean minus = false;
    static char operator = '+';
    static StringBuilder operand = new StringBuilder();
    static int ans = 0;
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String ex = br.readLine();
      for(int i=0; i<ex.length(); i++) {
          char n = ex.charAt(i);
          if(n == '+') {
              ans = calculate(operator, ans, Integer.parseInt(operand.toString()));
              operand = new StringBuilder();
              if(minus) operator = '-';
              else operator = '+';
          } else if(n == '-') {
              if(i != 0) ans = calculate(operator, ans, Integer.parseInt(operand.toString()));
              operand = new StringBuilder();
              if(!minus) minus = true;
              operator = '-';
          } else {
              operand.append(n);
              if(i == ex.length() - 1) {
                  ans = calculate(operator, ans, Integer.parseInt(operand.toString()));
              }
          }
      }
      System.out.println(ans);
    }
    static int calculate(char op, int a, int b) {
        if(op == '+') {
            return a + b;
        } else {
            return a - b;
        }
    }
}