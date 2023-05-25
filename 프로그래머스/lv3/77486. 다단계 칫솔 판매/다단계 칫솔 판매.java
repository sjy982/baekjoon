import java.util.*;
class Solution {
    static HashMap<String, ArrayList<String>> tree = new HashMap<>();
    static HashMap<String, ArrayList<Integer>> seller_map = new HashMap<>();
    static HashMap<String, Integer> answer_map = new HashMap<>();
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        tree.put("center", new ArrayList<>()); //root node
        seller_map.put("center", new ArrayList<>());
        for(int i=0; i<enroll.length; i++) {
            tree.put(enroll[i], new ArrayList<>());
            seller_map.put(enroll[i], new ArrayList<>());
        }
        for(int i=0; i<referral.length; i++) {
            if(referral[i].equals("-")) tree.get("center").add(enroll[i]);
            else tree.get(referral[i]).add(enroll[i]);
        }
        for(int i=0; i<seller.length; i++) seller_map.get(seller[i]).add(amount[i] * 100);
        DFS("center");
        for(int i=0; i<enroll.length; i++) answer[i] = answer_map.get(enroll[i]);
        return answer;
    }
    
    static ArrayList<Integer> DFS(String name) {
        if(tree.get(name).size() == 0) {
            ArrayList<Integer> distri_list = new ArrayList<>();
            int final_profit = 0;
            for(int i=0; i<seller_map.get(name).size(); i++) {
                distri_list.add(seller_map.get(name).get(i) / 10);
                final_profit += seller_map.get(name).get(i);
            }
            for(int i=0; i<distri_list.size(); i++) final_profit -= distri_list.get(i);
            answer_map.put(name, final_profit);
            return distri_list;
        }
        ArrayList<Integer> distri_list = new ArrayList<>(); //분산금 list
        int final_profit = 0; //최종 금액
        for(int i=0; i<seller_map.get(name).size(); i++) {
            distri_list.add(seller_map.get(name).get(i) / 10);
            final_profit += seller_map.get(name).get(i);
        }
        for(int i=0; i<tree.get(name).size(); i++) {
            final_profit += sum(distri_list, DFS(tree.get(name).get(i)));
        }
        for(int i=0; i<distri_list.size(); i++) final_profit -= distri_list.get(i);
        answer_map.put(name, final_profit);
        return distri_list;
    }
    
    static int sum(ArrayList<Integer> cur, ArrayList<Integer> lower) {
        int sum = 0;
        for(int i=0; i<lower.size(); i++) {
            sum += lower.get(i);
            int distri = lower.get(i) / 10;
            if(distri != 0) cur.add(distri);
        }
        return sum;
    }
}