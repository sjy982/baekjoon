import java.io.*;
import java.util.*;

class Student {
    int n, e;
    Student(int n , int e) {
        this.n = n;
        this.e = e;
    }
}

public class Main  {
    static int T;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        for(int k=0; k<T; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            ArrayList<Student>[] range = new ArrayList[N + 1];
            boolean[] visited = new boolean[M + 1];
            
            for(int i=1; i<=N; i++) {
                range[i] = new ArrayList<>();
            }
            
            for(int i=1; i<=M; i++) {
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                marking(range, i, Integer.parseInt(st2.nextToken()), Integer.parseInt(st2.nextToken()));
            }
            
            int answer = 0;
            
            for(int i=1; i<=N; i++) {
                if(aloc(range[i], visited)) {
                    answer += 1;
                }
            }
            
            sb.append(answer).append('\n');
        }
        System.out.println(sb.toString().trim());
    }
    
    static void marking(ArrayList<Student>[] range, int n, int s, int e) {
        Student student = new Student(n, e);
        for(int i=s; i<=e; i++) {
            range[i].add(student);
        }
    }
    
    static boolean aloc(ArrayList<Student> list, boolean[] visited ) {
        Student student = new Student(1001, 1001);
        for(int i=0; i<list.size(); i++) {
            if(!visited[list.get(i).n] && student.e > list.get(i).e) {
                student = list.get(i);
            }
        }
        if(student.n == 1001) {
            return false;
        }
        
        visited[student.n] = true;
        return true;
    }
}