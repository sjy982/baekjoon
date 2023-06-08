import java.util.*;
class Solution {
    static ArrayList<ArrayList<String>> bannedId_list = new ArrayList<>();
    static ArrayList<String> result = new ArrayList<>();
    static boolean[] visited;
    static int answer = 0;
    public int solution(String[] user_id, String[] banned_id) {
        visited = new boolean[banned_id.length];
        permutation_DFS(banned_id);
        combination_DFS(0, user_id, banned_id.length);
        return answer;
    }
    static void combination_DFS(int ind, String[] user_id, int size) {
        //조합
        if(size == result.size()) {
            for(int i=0; i<bannedId_list.size(); i++) {
                boolean isPosi = true;
                for(int j=0; j<bannedId_list.get(i).size(); j++) {
                    String bannedId = bannedId_list.get(i).get(j);
                    String userId = result.get(j);
                    if(userId.length() != bannedId.length()) isPosi = false; 
                    else {
                        for(int k=0; k<bannedId.length(); k++) {
                            if(bannedId.charAt(k) != '*' && (bannedId.charAt(k) != userId.charAt(k))) {
                                isPosi = false;
                                break;
                            }
                        }
                    }
                    if(!isPosi) break;
                }
                if(isPosi) {
                    answer += 1;
                    break;
                }
            }
            return;
        }
        for(int i=ind; i<user_id.length; i++) {
            result.add(user_id[i]);
            combination_DFS(i+1, user_id, size);
            result.remove(result.size()-1);
        }
    }

    static void permutation_DFS(String[] banned_id) {
        //순열
        if(result.size() == banned_id.length) {
            ArrayList<String> list = new ArrayList<>();
            list.addAll(result);
            bannedId_list.add(list);
            return;
        }
        for(int i=0; i<banned_id.length; i++) {
            if(!visited[i]) {
                result.add(banned_id[i]);
                visited[i] = true;
                permutation_DFS(banned_id);
                result.remove(result.size()-1);
                visited[i] = false;
            }
        }
    }
}