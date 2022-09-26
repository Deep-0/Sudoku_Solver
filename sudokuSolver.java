package other;

public class sudokuSolver {
    public static void main(String[] args) {
        int[][] board = new int[][]{
                {3,0,6,5,0,8,4,0,0},
                {5,2,0,0,0,0,0,0,0},
                {0,8,7,0,0,0,0,3,1},
                {0,0,3,0,1,0,0,8,0},
                {9,0,0,8,6,3,0,0,5},
                {0,5,0,0,9,0,6,0,0},
                {1,3,0,0,0,0,2,5,0},
                {0,0,0,0,0,0,0,7,4},
                {0,0,5,2,0,6,3,0,0}
        };
        if(solve(board)){
            display(board);
        }
        else {
            System.out.println("cant solved");
        }
    }

    static boolean solve(int[][] board){
        int n = board.length;
        int row = -1;
        int col = -1;
        boolean emptyLeft = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j]==0){
                    row = i;
                    col = j;
                    emptyLeft = false;
                    break;
                }
            }
            if (emptyLeft==false){
                break;
            }
        }
        if (emptyLeft==true){
            return true;
            // sudoku solved
        }
        
        //backtrack
        for (int number = 1; number <= 9; number++) {
            if(isSafe(board,row,col,number)){
                board[row][col] = number;
                if(solve(board)){
                    return true;
                }
                else {
                    //backtrack
                    board[row][col] = 0;
                }
            }
        }
        return false;
    }

    private static void display(int[][] board) {
        System.out.println("-----------------------");
        for(int i=0;i< board.length;i++){
            for (int j=0;j< board[i].length;j++){
                System.out.print(board[i][j] + " ");
                if (j==2 || j==5 || j==8)
                    System.out.print("| ");
            }
            System.out.println();
            if (i==2 || i==5 || i==8){
                System.out.println("-----------------------");
            }
        }
    }


    static boolean isSafe(int[][] board,int row,int col,int num){
        // check row
        for (int i = 0; i < board.length; i++) {
            if(board[i][col]==num){
                return false;
            }
        }

        // check col
        for (int i = 0; i < board.length; i++) {
            if(board[row][i]==num){
                return false;
            }
        }

        int sqrt = (int)Math.sqrt(board.length);
        int rowStart = row - (row%sqrt);
        int colStart = col - (col%sqrt);

        for(int r=rowStart;r<rowStart+sqrt;r++){
            for(int c=colStart;c<colStart+sqrt;c++){
                if(board[r][c] == num){
                    return false;
                }
            }
        }
        return true;
    }
}
