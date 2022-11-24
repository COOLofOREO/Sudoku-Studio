package sudoku.utils;

import sudoku.pojo.Sudokus;

import java.util.HashSet;
import java.util.Set;

public class SudokuUtil {

    public static boolean hasProblem(Sudokus sudokus){
        if(hasProblem(sudokus.sudoku)) return true;
        int[][] sudoku= sudokus.sudoku;
        int[][][] situation= sudokus.situation;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(sudoku[i][j]!=0) continue;
                int n=0;
                for (int k = 0; k < 9; k++) {
                    if(situation[i][j][k]!=0) n++;
                }
                if(n==0) return true;
            }
        }
        return false;
    }

    private static boolean hasProblem(int[][] sudoku){
        for (int i = 0; i < 9; i++) {
            Set<Integer> a=new HashSet<>();
            Set<Integer> b=new HashSet<>();
            int x=0,y=0;
            for (int j = 0; j < 9; j++) {
                if(sudoku[i][j]!=0){
                    a.add(sudoku[i][j]);
                    x++;
                }
                if(sudoku[j][i]!=0){
                    b.add(sudoku[j][i]);
                    y++;
                }
            }
            if(x!=a.size()||y!=b.size()) return true;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Set<Integer> c=new HashSet<>();
                int z=0;
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        if(sudoku[i*3+k][j*3+l]!=0){
                            c.add(sudoku[i*3+k][j*3+l]);
                            z++;
                        }
                    }
                }
                if(z!=c.size()) return true;
            }
        }

        return false;
    }

    public static boolean isFinish(int[][] sudoku){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(sudoku[i][j]==0) return false;
            }
        }
        return true;
    }

    public static int[][] clone(int[][] num){
        int a=num.length;
        int b=num[0].length;
        int[][] res=new int[a][b];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                res[i][j]=num[i][j];
            }
        }
        return res;
    }

    public static int[][][] clone(int[][][] num){
        int x=num.length;
        int y=num[0].length;
        int z=num[0][0].length;
        int[][][] res=new int[x][y][z];
        for (int i = 0; i < x; i++) {
            res[i]=clone(num[i]);
        }
        return res;
    }

    public static Sudokus clone(Sudokus sudokus){
        Sudokus sud=new Sudokus();
        sud.sudoku=clone(sudokus.sudoku);
        sud.situation=clone(sudokus.situation);
        return sud;
    }
}
