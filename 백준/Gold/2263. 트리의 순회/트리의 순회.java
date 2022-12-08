import java.io.*;
import java.util.*;

class Tree {
    int v,ind;
    Tree left,right;
    Tree(int v, int ind) {
        this.v = v;
        this.ind = ind;
    }
}

public class Main {
    static int N;
    static int post[], in[], in_ind[];
    static Tree tree;
    static boolean visited[];
    static int pi_loca;
    static StringBuilder ans = new StringBuilder();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        post = new int[N];
        in = new int[N];
        in_ind = new int[N+1];
        visited = new boolean[N];
        pi_loca = N-1;
        for(int i=0; i<2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            if(i==0) {
                for(int j=0; j<N; j++) {
                    int v = Integer.parseInt(st.nextToken());
                    in[j] = v;
                    in_ind[v] = j;
                }
            } else if(i==1) {
                for(int j=0; j<N; j++) {
                    int v = Integer.parseInt(st.nextToken());
                    post[j] = v;
                }
            }
        }
        tree = new Tree(post[pi_loca], in_ind[post[pi_loca]]);
        visited[in_ind[post[pi_loca]]] = true;
        pi_loca -= 1;
        make_tree(tree);
        preorder(tree);
        System.out.println(ans.toString());
    }
    
    static void make_tree(Tree node) {
        if(node.ind != N-1) {
            if(!visited[node.ind+1]) {
                visited[in_ind[post[pi_loca]]] = true;
                node.right = new Tree(post[pi_loca], in_ind[post[pi_loca]]);
                pi_loca -= 1;
                make_tree(node.right);
            }
        }
        if(node.ind != 0) {
           if(!visited[node.ind-1]) {
               visited[in_ind[post[pi_loca]]] = true;
               node.left = new Tree(post[pi_loca], in_ind[post[pi_loca]]);
               pi_loca -= 1;
               make_tree(node.left);
           }
        }
    }
    
    static void preorder(Tree node) {
        if(node == null) return;
        ans.append(node.v + " ");
        preorder(node.left);
        preorder(node.right);
    }
}