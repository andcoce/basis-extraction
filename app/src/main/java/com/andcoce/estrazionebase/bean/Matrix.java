package com.andcoce.estrazionebase.bean;

import java.util.Random;

public class Matrix {

    private final int VECTOR_DIM = 5;
    private final int N_VECTORS = 10;
    private String matrix[][];
    private Random rand;

    public Matrix(){
        matrix = new String[N_VECTORS][VECTOR_DIM];
        rand = new Random();
    }

    public void init(){
        for(int i = 0; i < N_VECTORS; i++){
            for(int j = 0; j < VECTOR_DIM; j++){
                matrix[i][j] = Integer.toString(rand.nextInt(10));
            }
        }
        /*
        matrix[0][0] = "0";
        matrix[1][0] = "0";
        matrix[0][2] = "0";
        matrix[0][4] = "0";
        matrix[1][4] = "0";
        matrix[2][4] = "0";
        */
    }

    public int getVECTOR_DIM() {
        return VECTOR_DIM;
    }

    public int getN_VECTORS() {
        return N_VECTORS;
    }

    public String[][] getMatrix() {
        return matrix;
    }

    public String toString(){
        String output = "Sono stati generati i seguenti vettori:\n";

        for(int i = 0; i < N_VECTORS; i++){
            output = output + "v" + Integer.toString(i+1) + " = (";
            for(int j = 0; j < VECTOR_DIM; j++){
                output = output + matrix[i][j];
                if(j == VECTOR_DIM - 1){
                    output = output + ")\n";
                }else{
                    output = output + ", ";
                }
            }
        }

        return output;
    }
}
