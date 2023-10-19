package com.andcoce.estrazionebase.business;

import com.andcoce.estrazionebase.bean.Matrix;

public class GaussianElimination {

    private Matrix matrix;

    public GaussianElimination(Matrix matrix){
        this.matrix = matrix;
    }

    public void echelonForm(){
        for(int idx = 0; idx < matrix.getN_VECTORS(); idx++){
            checkFirstElement(idx);
        }
    }

    private void checkFirstElement(int col){
        for(int row = 0; row < matrix.getVECTOR_DIM(); row++){
            if(matrix.getMatrix()[col][row] != 0){
                int validRow = findRow(row+1, col);
                if(validRow == matrix.getVECTOR_DIM() + 1){
                    return;
                }else{
                    swapRows(validRow, row);
                }
            }
        }
    }

    private int findRow(int start, int col){
        for(int i = start; i < matrix.getVECTOR_DIM(); i++){
            if(matrix.getMatrix()[col][i] == 0){
                return i;
            }
        }
        return matrix.getVECTOR_DIM() + 1;
    }

    private void swapRows(int row1, int row2){
        int tmpRow[] = new int[matrix.getN_VECTORS()];
        for(int i = 0; i < matrix.getN_VECTORS(); i++){
            tmpRow[i] = matrix.getMatrix()[i][row1];
            matrix.getMatrix()[i][row1] = matrix.getMatrix()[i][row2];
            matrix.getMatrix()[i][row2] = tmpRow[i];
        }
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }
}
