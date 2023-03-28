import java.io.*;
import java.util.*;

class Heap {
    ArrayList<Integer> arr;
    Heap() {
        arr = new ArrayList<>();
        arr.add(-1);
    }
    
    public int get(int index) {
        return arr.get(index);
    }
    
    public int size() {
        return arr.size()-1;
    }
    
    public void insert(int value) {
        arr.add(value);
        int ch_ind = this.size();
        while(ch_ind != 1) {
            int pa_ind = ch_ind / 2;
            if(this.get(ch_ind) < this.get(pa_ind)) {
                int tmp = this.get(pa_ind);
                arr.set(pa_ind, value);
                arr.set(ch_ind, tmp);
                ch_ind = pa_ind;
            } else break;
        }
    }
    
    public int delete() {
        if(this.size()==0) return 0;
        int root_value = this.get(1);
        arr.set(1, this.get(this.size()));
        arr.remove(this.size());
        int pa_ind = 1;
        while(pa_ind * 2 <= this.size()) {
            int ch_ind = pa_ind * 2;
            if((pa_ind * 2 + 1 <= this.size()) && this.get(ch_ind) > this.get(ch_ind + 1)) {
                //오른쪽 자식이 존재하면서, 우선순위가 오른쪽 자식이 더 높다면
                ch_ind += 1;
            }
            //자식 노드가 우선 순위가 더 높다면,
            if(this.get(pa_ind) > this.get(ch_ind)) {
                int tmp = this.get(pa_ind);
                arr.set(pa_ind, this.get(ch_ind));
                arr.set(ch_ind, tmp);
                pa_ind = ch_ind;
            } else break;
        }
        return root_value;
    }
}

public class Main {
    static int N;
    static Heap heap = new Heap();
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            int op = Integer.parseInt(br.readLine());
            if(op == 0) {
                sb.append(heap.delete() + "\n");
            } else {
                heap.insert(op);
            }
        }
        System.out.println(sb.toString().trim());
    }
}