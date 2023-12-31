import java.util.*;
import java.io.*;

class Person {
    int x, y;
    Person(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N;
    static StringBuilder sb = new StringBuilder();
    static Person[] arr;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new Person[N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[i] = new Person(x, y);
        }
        
        for(int i=0; i<N; i++) {
            sb.append(findRank(arr[i], i)).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
    
    static int findRank(Person mp, int ind) {
        int rank = 1;
        for(int j=0; j<N; j++) {
            if(j != ind && check(mp, arr[j])) {
                rank += 1;
            }
        }
        return rank;
    }
    
    static boolean check(Person mp, Person p) {
        if((mp.x < p.x ) && (mp.y < p.y)) {
            return true;
        }
        return false;
    }
}