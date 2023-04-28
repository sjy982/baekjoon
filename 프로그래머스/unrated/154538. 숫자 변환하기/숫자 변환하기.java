import java.util.*;
class Solution {
    static boolean[] visited = new boolean[1000001];
    public int solution(int x, int y, int n) {
        int answer = 0;
        answer = BFS(x, y, n);
        return answer;
    }
    static int BFS(int x, int y, int n) {
        Queue<Integer> que = new LinkedList<>();
        que.add(x);
        visited[x] = true;
        int cout = 0;
        while(que.size() != 0) {
            int sz = que.size();
            for(int i=0; i<sz; i++) {
                int v = que.poll();
                if(v == y) return cout;
                for(int j=0; j<3; j++) {
                    int nv = v;
                    if(j==0) nv += n;
                    else if(j==1) nv *= 2;
                    else nv *= 3;
                    if(nv<=y && !visited[nv]) {
                        que.add(nv);
                        visited[nv] = true;
                    }
                }
            }
            cout += 1;
        }
        return -1;
    }
}