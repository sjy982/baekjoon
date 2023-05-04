import java.util.*;
class Solution {
    static ArrayList<Integer> box_group = new ArrayList<>();
    static boolean[] visited;
    public int solution(int[] cards) {
        int answer = 0;
        visited = new boolean[cards.length];
        for(int i=0; i<cards.length; i++) {
            int next = i;
            int cout = 0;
            while(!visited[next]) {
                visited[next] = true;
                next = cards[next]-1;
                cout += 1;
            }
            if(cout != 0) box_group.add(cout);
        }
        if(box_group.size() != 1) {
            Collections.sort(box_group, Collections.reverseOrder());
            answer = box_group.get(0) * box_group.get(1);
        }
        return answer;
    }
}