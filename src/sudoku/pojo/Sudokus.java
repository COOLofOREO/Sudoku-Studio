package sudoku.pojo;

public class Sudokus {

    /**
     * 数独矩阵
     */
    public int[][] sudoku;
    /**
     * 用于记录当前数独每个空可填的数字
     */
    public int[][][] situation;

    public Sudokus(){}

    public Sudokus(int[][] sudoku){
        this.sudoku=new int[9][9];
        situation=new int[9][9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                situation[i][j]=new int[]{1,2,3,4,5,6,7,8,9};
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                setValue(i,j,sudoku[i][j]);
            }
        }
        update();
    }

    /**
     * 更新数独
     * 若数独中存在空格能填某个唯一的值，则填上
     * 递归调用自己，直到所有空格都不能被唯一确定
     */
    public void update(){
        boolean keep=false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(sudoku[i][j]!=0) continue;
                int n=0,value=0;
                for (int k = 0; k < 9; k++) {
                    if(situation[i][j][k]!=0){
                        n++;
                        value=situation[i][j][k];
                    }
                    if(n>=2) break;
                }
                if(n==1){
                    setValue(i,j,value);
                    keep=true;
                }
            }
        }
        if(keep) update();
    }

    /**
     * 在数独指定位置填写值，修改situation表
     * @param x
     * @param y
     * @param value
     */
    public void setValue(int x,int y,int value){
        if(value==0) return;
        sudoku[x][y]=value;
        situation[x][y]=new int[]{0,0,0,0,0,0,0,0,0};
        for (int i = 0; i < 9; i++) {
            situation[x][i][value-1]=0;
            situation[i][y][value-1]=0;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                situation[x/3*3+i][y/3*3+j][value-1]=0;
            }
        }
    }

    /**
     * 在指定位置设置值，修改situation表，并同时更新
     * @param x
     * @param y
     * @param value
     * @param udp
     */
    public void setValue(int x,int y,int value,boolean udp){
        setValue(x,y,value);
        if(udp) update();
    }

    public void print(){
        for (int i = 0; i < 9; i++) {
            if((i)%3==0) System.out.println("-------------------------------------");
            for (int j = 0; j < 9; j++) {
                if((j)%3==0) System.out.print("|  ");
                System.out.print(sudoku[i][j]+"  ");
            }
            System.out.println("|");
        }
        System.out.println("-------------------------------------");
    }
}
