import java.util.*;
class Solution {
    static ArrayList<ArrayList<Integer>> weakOrder_list = new ArrayList<>();
    static ArrayList<Integer> result = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;
    static boolean[] visited;
    public int solution(int n, int[] weak, int[] dist) {
        visited = new boolean[dist.length];
        for(int i=0; i<weak.length; i++) {
            ArrayList<Integer> weak_list = new ArrayList<>();
            for(int j=i; j < i + weak.length; j++) {
                if(j < weak.length) weak_list.add(weak[j]); 
                else weak_list.add(weak[j - weak.length] + n);
            }
            weakOrder_list.add(weak_list);
        }
        DFS(dist);
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    static void DFS(int[] dist) {
        if(result.size() == dist.length) {
            for(int i=0; i<weakOrder_list.size(); i++) {
                ArrayList<Integer> weak_list = weakOrder_list.get(i);
                int weak_ind = 0;
                int cnt = 0;
                for(int j=0; j<result.size(); j++) {
                    int end = result.get(j) + weak_list.get(weak_ind);
                    weak_ind = search_nextInd(end, weak_ind, weak_list);
                    cnt += 1;
                    if(weak_ind == -1) {
                        //모든 취약 지점을 점검함.
                        answer = Math.min(answer, cnt);
                        break;
                    }
                }
            }
            return;
        }
        for(int i=0; i<dist.length; i++) {
            if(!visited[i]) {
                result.add(dist[i]);
                visited[i] = true;
                DFS(dist);
                result.remove(result.size()-1);
                visited[i] = false;
            }
        }
    }
    
    static int search_nextInd(int end, int weak_ind, ArrayList<Integer> weak_list) {
        for(int i=weak_ind + 1; i<weak_list.size(); i++) {
            if(weak_list.get(i) > end) return i;
        }
        return -1; //
    }
}