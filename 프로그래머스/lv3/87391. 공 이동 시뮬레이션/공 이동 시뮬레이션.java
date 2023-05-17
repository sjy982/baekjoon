class Node {
    Range x, y;
    Node(Range x, Range y) {
        this.x = x;
        this.y = y;
    }
}
class Range {
    long min, max;
    Range(long min, long max) {
        this.min = min;
        this.max = max;
    }
}
class Solution {
    //0: <-, 1: ->, 2: ^, 3: v
    static Node range;
    static int w, h;
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long answer = -1;
        w = m;
        h = n;
        range = new Node(new Range(y, y), new Range(x, x));
        for(int i=queries.length-1; i>=0; i--) if(!query(queries[i][0], queries[i][1])) return 0;
        answer = (range.x.max - range.x.min + 1) * (range.y.max - range.y.min + 1);
        return answer;
    }
    static boolean query(int dir, int d) {
        if(dir == 0) {
            // >
            if(range.x.min != 0) range.x.min += d;
            range.x.max = (range.x.max + d) > w-1 ? w-1 : range.x.max + d;
            if(range.x.min > w-1) return false;
        } else if(dir == 1) {
            // <
            if(range.x.max != w-1) range.x.max -= d;
            range.x.min = (range.x.min - d) < 0 ? 0 : range.x.min - d;
            if(range.x.max < 0) return false;
        } else if(dir == 2) {
            // v
            if(range.y.min != 0) range.y.min += d;
            range.y.max = (range.y.max + d) > h-1 ? h-1 : range.y.max + d;
            if(range.y.min > h-1) return false;
        } else if(dir == 3) {
            // ^
            if(range.y.max != h-1) range.y.max -= d;
            range.y.min = (range.y.min - d) < 0 ? 0 : range.y.min - d;
            if(range.y.max < 0) return false;
        }
        return true;
    }
}