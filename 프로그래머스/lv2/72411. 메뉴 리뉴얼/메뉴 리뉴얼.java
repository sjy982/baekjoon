import java.util.*;
class Solution {
    static ArrayList<String>[][] combi_orders;
    static ArrayList<Integer> result = new ArrayList<>();
    static HashMap<String, Integer> course_map = new HashMap<>();
    static int[] max_orders = new int[11];
    static ArrayList<String> course_list = new ArrayList<>();
    public String[] solution(String[] orders, int[] course) {
        for(int i=0; i<orders.length; i++) {
            //문자열내 문자를 오름차순 정렬
            char[] charArr = orders[i].toCharArray();
            Arrays.sort(charArr);
            orders[i] = new String(charArr);
        }
        combi_orders = new ArrayList[orders.length][11];
        for(int i=0; i<course.length; i++) {
            for(int j=0; j<orders.length; j++) {
                if(orders[j].length() >= course[i]) DFS(j, course[i], orders[j], 0);
            }
        }
        for(int i=0; i<combi_orders.length; i++) {
            for(int j=0; j<combi_orders[i].length; j++) {
                if(combi_orders[i][j] != null) {
                    for(int k=0; k<combi_orders[i][j].size(); k++) {
                        String course_str = combi_orders[i][j].get(k);
                        if(course_map.get(course_str) == null) course_map.put(course_str, 1);
                        else course_map.put(course_str, course_map.get(course_str) + 1);
                        if(course_map.get(course_str) > max_orders[course_str.length()]) max_orders[course_str.length()] = course_map.get(course_str);
                    }
                }
            }
        }
        course_map.forEach((key, value) -> {
            if(value >= 2 && max_orders[key.length()] == value) course_list.add(key);
        });
        Collections.sort(course_list); //문자열 오름차순 정렬
        String[] answer = new String[course_list.size()];
        for(int i=0; i<course_list.size(); i++) answer[i] = course_list.get(i);
        return answer;
    }
    
    static void DFS(int orders_index, int select_number, String order, int index) {
        if(result.size() == select_number) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<result.size(); i++) sb.append(order.charAt(result.get(i)));
            if(combi_orders[orders_index][select_number] == null) combi_orders[orders_index][select_number] = new ArrayList<>();
            combi_orders[orders_index][select_number].add(sb.toString());
            return;
        }
        for(int i=index; i<order.length(); i++) {
            result.add(i);
            DFS(orders_index, select_number, order, i + 1);
            result.remove(result.size() - 1);
        }
    }
}