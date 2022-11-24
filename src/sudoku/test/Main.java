package sudoku.test;

import sudoku.pojo.Sudokus;
import sudoku.utils.BFS;
import sudoku.utils.DFS;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        compareTest(Sample.difficult4);
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
        return sudoku;
    }

    public static void compareTest(int[][] target){
        Sudokus sudokus=new Sudokus(target);
        long a=System.currentTimeMillis();
        Sudokus bfs=BFS.solve(sudokus);
        System.out.println("BFS:");
        bfs.print();
        long b=System.currentTimeMillis();
        Sudokus dfs=DFS.solve(sudokus);
        System.out.println("DFS:");
        dfs.print();
        long c=System.currentTimeMillis();
        System.out.println("BFS uses "+(b-a)+"ms and recurs "+BFS.count+" times");
        System.out.println("DFS uses "+(c-b)+"ms and recurs "+DFS.count+" times");
    }
}
