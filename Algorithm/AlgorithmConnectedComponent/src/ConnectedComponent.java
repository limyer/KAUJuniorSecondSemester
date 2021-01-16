// 2018125084 임예랑
// 2020-2 알고리즘 해석 및 설계 이인복 교수님
// 201129 13주차 프로그래밍 과제 3
// ConnectedComponent.java

import java.util.ArrayList;
import java.util.Scanner;
    
public class ConnectedComponent {
    
    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    static boolean visited[];
    static int count = 0;

    public static void main(String args[]){
    	Scanner input = new Scanner(System.in);

        int N = input.nextInt();
        int M = input.nextInt();

        for(int i = 0; i<=N; i++){
            map.add(new ArrayList<>());
        }

        visited = new boolean[N+1];

        for(int i = 0; i<M; i++){
            int x = input.nextInt();
            int y = input.nextInt();
            map.get(x).add(y);
            map.get(y).add(x);
        }

        for(int i = 1; i <= N; i++){
            if(!visited[i]){
                dfs(i);
                count ++;
            }
        }

        System.out.println(count);
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
}