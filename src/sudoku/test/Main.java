package sudoku.test;

import sudoku.pojo.Sudokus;
import sudoku.utils.BFS;
import sudoku.utils.DFS;

import java.util.Scanner;
/*
  程序入口
  author: 张奥
*/
public class Main {

    public static void main(String[] args) {
        //compareTest(Sample.difficult4);
        long[] ans;
        int[][] target = scan();
        if (args.length == 0) {
            compareTest(target); 
        } else if (args[0].equals("dfs")) {
            ans = dfsTest(target);
            System.out.println("BFS uses "+(ans[0])+"ms and recurs "+ans[1]+" times");
        } else if (args[0].equals("bfs")) {
            ans = bfsTest(target);
            System.out.println("BFS uses "+(ans[0])+"ms and recurs "+ans[1]+" times");
        } else {
            throw new Error("mode must be dfs or bfs");
        }
    }

    public static int[][] scan(){
        int[][] sudoku=new int[9][9];
        Scanner scanner=new Scanner(System.in);
        System.out.println("请逐行输入，数字间不要有间隔，空格用0表示");
        for (int i = 0; i < 9; i++) {
            String line=scanner.nextLine();
            char[] chars=line.toCharArray();
            for (int j = 0; j < 9; j++) {
                sudoku[i][j]=Integer.parseInt(Character.toString(chars[j]));
            }
        }
        scanner.close(); // 关闭scanner
        return sudoku;
    }

    public static long[] bfsTest(int[][] target) {
        Sudokus sudokus=new Sudokus(target);
        long a=System.currentTimeMillis();
        Sudokus bfs=BFS.solve(sudokus);
        System.out.println("BFS:");
        bfs.print();
        long b=System.currentTimeMillis();
        long[] ans = {b-a, BFS.count};
        return ans;
    }

    public static long[] dfsTest(int[][] target) {
        Sudokus sudokus=new Sudokus(target);
        long b=System.currentTimeMillis();
        Sudokus dfs=DFS.solve(sudokus);
        System.out.println("DFS:");
        dfs.print();
        long c=System.currentTimeMillis();
        long[] ans = {c-b, DFS.count};
        return ans;
    }

    public static void compareTest(int[][] target){
        long[] DFSans = dfsTest(target);
        long[] BFSans = bfsTest(target);
        System.out.println("BFS uses "+(BFSans[0])+"ms and recurs "+BFSans[1]+" times");
        System.out.println("DFS uses "+(DFSans[0])+"ms and recurs "+DFSans[1]+" times");
    }
}
