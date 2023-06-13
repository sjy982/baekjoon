import java.util.*;
class Solution {
    static ArrayList<Integer>[][][][] info_list = new ArrayList[4][3][3][3];
    static ArrayList<Integer> result = new ArrayList<>();
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        Arrays.sort(info, (a, b) -> {
            String[] split_a = a.split(" ");
            String[] split_b = b.split(" ");
            int dif = Integer.parseInt(split_a[4]) - Integer.parseInt(split_b[4]);
            if(dif <= 0) return -1;
            else return 1;
        });
        for(int i=0; i<info.length; i++) {
            String[] split_info = info[i].split(" ");
            DFS(find_index(split_info[0]), find_index(split_info[1]), find_index(split_info[2]), find_index(split_info[3]), Integer.parseInt(split_info[4]));
        }
        //탐색
        for(int i=0; i<query.length; i++) {
            String[] split_query = query[i].split(" ");
            answer[i] = find_answer(info_list[find_index(split_query[0])][find_index(split_query[2])][find_index(split_query[4])][find_index(split_query[6])], Integer.parseInt(split_query[7]));
        }
        return answer;
    }
    
    static int find_answer(ArrayList<Integer> list, int value) {
        if(list == null) return 0;
        int min = 0;
        int max = list.size() - 1;
        while(min <= max) {
            int mid = (min + max) / 2;
            if(list.get(mid) >= value) max = mid - 1;
            else min = mid + 1;
        }
        return (list.size() - 1) - min + 1;
    }
    
    static void DFS(int i1, int i2, int i3, int i4, int score) {
        if(result.size() == 4) {
            int ind1 = result.get(0) == 1 ? i1 : 0;
            int ind2 = result.get(1) == 1 ? i2 : 0;
            int ind3 = result.get(2) == 1 ? i3 : 0;
            int ind4 = result.get(3) == 1 ? i4 : 0;
            if(info_list[ind1][ind2][ind3][ind4] == null) info_list[ind1][ind2][ind3][ind4] = new ArrayList<>();
            info_list[ind1][ind2][ind3][ind4].add(score);
            return;
        }
        for(int i=0; i<2; i++) {
            //0은 -, 1은 선택
            result.add(i);
            DFS(i1, i2, i3, i4, score);
            result.remove(result.size()-1);
        }
    }
    
    static int find_index(String item) {
        if(item.equals("cpp") || item.equals("backend") || item.equals("junior") || item.equals("chicken")) return 1;
        else if(item.equals("java") || item.equals("frontend") || item.equals("senior") || item.equals("pizza")) return 2;
        else if(item.equals("python")) return 3;
        return 0; // "-"
    }
}