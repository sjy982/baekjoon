import java.util.*;
class Node {
    int sheep, wolf;
    Node(int sheep, int wolf) {
        this.sheep = sheep;
        this.wolf = wolf;
    }
}
class Solution {
    static boolean[] visited = new boolean[(int)Math.pow(2, 17) + 1];
    static ArrayList<Integer>[] tree;
    static int answer = -1;
    public int solution(int[] info, int[][] edges) {
        tree = new ArrayList[info.length];
        for(int i=0; i<tree.length; i++) tree[i] = new ArrayList<>();
        for(int i=0; i<edges.length; i++) tree[edges[i][0]].add(edges[i][1]);
        ArrayList<Integer> node_list = new ArrayList<>();
        node_list.add(0);
        visited[1] = true;
        DFS(node_list, 1, 0, new Node(1,0), info);
        return answer;
    }
    
    static void DFS(ArrayList<Integer> node_list, int set, int cur_n, Node info_n, int[] info) {
        for(int i=0; i<tree[cur_n].size(); i++) node_list.add(tree[cur_n].get(i)); //자식 노드 추가
        node_list.remove(Integer.valueOf(cur_n)); //현재 노드 삭제
        if((info_n.sheep == info_n.wolf) || (node_list.size() == 0)) {
            //더이상 갈 수 없거나, 다음 탐색 노드가 없는 경우 업데이트
            answer = Math.max(answer, info_n.sheep);
            return;
        }
        for(int i=0; i<node_list.size(); i++) {
            int b = node_list.get(i);
            int next_set = set | (1 << b);
            if(!visited[next_set]) {
                visited[next_set] = true;
                Node next_info = new Node(info_n.sheep, info_n.wolf);
                ArrayList<Integer> copy_node_list = deep_copy(node_list);
                if(info[b] == 0) next_info.sheep += 1;
                else next_info.wolf += 1;
                DFS(copy_node_list, next_set, b, next_info, info);
            }
        }
    }
    static ArrayList<Integer> deep_copy(ArrayList<Integer> list) {
        ArrayList<Integer> node_list = new ArrayList<>();
        node_list.addAll(list);
        return node_list;
    }
}