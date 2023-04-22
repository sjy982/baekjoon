import java.util.*;
class Plan {
    String s;
    int st, lt;
    Plan(String s, int st, int lt) {
        this.s = s;
        this.st = st;
        this.lt = lt;
    }
}
class Solution {
    static Plan[] ob_plans;
    static Stack<Plan> wait_stack = new Stack<>();
    static ArrayList<String> end_list = new ArrayList<>();
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        ob_plans = new Plan[plans.length];
        for(int i=0; i<plans.length; i++) {
            StringTokenizer st = new StringTokenizer(plans[i][1], ":");
            int min = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
            ob_plans[i] = new Plan(plans[i][0], min, Integer.parseInt(plans[i][2]));
        }
        Arrays.sort(ob_plans, (o1, o2) -> {
           return o1.st - o2.st; 
        });
        
        for(int i=0; i<ob_plans.length; i++) {
            if(i == ob_plans.length - 1) {
                end_list.add(ob_plans[i].s);
                while(wait_stack.size()!=0) {
                    end_list.add(wait_stack.pop().s);
                }
            } else {
                int left = ob_plans[i+1].st - (ob_plans[i].st + ob_plans[i].lt);
                if(left >= 0) {
                    end_list.add(ob_plans[i].s);
                    while(wait_stack.size()!=0 && left != 0) {
                        Plan top = wait_stack.pop();
                        if(top.lt<=left) {
                            end_list.add(top.s);
                            left -= top.lt;
                        } else {
                            top.lt -= left;
                            left = 0;
                            wait_stack.push(top);
                        }
                    }
                } else wait_stack.push(new Plan(ob_plans[i].s, ob_plans[i].st, Math.abs(left)));
            }
        }
        for(int i=0; i<end_list.size(); i++) answer[i] = end_list.get(i);
        return answer;
    }
}