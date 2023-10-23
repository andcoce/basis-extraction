package com.andcoce.estrazionebase.business;

import android.icu.text.DecimalFormat;
import android.util.Log;

import com.andcoce.estrazionebase.bean.Matrix;

public class GaussianElimination {

    private Matrix matrix;

    private DecimalFormat decfor;

    public GaussianElimination(Matrix matrix){
        this.matrix = matrix;
        decfor = new DecimalFormat("0.0");
    }

    public void echelonForm(){
        for(int col = 0; col < matrix.getN_VECTORS(); col++){
            if(col != 0){
                System.out.println();
            }
            rearrange(col); //metodo che riordina le righe in base agli zeri prima di ogni operazione
            rowOperations(col);
        }
    }

    private void rearrange(int col){
        for(int row = 0; row < matrix.getVECTOR_DIM(); row++){
            if(matrix.getMatrix()[col][row] == 0 && previousAreZero(row, col)){
                swapRows(row, findRow(row, col));
            }
        }
    }

    private void rowOperations(int col){
        int pivotRow = findPreviousPivot(col);
        for(int row = pivotRow + 1; row < matrix.getVECTOR_DIM(); row++){ //lascio invariata la riga utilizzata per le operazioni
            if(matrix.getMatrix()[col][row] != 0) {
                double a = matrix.getMatrix()[col][pivotRow];
                double b = matrix.getMatrix()[col][row];
                double div = b/a;
                addFirstRowComb(row, col, div, pivotRow);
            }
        }
    }

    private void addFirstRowComb(int target, int start, double div, int pivotRow){
        for(int i = start; i < matrix.getN_VECTORS(); i++){
            double r = matrix.getMatrix()[i][target] - div*matrix.getMatrix()[i][pivotRow];
            matrix.getMatrix()[i][target] = Double.parseDouble(decfor.format(r));
        }
    }

    private int findPreviousPivot(int col){
        for(int i = 0; i < matrix.getVECTOR_DIM(); i++){
            if(previousAreZero(i, col) && matrix.getMatrix()[i][col] != 0){
                return i;
            }
        }

        return matrix.getVECTOR_DIM();
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
            double tmpRow[] = new double[matrix.getN_VECTORS()];
            for(int i = 0; i < matrix.getN_VECTORS(); i++){
                tmpRow[i] = matrix.getMatrix()[i][row1];
                matrix.getMatrix()[i][row1] = matrix.getMatrix()[i][row2];
                matrix.getMatrix()[i][row2] = tmpRow[i];
            }
        }
    }

    private boolean previousAreZero(int row, int col){
        boolean prev = true;
        for(int i = col - 1; i >= 0; i--){
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
