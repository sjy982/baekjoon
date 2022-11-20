import java.io.*;
import java.util.*;

class Expression {
    char op;
    long v;
    Expression(char op, long v) {
        this.op = op;
        this.v = v;
    }
}

public class Main {
    static final long MOD = 1000000007L;
    static int N;
    static long K;
    static boolean LIMIT = false;
    static Expression x1;
    static Expression x2;
    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      K = Long.parseLong(st.nextToken());
      for(int i=0; i<N; i++) {
          StringTokenizer sti = new StringTokenizer(br.readLine());
          String s_x1 = sti.nextToken();
          String s_x2 = sti.nextToken();
          x1 = new Expression(s_x1.charAt(0), Long.parseLong(s_x1.substring(1, s_x1.length())));
          x2 = new Expression(s_x2.charAt(0), Long.parseLong(s_x2.substring(1, s_x2.length())));
          if(x2.op == '*') swap(x1, x2);
          if(x1.op != '*' && x2.op == '+') swap(x1, x2);
          
          if(x1.op == '*' && x2.op == '*') {
              long xv = Math.max(x1.v, x2.v);
              K = multiply(K, xv);
          } else if(x1.op == '*' && x2.op == '-') {
              if(x1.v != 0) K = multiply(K, x1.v);
              else K = sub(K, x2.v);
          } else if(x1.op == '+' && x2.op == '+') {
              long xv = Math.max(x1.v, x2.v);
              K = add(K, xv);
          } else if(x1.op == '+' && x2.op == '-') {
              K = add(K, x1.v);
          } else if(x1.op == '-' && x2.op == '-') {
              long xv = Math.min(x1.v, x2.v);
              K = sub(K, xv);
          } else {
              if(x1.v == 0 || x1.v == 1) {
                  K = add(K, x2.v);
              } else if(LIMIT) {
                  K = multiply(K, x1.v);
              } else if(K >= 1e9) {
                  K = multiply(K, x1.v);
              } else {
                  long r_k1 = K * x1.v;
                  long r_k2 = K + x2.v;
                  if(r_k1 > r_k2) K = multiply(K, x1.v);
                  else K = K = add(K, x2.v);
              }
          }
      }
      K %= MOD;
      System.out.println(K);
    }
    static long add(long k, long x) {
        k += x;
        if(!LIMIT && k >= 1e15) {
            LIMIT = true;
            k %= MOD;
        }
        return k;
    }
    
    static long sub(long k, long x) {
        if(LIMIT) {
            k = (k + MOD - x) % MOD;
            return k;
        }
        k -= x;
        if(k < 0) k = 0;
        return k;
    }
    
    static long multiply(long k, long x) {
        if(x == 0) {
            LIMIT = false;
            return 0;
        }
        if(!LIMIT && k/1000000*x >= 1e9) {
            LIMIT = true;
            k %= MOD;
            x %= MOD;
        }
        if(LIMIT) {
            k *= x;
            k %= MOD;
        } else {
            k *= x;
        }
        return k;
    }
    
    static void swap(Expression a, Expression b) {
        x1 = b;
        x2 = a;
    }
}