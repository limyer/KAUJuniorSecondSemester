// 2018125084 임예랑
// 2020-2 알고리즘 해석 및 설계 이인복 교수님
// 201205 14주차 프로그래밍 과제 4
// FairNode.java

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
    
public class FairNode {
    
    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    static Queue<Integer> Q;
    static List<Integer> depthMapA = new ArrayList<Integer>(); 
    static List<Integer> depthMapB = new ArrayList<Integer>();
    static int depth;
    static boolean visited[];
    static int count = 0;

    public static void main(String args[]){
    	Scanner input = new Scanner(System.in);

        int N = input.nextInt();
        int M = input.nextInt();
        int A = input.nextInt();
        int B = input.nextInt();
        
        for(int i = 0; i<=N; i++){
            map.add(new ArrayList<>());
            depthMapA.add(0);
            depthMapB.add(0);
        }



        for(int i = 0; i<M; i++){
            int x = input.nextInt();
            int y = input.nextInt();
            map.get(x).add(y);
            map.get(y).add(x);
        }
        

        visited = new boolean[N+1];

        for(int i = 1; i <= N; i++){
            if(!visited[i]){
                dfs(i); 
                count++;

            }
        }
        
        if (count > 1) {
        	System.out.println(0);
        	input.close();
        	return;
        }
        
        Q = new LinkedList<Integer>();
        
        visited = new boolean[N+1];
        bfs(A, 'A');
        
        visited = new boolean[N+1];
        bfs(B, 'B');
        

        System.out.println(checkFairNode());
        input.close();

    }

    public static void dfs(int v){
        visited[v] = true;

        for(int value : map.get(v)){
            if(!visited[value]){
                dfs(value);
            }
        }
    }
    
    public static void bfs(int v, char name){
    	int depth = 0;
    	Q.add(v);
        visited[v] = true;
        
        while(!Q.isEmpty()) {
        	int q = Q.poll();
	    	if (name == 'A') {
	    		depth = depthMapA.get(q) + 1;
	    	}
	    	else if (name == 'B'){
	    		depth = depthMapB.get(q) + 1;
	    	}
        	for(int i: map.get(q)) {
        		if(!visited[i]) {
        			Q.add(i);
        			visited[i] = true;
        	    	if (name == 'A') {
        	    		depthMapA.set(i, depth);
        	    	}
        	    	else if (name == 'B'){
        	    		depthMapB.set(i, depth);
        	    	}
        		}
        	}
        	
        }
    }
    
    public static int checkFairNode() {
    	int fairNode = -1;
    	int diff = Integer.MAX_VALUE;
    	int depthA = 0, depthB = 0;
    	
    	for(int i = 1; i < depthMapA.size(); i++) {
    		depthA = depthMapA.get(i);
    		depthB = depthMapB.get(i);
    		int checkDiff = Math.abs(depthA-depthB);
    		if (diff > checkDiff) {
    			fairNode = i;
    			diff = checkDiff;
    		}
    	}
    	
    	return fairNode;
    }
    
    

}