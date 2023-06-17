import java.util.*;
class Solution {
    static HashSet<Integer> result = new HashSet<>();
    static ArrayList<HashSet<Integer>> cd_key_list = new ArrayList<>();
    static int answer = 0;
    public int solution(String[][] relation) {
        for(int i=1; i<=relation[0].length; i++) {
            DFS(i, relation, 0);
        }
        return answer;
    }
    
    static void DFS(int depth, String[][] relation, int ind) {
        if(result.size() == depth) {
            if(included_set()) {
                if(isCdKey(relation)) {
                    HashSet<Integer> set = new HashSet<>();
                    set.addAll(result);
                    cd_key_list.add(set);
                    answer += 1;
                }
            }
            return;
        }
        for(int i=ind; i<relation[0].length; i++) {
            result.add(i);
            DFS(depth, relation, i + 1);
            result.remove(i);
        }
    }

    static boolean isCdKey(String[][] relation) {
        ArrayList<String> tuple_list = new ArrayList<>();
        for(int i=0; i<relation.length; i++) {
            StringBuilder tuple = new StringBuilder();
            Iterator<Integer> it = result.iterator();
            while(it.hasNext()) tuple.append(relation[i][it.next()] + " ");
            //check
            for(int j=0; j<tuple_list.size(); j++) {
                if(tuple.toString().trim().equals(tuple_list.get(j))) return false;
            }
            tuple_list.add(tuple.toString().trim());
        }
        return true;
    }
    
    static boolean included_set() {
        for(int i=0; i<cd_key_list.size(); i++) {
            if(result.containsAll(cd_key_list.get(i))) return false;
        }
        return true;
    }
}