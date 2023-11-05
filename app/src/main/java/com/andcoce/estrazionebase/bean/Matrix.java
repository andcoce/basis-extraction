package com.andcoce.estrazionebase.bean;

import java.util.ArrayList;
import java.util.Random;

public class Matrix {

    private final int VECTOR_DIM = 5;
    private final int N_VECTORS = 10;
    private double matrix[][];
    private Random rand;
    private int bound;

    public Matrix(int bound){
        matrix = new double[N_VECTORS][VECTOR_DIM];
        rand = new Random();
        this.bound = bound + 1;
    }

    public void init(){ //genera matrice random, con valori da 0 a 9
        for(int i = 0; i < N_VECTORS; i++){
            for(int j = 0; j < VECTOR_DIM; j++){
                matrix[i][j] = (rand.nextInt(bound));
            }
        }
        /*
        matrix[0][0] = 0;
        matrix[0][1] = 1;
        matrix[0][2] = 1;
        matrix[0][3] = 2;
        matrix[0][4] = 0;

        matrix[1][0] = 1;
        matrix[1][1] = 0;
        matrix[1][2] = 2;
        matrix[1][3] = 0;
        matrix[1][4] = 2;

        matrix[2][0] = 1;
        matrix[2][1] = 1;
        matrix[2][2] = 0;
        matrix[2][3] = 1;
        matrix[2][4] = 0;

        matrix[3][0] = 2;
        matrix[3][1] = 2;
        matrix[3][2] = 0;
        matrix[3][3] = 1;
        matrix[3][4] = 1;

        matrix[4][0] = 2;
        matrix[4][1] = 1;
        matrix[4][2] = 2;
        matrix[4][3] = 2;
        matrix[4][4] = 1;

        matrix[5][0] = 1;
        matrix[5][1] = 1;
        matrix[5][2] = 2;
        matrix[5][3] = 1;
        matrix[5][4] = 2;

        matrix[6][0] = 2;
        matrix[6][1] = 2;
        matrix[6][2] = 0;
        matrix[6][3] = 0;
        matrix[6][4] = 1;

        matrix[7][0] = 0;
        matrix[7][1] = 0;
        matrix[7][2] = 0;
        matrix[7][3] = 2;
        matrix[7][4] = 1;

        matrix[8][0] = 2;
        matrix[8][1] = 2;
        matrix[8][2] = 1;
        matrix[8][3] = 2;
        matrix[8][4] = 1;

        matrix[9][0] = 2;
        matrix[9][1] = 2;
        matrix[9][2] = 2;
        matrix[9][3] = 2;
        matrix[9][4] = 0;*/
    }

    public int getVECTOR_DIM() {
        return VECTOR_DIM;
    }

    public int getN_VECTORS() {
        return N_VECTORS;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public String toVectorString(){
        String output = "Si considerino i seguenti vettori generatori per R⁵:\n\n";

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
