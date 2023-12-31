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
