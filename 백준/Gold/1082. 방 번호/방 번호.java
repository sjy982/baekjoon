import java.io.*;
import java.util.*;

class Product implements Comparable<Product> {
    int p, n;
    Product(int p, int n) {
        this.p = p;
        this.n = n;
    }
    
    @Override
    public int compareTo(Product o) {
        if(this.p < o.p) {
            return 1;
        } else if(this.p > o.p) {
            return -1;
        } 
        return 0;
    }
}

public class Main {
    static int N, M;
    static Product[] list, numOrderList;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new Product[N];
        numOrderList = new Product[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            Product pd = new Product(Integer.parseInt(st.nextToken()), i);
            list[i] = pd;
            numOrderList[i] = pd;
        }
        Arrays.sort(list);
        M = Integer.parseInt(br.readLine());
        
        Stack<Product> stack = new Stack();
        init(stack);
        
        if(stack.peek().n == 0) {
            System.out.println("0");
        } else {
            System.out.println(answer(stack));
        }
    }
    
    static String answer(Stack<Product> stack) {
        int curV = 0;
        for(Product p: stack) {
            curV += p.p;
        }
        StringBuilder sb = new StringBuilder();
        while(stack.size()!=0) {
            Product top = stack.pop();
            curV -= top.p;
            int left = M - curV;
            for(int i=numOrderList.length - 1; i>=0; i--) {
                if(left >= numOrderList[i].p) {
                    sb.append(numOrderList[i].n);
                    curV += numOrderList[i].p;
                    break;
                }
            }
        }
        return sb.toString();
    }
    
    static void init(Stack<Product> stack) {
        int mo = M;
        Product fst = list[N-1]; //가장 가격이 작은 물품
        if(fst.n == 0 && N != 1) {
            Product sec = list[N-2];
            while((mo - sec.p) >= 0) {
                stack.push(fst);
                mo -= fst.p;
            }
            if(stack.size() != 0) {
                stack.pop();
                stack.push(sec);
            } else {
                stack.push(fst); //이때는 답이 0
            }
        } else {
            while((mo - fst.p) >= 0) {
                stack.push(fst);
                mo -= fst.p;
            }
        }
    }
}