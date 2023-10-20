package com.andcoce.estrazionebase.business;

import com.andcoce.estrazionebase.bean.Matrix;

import java.util.ArrayList;

public class GaussianElimination {

    private Matrix matrix;

    public GaussianElimination(Matrix matrix){
        this.matrix = matrix;
    }

    public void echelonForm(){
        for(int col = 0; col < matrix.getN_VECTORS(); col++){
            checkFirstElement(col);
        }
    }

    private void checkFirstElement(int col){
        for(int row = 0; row < matrix.getVECTOR_DIM(); row++){
            if(matrix.getMatrix()[col][row] == 0 && previousAreZero(row, col)){
                swapRows(row, findRow(row, col));
            }
        }
    }

    private int findRow(int start, int col){
        for(int i = matrix.getVECTOR_DIM() -1; i >= start; i--){
            if(matrix.getMatrix()[col][i] != 0){
                return i;
            }
        }
        return matrix.getVECTOR_DIM() + 1;
    }

    private void swapRows(int row1, int row2){
        if(row2 != matrix.getVECTOR_DIM() + 1 && row1 < row2){
            int tmpRow[] = new int[matrix.getN_VECTORS()];
            for(int i = 0; i < matrix.getN_VECTORS(); i++){
                tmpRow[i] = matrix.getMatrix()[i][row1];
                matrix.getMatrix()[i][row1] = matrix.getMatrix()[i][row2];
                matrix.getMatrix()[i][row2] = tmpRow[i];
            }
        }
    }

    private boolean previousAreZero(int row, int col){
        boolean prev = true;
        for(int i = col; i >= 0; i--){
            if(matrix.getMatrix()[i][row] != 0){
                prev = false;
            }
        }
        return prev;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }
}
