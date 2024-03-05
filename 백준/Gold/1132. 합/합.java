import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int rowInd;
    long w;
    Node(int rowInd, long w) {
        this.rowInd = rowInd;
        this.w = w;
    }
    
    @Override
    public int compareTo(Node o) {
        if(this.w < o.w) {
            return 1;
        } else if(this.w > o.w) {
            return -1;
        }
        return 0;
    }
}

public class Main {
    static int N;
    static int[][] arr = new int[10][12];
    static boolean[] fst = new boolean[10];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<str.length(); j++) {
                if(j == 0) {
                    fst[parseRowInd(str.charAt(j))] = true;
                }
                fill(str.charAt(j), (str.length() - 1) - j);
            }
        }
        ArrayList<Node> list = new ArrayList<>();
        cal(list);
        Collections.sort(list);
        
        int[] w = new int[10];
        for(int i=0; i<w.length; i++) {
            w[i] = -1;
        }
        for(int i=0; i<list.size(); i++) {
            w[list.get(i).rowInd] = 9 - i;
        }
        if(checkZero(w)) {
            swap(w);
        }
        System.out.println(answerCal(w));
    }
    
    static void swap(int [] w) {
        int v = 1;
        while(true) {
            int rowInd = -1;
            int swapInd = -1;
            for(int i=0; i<w.length; i++) {
                if(w[i] == 0) {
                    rowInd = i;
                } else if(w[i] == v) {
                    swapInd = i;
                }
            }
            if(!fst[rowInd]) {
                return;
            } 
            w[swapInd] = 0;
            w[rowInd] = v;
            v += 1;
        }
    }
    
    static boolean checkZero(int[] w) {
        for(int i=0; i<w.length; i++) {
            if(w[i] == 0) {
                return true;
            }
        }
        return false;
    }
    
    static long answerCal(int[] w) {
        long result = 0;
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[i].length; j++) {
                result += (arr[i][j] * w[i]) * (long) Math.pow(10, j); //10^j
            }
        }
        return result;
    }
    
    static void cal(ArrayList<Node> list) {
        for(int i=0; i<arr.length; i++) {
            long value = 0;
            for(int j=0; j<arr[i].length; j++) {
                value += arr[i][j] * (long) Math.pow(10, j); //10^j;
            }
            if(value != 0) {
                list.add(new Node(i, value));
            }
        }
    }
    
    static void fill(Character c, int ind) {
        //ind -> [c][ind] -> 10^ind
        arr[parseRowInd(c)][ind] += 1;
    }
    
    static int parseRowInd(Character c) {
        //row -> 행
        switch(c) {
            case 'A':
                return 0;
            case 'B':
                return 1;
            case 'C':
                return 2;
            case 'D':
                return 3;
                
            case 'E':
                return 4;
                
            case 'F':
                return 5;
                
            case 'G':
                return 6;
                
            case 'H':
                return 7;
                
            case 'I':
                return 8;
        }
        return 9;
    }
}