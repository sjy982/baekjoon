import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(String s) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        makeArrayList(s, list);
        sortLengthASC(list);
        int[] answer = new int[list.size()];
        boolean[] visited = new boolean[100001];
        for(int i=0; i<list.size(); i++) {
            for(int j=0; j<list.get(i).size(); j++) {
                int v = list.get(i).get(j);
                if(!visited[v]) {
                    visited[v] = true;
                    answer[i] = v;
                    break;
                }
            }
        }
        return answer;
    }
    
    static void makeArrayList(String s, ArrayList<ArrayList<Integer>> list) {
        String[] split = s.split("},");
        for(int i=0; i<split.length; i++) {
            StringBuilder sb = new StringBuilder();
            list.add(new ArrayList<>());
            for(int j=0; j<split[i].length(); j++) {
                if(split[i].charAt(j) != '{' && split[i].charAt(j) != '}') {
                    sb.append(split[i].charAt(j));
                }
            }
            String[] split2 = sb.toString().split(",");
            for(int k=0; k<split2.length; k++) {
                list.get(i).add(Integer.parseInt(split2[k]));
            }
        }
    }
    
    static void sortLengthASC(ArrayList<ArrayList<Integer>> list) {
        Collections.sort(list, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                if(o1.size() < o2.size()) {
                    return -1;
                } else if(o1.size() > o2.size()) {
                    return 1;
                }
                return 0;
            }
        });
    }
}