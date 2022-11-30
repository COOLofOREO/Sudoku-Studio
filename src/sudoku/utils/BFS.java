package sudoku.utils;

import sudoku.pojo.Sudokus;

import java.util.*;

public class BFS {

    private List<Node> queue=new ArrayList<>();
    //记录递归次数
    public static int count;

    private static class Node{
        Sudokus sudokus;
        boolean enable;
        //数独中剩余未填空的个数
        int remain;

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
            int[][] sudoku= sudokus.sudoku;
            if(SudokuUtil.hasProblem(sudokus)) enable=false;
            int n=0;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if(sudoku[i][j]==0) n++;
                }
            }
            remain=n;
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
        BFS b=new BFS();
        Node node=b.init(sudokus);
        return node.sudokus;
    }

    private Node init(Sudokus sudokus){
        Node root=new Node();
        root.sudokus=sudokus;
        queue=root.makeChildren();
//        sort();
        return bfs();
    }

    private Node bfs(){
        count++;
        int n=firstIndex();
        Node first=queue.get(n);
        if(!first.enable){
            queue.remove(n);
            if(queue.size()==0) return null;
            return bfs();
        }
        if(SudokuUtil.isFinish(first.sudokus.sudoku)) return first;
        else {
            List<Node> children=first.makeChildren();
            queue.remove(n);
            for (int i = 0; i < children.size(); i++) {
                queue.add(children.get(i));
            }
            return bfs();
        }
    }

    private void sort(){
        queue.sort(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.remain-o2.remain;
            }
        });
    }

    private int firstIndex(){
        int remain=queue.get(0).remain;
        int n=0;
        for (int i = 1; i < queue.size(); i++) {
            if(queue.get(i).remain<remain){
                remain=queue.get(i).remain;
                n=i;
            }
        }
        return n;
    }

}
