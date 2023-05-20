class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for(int i=0; i<numbers.length; i++) {
            String sat_binary = make_saturated_binary_tree(Long.toBinaryString(numbers[i]));
            if(sat_binary.charAt(sat_binary.length() / 2) == '0') answer[i] = 0;
            else {
                if(possible('1', sat_binary)) answer[i] = 1;
                else answer[i] = 0;
            }
        }
        return answer;
    }
    static boolean possible(char parent_node, String binary) {
        int mid_ind = binary.length() / 2;
        if(parent_node == '0' && binary.charAt(mid_ind) == '1') return false;
        if(binary.length() != 1) {
            if(!possible(binary.charAt(mid_ind), binary.substring(0, mid_ind))) return false;
            if(!possible(binary.charAt(mid_ind), binary.substring(mid_ind + 1, binary.length()))) return false;
        }
        return true;
    }
    
    static String make_saturated_binary_tree(String binary) {
        int node_cnt = 0;
        int tree_h = 0;
        while(node_cnt < binary.length()) {
            tree_h += 1;
            node_cnt += (int) Math.pow(2, tree_h-1);
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<node_cnt - binary.length(); i++) sb.append("0");
        sb.append(binary);
        return sb.toString();
    }
}