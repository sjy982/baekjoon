import java.io.*;
import java.util.*;

class Candidate implements Comparable<Candidate> {
    int num, rc;
    Candidate(int num, int rc) {
        this.num = num;
        this.rc = rc;
    }
    
    @Override
    public int compareTo(Candidate o) {
        if(this.num < o.num) {
            return -1;
        } else if(this.num > o.num) {
            return 1;
        }
        return 0;
    }
}

public class Main {
    static int N, C;
    static ArrayList<Candidate> frame = new ArrayList<>();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        C = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<C; i++) {
            int rCandidate = Integer.parseInt(st.nextToken());
            int ind = findCandidateInd(rCandidate);
            if(ind == -1) {
                if(frame.size() < N) {
                    frame.add(new Candidate(rCandidate, 1));
                } else {
                    int minInd = findSmallestCandidateInd();
                    frame.remove(minInd);
                    frame.add(new Candidate(rCandidate, 1));
                }
            } else {
                frame.get(ind).rc += 1;
            }
        }
        Collections.sort(frame);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<frame.size(); i++) {
            sb.append(frame.get(i).num).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
    
    static int findCandidateInd(int rCandidate) {
        for(int i=0; i<frame.size(); i++) {
            if(frame.get(i).num == rCandidate) {
                return i;
            }
        }
        return -1;
    }
    
    static int findSmallestCandidateInd() {
        int min = 1001;
        int minInd = -1;
        for(int i=0; i<frame.size(); i++) {
            if(frame.get(i).rc < min) {
                min = frame.get(i).rc;
                minInd = i;
            }
        }
        return minInd;
    }
}