import java.util.*;
class Node {
    int index, value;
    Node(int index, int value) {
        this.index = index;
        this.value = value;
    }
}
class Solution {
    static ArrayList<Node> sorted_a = new ArrayList<>();
    static HashMap<Integer, Boolean> visited = new HashMap<>();
    public int solution(int[] a) {
        if(a.length == 1) return 1;
        int answer = 1;
        int min = Integer.MAX_VALUE;
        int min_index = -1;
        for(int i=0; i<a.length; i++) {
            sorted_a.add(new Node(i, a[i]));
            if(min > a[i]) {
                min = a[i];
                min_index = i;
            }
        }
        Collections.sort(sorted_a, (o1, o2) -> {return o1.value - o2.value;});
        Queue<Integer> left_que = new LinkedList<>();
        for(int i=min_index - 1; i>=0; i--) left_que.add(a[i]);
        Queue<Integer> right_que = new LinkedList<>();
        for(int i=min_index + 1; i<a.length; i++) right_que.add(a[i]);
        int search_index = 1;
        while(left_que.size() != 0 || right_que.size() != 0) {
            int ind = sorted_a.get(search_index).index;
            int val = sorted_a.get(search_index).value;
            if(ind < min_index) {
                //왼쪽 큐에 존재한다.
                if(visited.get(val) == null) {
                    //아직 해당값이 나온적이 없음.
                    answer += 1;
                    pop_balloon(val, left_que);
                }
            } else if(ind > min_index) {
                //오른쪽 큐에 존재한다.
                if(visited.get(val) == null) {
                    answer += 1;
                    pop_balloon(val, right_que);
                }
            }
            search_index += 1;
        }
        return answer;
    }
    static void pop_balloon(int value, Queue<Integer> que) {
        while(true) {
            int n = que.poll();
            visited.put(n, true); //방문처리
            if(n == value) return;
        }
    }
}