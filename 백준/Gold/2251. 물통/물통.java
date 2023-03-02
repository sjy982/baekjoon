import java.io.*;
import java.util.*;

class Node {
    int a,b,c;
    Node(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}

public class Main {
    static int volume[] = new int[3]; //0 -> A, 1 -> B, 2 -> C
    static boolean c_visited[] = new boolean[201];
    static HashMap<Integer, Boolean> visited = new HashMap<>();
    static ArrayList<Integer> ans_list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        volume[0] = Integer.parseInt(st.nextToken());
        volume[1] = Integer.parseInt(st.nextToken());
        volume[2] = Integer.parseInt(st.nextToken());
        BFS();
        Collections.sort(ans_list);
        for(int i=0; i<ans_list.size(); i++) {
            sb.append(ans_list.get(i) + " ");
        }
        System.out.println(sb.toString().trim());
    }
    
    static void BFS() {
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(0, 0, volume[2])); 
        visited.put(volume[2], true); // a * 1000000 + b * 1000 + c
        while(que.size()!=0) {
            Node n = que.poll();
            if(n.a==0) {
                if(!c_visited[n.c]) {
                    ans_list.add(n.c);
                    c_visited[n.c] = true;
                }
            }
            if(n.a!=0) {
                for(int i=0; i<2; i++) {
                    Node new_n;
                    if(i==0) new_n = pour_water(n, 0, 1);
                    else new_n = pour_water(n, 0, 2);
                    //방문 검사
                    int vis_num = new_n.a * 1000000 + new_n.b * 1000 + new_n.c;
                    if(visited.get(vis_num) == null) {
                        que.add(new_n);
                        visited.put(vis_num, true);
                    }
                }
            }
            if(n.b!=0) {
                for(int i=0; i<2; i++) {
                    Node new_n;
                    if(i==0) new_n = pour_water(n, 1, 0);
                    else new_n = pour_water(n, 1, 2);
                    //방문 검사
                    int vis_num = new_n.a * 1000000 + new_n.b * 1000 + new_n.c;
                    if(visited.get(vis_num) == null) {
                        que.add(new_n);
                        visited.put(vis_num, true);
                    }
                }
            }
            if(n.c!=0) {
                for(int i=0; i<2; i++) {
                    Node new_n;
                    if(i==0) new_n = pour_water(n, 2, 0);
                    else new_n = pour_water(n, 2, 1);
                    //방문 검사
                    int vis_num = new_n.a * 1000000 + new_n.b * 1000 + new_n.c;
                    if(visited.get(vis_num) == null) {
                        que.add(new_n);
                        visited.put(vis_num, true);
                    }
                }
            }
        }
    }
    
    static Node pour_water(Node n, int from, int to) {
        int arr[] = {n.a, n.b, n.c};
        arr[to] += arr[from];
        arr[from] = 0;
        if(arr[to] > volume[to]) {
            arr[from] = arr[to] - volume[to];
            arr[to] = volume[to];
        }
        return new Node(arr[0], arr[1], arr[2]);
    }
}