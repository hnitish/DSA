class Solution {
    public void solveSudoku(char[][] board) {
        helper(board, 0, 0);
    }

    private static boolean helper(char[][] board, int row, int col) {
        //base condition if we reach end of row then sudoko is solved
        //board[8][8] -> new row will be end which is length of board
        if (row == board.length) {
            return true;
        }

        int nrow = 0;
        int ncol = 0;

        if (col == board.length-1) { // if we reached end of column then shift to next row and start from first column
            nrow = row+1;
            ncol = 0;
        } else { // traverse each column till end 
            nrow = row ;
            ncol = col+1;
        }

        if(board[row][col]!='.'){ // it means we have a number here so don't do anything and go for next col/row
            if(helper(board,nrow,ncol)){
                return true;
            }//if this is true it means sudoko is solved

        }else{ //else place a number for possible solution
            for (int i=1;i<=9;i++){
                if(isValid(board,row,col,i)){
                    board[row][col]=(char)(i+'0');
                    if(helper(board,nrow,ncol)){ // check if board is solved, if solved then it will be true
                        return true;
                    }else { //else do the backtrack
                        board[row][col]='.';
                    }
                }
            }
        }
        return false;
    }
    public static boolean isValid(char[][] board,int row, int col, int number){
        //check that this number does not exist across whole row or column
        for (int i=0;i<board.length;i++){
            if(board[row][i]==((char)number+'0')){// check across whole row
                return false;
            }
            if(board[i][col]==((char)number+'0')){// check across whole col
                return false;
            }
        }
        //check that this number does not exist in the local sudoko grid
        //get start of grid
        int sr=(row/3)*3;
        int sc=(col/3)*3;
        //check in whole matrix if this number exist
        for(int i=sr;i<sr+3;i++){
            for(int j=sc;j<sc+3;j++){
                if(board[i][j]==((char)number+'0')){
                    return false;
                }
            }
        }
        return true;
    }
}
