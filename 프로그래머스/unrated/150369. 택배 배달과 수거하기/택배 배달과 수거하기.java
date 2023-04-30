import java.util.*;
class Node {
    int h, c;
    Node(int h, int c) {
        this.h = h;
        this.c = c;
    }
}
class Solution {
    static ArrayList < Node > d_list = new ArrayList < > ();
    static ArrayList < Node > p_list = new ArrayList < > ();
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        for (int i = 0; i < n; i++) {
            if (deliveries[i] != 0) d_list.add(new Node(i + 1, deliveries[i]));
            if (pickups[i] != 0) p_list.add(new Node(i + 1, pickups[i]));
        }
        while (d_list.size() != 0 || p_list.size() != 0) {
            int house = 0;
            int box = 0;
            int cout = 1; //이동 횟수
            if (d_list.size() == 0) {
                house = p_list.get(p_list.size()-1).h;
                box = p_list.get(p_list.size()-1).c;
            } else if (p_list.size() == 0) {
                house = d_list.get(d_list.size()-1).h;
                box = d_list.get(d_list.size()-1).c;
            } else {
                if (d_list.get(d_list.size() - 1).h == p_list.get(p_list.size() - 1).h) {
                    house = d_list.get(d_list.size() - 1).h;
                    box = d_list.get(d_list.size() - 1).c >= p_list.get(p_list.size() - 1).c ? d_list.get(d_list.size() - 1).c : p_list.get(p_list.size() - 1).c;
                } else if (d_list.get(d_list.size() - 1).h > p_list.get(p_list.size() - 1).h) {
                    house = d_list.get(d_list.size() - 1).h;
                    box = d_list.get(d_list.size() - 1).c;
                } else {
                    house = p_list.get(p_list.size() - 1).h;
                    box = p_list.get(p_list.size() - 1).c;
                }
            }
            while (cap * cout < box) cout += 1;
            delivery_pickup(d_list, cap * cout);
            delivery_pickup(p_list, cap * cout);
            answer += (house * cout) * 2;
        }
        return answer;
    }
    static void delivery_pickup(ArrayList < Node > list, int box) {
        while (box != 0 && list.size() != 0) {
            if (list.get(list.size() - 1).c > box) {
                list.get(list.size() - 1).c -= box;
                box = 0;
            } else {
                box -= list.get(list.size() - 1).c;
                list.remove(list.size() - 1);
            }
        }
    }
}