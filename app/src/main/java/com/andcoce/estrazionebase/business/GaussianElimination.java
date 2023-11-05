package com.andcoce.estrazionebase.business;

import android.util.Log;

import com.andcoce.estrazionebase.bean.Matrix;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class GaussianElimination {

    private Matrix matrix;

    public GaussianElimination(Matrix matrix){
        this.matrix = matrix;
    }

    public String echelonForm(){
        for(int col = 0; col < matrix.getN_VECTORS(); col++){
            rearrange(col); //metodo che riordina le righe in base agli zeri prima di ogni operazione
            rowOperations(col); //metodo che somma ad una riga la riga contenente l'ultimo pivot moltiplicata per una costante
        }

        return buildResults(getPivotColumns());
    }

    private void rearrange(int col){ //col = colonna di riferimento
        for(int row = 0; row < matrix.getVECTOR_DIM(); row++){ //partendo dall'alto, controlla ogni riga
            if(matrix.getMatrix()[col][row] == 0 && previousAreZero(row, col)){ //se l'elemento alla colonna "col", riga "row" è zero e i valori precedenti della riga sono 0
                swapRows(row, findRow(row, col)); //prendi la prima riga dove alla colonna "col" il valore non è zero e scambiala con la riga dell'iterazione corrente
            }
        }
    }

    private void rowOperations(int col){
        int pivotRow = findPreviousPivot(col); //trova l'elemento pivot presente sulla riga precedente, sulla base del quale verrà creato un coefficiente per la riga da sommare
        for(int row = pivotRow + 1; row < matrix.getVECTOR_DIM(); row++){ //lascio invariata la riga utilizzata per le operazioni
            if(matrix.getMatrix()[col][row] != 0) {
                double a = matrix.getMatrix()[col][pivotRow]; //elemento pivot
                double b = matrix.getMatrix()[col][row]; //elemento sottostante nella riga presa in esame
                double div = b/a; //coefficiente che, moltiplicato per il pivot e sommato all'elemento preso in esame, annulla quest'ultimo
                addFirstRowComb(row, col, div, pivotRow); //Esegue la somma
            }
        }
    }

    private void addFirstRowComb(int target, int start, double div, int pivotRow){
        for(int i = start; i < matrix.getN_VECTORS(); i++){
            double r = matrix.getMatrix()[i][target] - div*matrix.getMatrix()[i][pivotRow];
            matrix.getMatrix()[i][target] = new BigDecimal(r).setScale(1, RoundingMode.HALF_UP).doubleValue();
        }
    }

    private int findPreviousPivot(int col){
        for(int i = 0; i < matrix.getVECTOR_DIM(); i++){
            if(previousAreZero(i, col) && matrix.getMatrix()[col][i] != 0){
                return i;
            }
        }

        return matrix.getVECTOR_DIM();
    }

    private int findRow(int start, int col){ //funzione che cerca una riga (partendo dal basso) dove l'elemento alla colonna "col" è diverso da zero
        for(int i = matrix.getVECTOR_DIM() -1; i >= start; i--){
            if(matrix.getMatrix()[col][i] != 0){ //se l'elemento non è zero
                return i; //ritorna la riga
            }
        }
        return matrix.getVECTOR_DIM() + 1; //se non ci sono elementi adatti, ritorna un numero che non corrisponde a nessuna riga
    }

    private void swapRows(int row1, int row2){
        if(row2 != matrix.getVECTOR_DIM() + 1 && row1 < row2){ //se la riga da sommare è stata trovata ed è più in basso della riga presa in analisi
            double tmpRow[] = new double[matrix.getN_VECTORS()]; //vettore temporaneo
            for(int i = 0; i < matrix.getN_VECTORS(); i++){ //scorre tutti i valori delle righe e li scambia
                tmpRow[i] = matrix.getMatrix()[i][row1];
                matrix.getMatrix()[i][row1] = matrix.getMatrix()[i][row2];
                matrix.getMatrix()[i][row2] = tmpRow[i];
            }
        }
    }

    private boolean previousAreZero(int row, int col){ //verifica se, dato un elemento, tutti i precedenti ad esso presenti sulla stessa riga sono nulli (necessario per i pivot)
        boolean prev = true; //di base è vero
        for(int i = col - 1; i >= 0; i--){
            if(matrix.getMatrix()[i][row] != 0){ //se trova un valore diverso da zero, la condizione non è soddisfatta
                prev = false;
            }
        }
        return prev;
    }

    private ArrayList<Integer> getPivotColumns(){
        ArrayList<Integer> cols = new ArrayList<>();

        for(int i = 0; i < matrix.getN_VECTORS(); i++){
            boolean pivot = false;
            for(int j = 0; j < matrix.getVECTOR_DIM(); j++){
                if(previousAreZero(j, i) && matrix.getMatrix()[i][j] != 0){
                    pivot = true;
                }
            }

            if(pivot){
                cols.add(i);
            }
        }

        return cols;
    }

    private String buildResults(ArrayList<Integer> pivots){
        String output1 = "Si ottiene che B = {";
        String gen = "";
        for(int i = 0; i < pivots.size(); i++){
            gen = gen + "v" + (pivots.get(i) + 1);
            if(i != pivots.size() - 1){
                gen = gen + ", ";
            }
        }

        output1 = output1 + gen + "} è una base per R⁵.\n";

        String output2 = "\nInoltre,\nSpan(" + gen + ")\nè uguale a\nSpan(v1, …, v10).\n";

        String majorInfo = "\nÈ possibile visualizzare la matrice portata a scala ed i relativi pivot, cliccando sul pulsante \"Visualizza Matrice\".";


        return output1 + output2 + majorInfo;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }
}
