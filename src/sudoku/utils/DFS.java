package sudoku.utils;

import sudoku.pojo.Sudokus;

import java.util.ArrayList;
import java.util.List;

public class DFS {

    private List<Node> stack=new ArrayList<>();
    //记录递归次数
    public static int count;

    private static class Node{
        Sudokus sudokus;
        boolean enable;

        public Node(){
            this.enable=true;
        }

        public Node(Sudokus sudokus,int x,int y,int value){
            this.sudokus=sudokus;
            this.enable=true;
            setValue(x,y,value);
        }

        private void setValue(int x,int y,int value){
            sudokus.setValue(x,y,value,true);
            if(SudokuUtil.hasProblem(sudokus)) enable=false;
        }

        public List<Node> makeChildren(){
            List<Node> children=new ArrayList<>();
            int[][] sudoku= sudokus.sudoku;
            int[][][] situation= sudokus.situation;
            int x=0,y=0;
            boolean flag=false;
            for(int i=0;i<9;i++){
                for (int j=0;j<9;j++){
                    if(sudoku[i][j]==0){
                        x=i;
                        y=j;
                        flag=true;
                        break;
                    }
                }
                if(flag) break;
            }
            if(!flag) return null;
            for (int k = 0; k < 9; k++) {
                if(situation[x][y][k]!=0){
                    Node child=new Node(SudokuUtil.clone(sudokus),x,y,k+1);
                    children.add(child);
                }
            }
            return children;
        }
    }

    public static Sudokus solve(Sudokus sudokus){
        sudokus.update();
        if(SudokuUtil.isFinish(sudokus.sudoku)) return sudokus;
        DFS b=new DFS();
        Node node=b.init(sudokus);
        if(node==null) return sudokus;
        return node.sudokus;
    }

    private Node init(Sudokus sudokus){
        Node root=new Node();
        root.sudokus=sudokus;
        stack=root.makeChildren();
        return dfs();
    }

    private Node dfs(){
        count++;
        int len=stack.size();
        Node node=stack.get(len-1);
        if(!node.enable){
            stack.remove(len-1);
            return dfs();
        }
        if(SudokuUtil.isFinish(node.sudokus.sudoku)) return node;
        else {
            List<Node> children=node.makeChildren();
            stack.remove(len-1);
            for (int i = children.size()-1; i >=0 ; i--) {
                stack.add(children.get(i));
            }
            return dfs();
        }
    }
}
